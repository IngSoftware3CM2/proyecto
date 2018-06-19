package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.*;
import com.is.controlincidencias.repository.*;
import com.is.controlincidencias.service.IncidenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service("incidenciaServiceImpl")
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    @Qualifier("incidenciaRepository")
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    @Qualifier("personalRepository")
    private PersonalRepository personalRepository;

    @Autowired
    @Qualifier("diaRepository")
    private DiaRepository diaRepository;

    @Autowired
    @Qualifier("asistenciaRepository")
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    @Qualifier("tiempoSuplGeneradoRepository")
    private TiempoSuplGeneradoRepository tiempoSuplGeneradoRepository;

    private static final int MINUTO = 60;
    private static final int TREINTA_MIN = MINUTO * 30;
    private static final int TREINTA_UNO = MINUTO * 31;
    private static final int ONCE = MINUTO * 11;
    private static final int HORA = 3600;
    private static final String ADMON = "ROLE_DCADM";

    @Override
    public List<Incidencia> getIncidenciasByPersonal(Personal personal) {
        return incidenciaRepository.findAllByPersonal(personal);
    }

    @Override
    public List<Incidencia> getIncidenciasByJustificanteId(int justificanteId, List<Incidencia> incidencias) {
        List<Incidencia> incidenciasConIdJustificante = new ArrayList<>();
        for (Incidencia incidencia : incidencias){
            if (incidencia.getJustificanteId() == justificanteId){
                incidenciasConIdJustificante.add(incidencia);
            }
        }
        return incidenciasConIdJustificante;
    }

    @Override
    public void updateIdJustificante(int idJustificante, int idIncidencia) {
        incidenciaRepository.updateIdJustificante(idJustificante,idIncidencia);
    }

    @Override
    public List<Incidencia> listAllIncidencia() {
        return null;
    }

    @Override
    public Incidencia consultarIncidencia(int id) {
        return incidenciaRepository.findByIdIncidencia(id);
    }


    @Override
    public int getIdEmpleadoByIdJustificante(int id) {
            return incidenciaRepository.getIdEmpleadoByIdJustificante(id);
    }
    // En el metodo de inicio de DCHController mando a llamar a esto metodo para pruebas
    // El metodo deberia estar comentado o descomentado segun se necesite
    //@Scheduled(cron = "0 00 23 * * MON-FRI")
    @Override
    public int registrarIncidencia() {
        // Si la fecha actual corresponde a un dia habil

        List<Personal> listaPersonal = personalRepository.findAll();
        // Uso localdate porque es como esta mapeada la base
        LocalDate actual = LocalDate.now();

        // Obteniendo la fecha del dia que marca la RN48
        LocalDate fecha = actual.minusDays(3); // resta

        // Validar si esa fecha es un dia inhabil o fin de semana

        // Obtener el nombre del dia en el formato que esta en la base de datos (LUN, MAR, MIE, JUE,
        // VIE) para hacer la consulta
        String diaSemana = obtenerDia(fecha);
        Integer horas;
        for(Personal per : listaPersonal){
            // Obtiene el numero de empleado y tipo de personal
            log.info("--------------------------------------------------------------------------");
            log.info("Nombre: "+per.getNombre()+" ID: "+per.getIdEmpleado()+ " Tipo: " + per.getTipo());
            HorarioActual h = per.getHorarioActual();
            Integer idHorario = h.getIdHorario();
            // Obtiene el horario de dicho empleado de acuerdo a la RN48 (del dia previo al anterior)
            Dia dia = diaRepository.findFirstByHorarioActual_IdHorarioAndNombre(idHorario,
                    diaSemana);

            // Obtiene registro de entrada y de salida del empleado del dia que cumpla con la RN48
            // Uso esta madre porque ya la tenia xD
            Asistencia a = asistenciaRepository.findAsistenciaByFechaRegistroAndPersonalNoTarjeta
                    (fecha, per.getNoTarjeta());
            // Coteja las horas de entrada y salida del horario del empleado con las de su asistencia
            // En caso de haber inconsistencias identificar el tipo de incidencia, de acuerdo al tipo de personal y las
            // respectivas reglas del negocio
            Incidencia incidencia = new Incidencia();
            if (per.getTipo().equals("ROLE_DOC") || per.getTipo().equals(ADMON)) {
                // Trayectoria F
                if (noTieneAsistencia(a)) {
                    if (per.getTipo().equals(ADMON)) {
                        // Trayectoria G
                        log.info("TRAYECTORIA G DENTRO DE F");
                        incidencia.setTipo("FD");
                    } else {
                        log.info("TRAYECTORIA F de DOCENTE");
                        incidencia.setTipo("FC");
                    }
                    horas = dia.getHoraSalida().toSecondOfDay() - dia.getHoraEntrada().toSecondOfDay();
                    horas = horas / HORA;
                    incidencia.setHorasFaltantes(horas);
                    incidencia.setFechaRegistro(fecha);
                } else {
                    if (per.getHabierto())
                        incidencia = esAbierto(a, fecha, per); // Trayectoria A
                    else
                        incidencia = noAbierto(dia, a, per, fecha);
                }
            } else if (per.getTipo().equals("PAAE")) {
                incidencia = esPAAE(a, dia, fecha, per);
            }
            guardarIncidencia(incidencia, per.getIdEmpleado());
            log.info("-------------------------¿Que chingados?-----------------------------------");
        }
        return 0;
    }

    @Override
    public void updateIdIncidenciaAndHorasCubrir(int idJustificante, int idIncidencia, int horas) {
        incidenciaRepository.updateIdJustificanteAndHorasCubrir(idJustificante, idIncidencia,
                horas);
    }

    private Incidencia esAbierto(Asistencia a, LocalDate fecha, Personal per) {
        int entradaRegistrada = a.getHoraEntrada().toSecondOfDay();
        int salidaRegistrada = a.getHoraSalida().toSecondOfDay();
        float resta = (float) salidaRegistrada - entradaRegistrada;
        Integer horas;
        LocalTime hrSuplementario;
        resta = resta / 3600f;
        Incidencia incidencia = new Incidencia();
        TiempoSuplGenerado tiempoSuplGenerado = new TiempoSuplGenerado();
        if (resta < 8) {
            // Trayectoria I
            log.info("Te faltaron horas " + resta + " Trayectoria I");
            horas = (int) Math.ceil(8 - resta);
            incidencia.setTipo("FC");
            incidencia.setFechaRegistro(fecha); // no se si es esta fecha
            incidencia.setHorasFaltantes(horas);
        } else if (resta > 9) {
            // Trayectoria L
            log.info("Te pasaste de horas " + resta + " Trayectoria L");
            // Registrar horas suplementarias
            horas = (int) Math.floor(resta - 8);
            hrSuplementario = LocalTime.of(horas, 0);
            tiempoSuplGenerado.setFechaRegistro(fecha);
            tiempoSuplGenerado.setHoras(hrSuplementario);
            guardarSuplGenerado(tiempoSuplGenerado, per.getIdEmpleado());
        } else {
            // Trayectoria J
            log.info("TODO CHIDO - Trayectoria J");
        }
        return incidencia;
    }

    private Incidencia noAbierto(Dia dia, Asistencia a, Personal per, LocalDate fecha) {
        int entradaRegistrada = a.getHoraEntrada().toSecondOfDay();
        int salidaRegistrada = a.getHoraSalida().toSecondOfDay();
        int entradaHorario = dia.getHoraEntrada().toSecondOfDay();
        int salidaHorario = dia.getHoraSalida().toSecondOfDay();
        boolean trayectoriaE = entradaRegistrada >= entradaHorario + ONCE;
        trayectoriaE = trayectoriaE || salidaRegistrada < salidaHorario;
        Incidencia incidencia = new Incidencia();
        Integer horas;
        if (trayectoriaE) {
            // Trayectoria E
            if (per.getTipo().equals(ADMON)) {
                // Trayectoria G
                log.info("Trayectoria G dentro de E");
                incidencia.setTipo("FD");
            } else {
                log.info("Trayectoria E de PAAE");
                incidencia.setTipo("FC");
            }
            incidencia.setFechaRegistro(fecha);
            horas = calcularHorasDocente(dia, a);
            incidencia.setHorasFaltantes(horas);
            return incidencia;
        }
        int x = salidaRegistrada - entradaRegistrada;
        int y = salidaHorario - entradaHorario;
        boolean trayectoriaH = x >= (y + HORA);
        if (trayectoriaH) {
            ejecutarTrayectoriaH(x, y, fecha, per);
        }
        return incidencia;
    }

    private Incidencia esPAAE(Asistencia a, Dia dia, LocalDate fecha, Personal per) {
        int entradaRegistrada = a.getHoraEntrada().toSecondOfDay();
        int salidaRegistrada = a.getHoraSalida().toSecondOfDay();
        int entradaHorario = dia.getHoraEntrada().toSecondOfDay();
        int salidaHorario = dia.getHoraSalida().toSecondOfDay();
        Incidencia incidencia = new Incidencia();
        Integer horas;
        if (noTieneAsistencia(a)) {
            //Trayectoria D
            log.info("TRAYECTORIA D");
            horas = (salidaHorario - entradaHorario) / HORA;
            incidencia.setHorasFaltantes(horas);
            incidencia.setTipo("FI");
            incidencia.setFechaRegistro(fecha);
        } else {
            boolean esTrayectoriaB = entradaHorario + MINUTO <= entradaRegistrada;
            esTrayectoriaB = esTrayectoriaB && entradaRegistrada <= entradaHorario + TREINTA_MIN;
            esTrayectoriaB = esTrayectoriaB && salidaHorario <= salidaRegistrada;
            if (esTrayectoriaB) {
                // Trayectoria B
                log.info("TRAYECTORIA B");
                incidencia.setFechaRegistro(fecha);
                incidencia.setTipo("RE");
                incidencia.setHorasFaltantes(0);
                return incidencia;
            }
            boolean esTrayectoriaC = entradaRegistrada >= entradaHorario + TREINTA_UNO;
            esTrayectoriaC = esTrayectoriaC || salidaRegistrada < salidaHorario;
            if (esTrayectoriaC) {
                // Trayectoria C
                log.info("TRAYECTORIA C");
                horas = calcularHorasPAAE(dia, a);
                incidencia.setTipo("FI");
                incidencia.setHorasFaltantes(horas);
                return incidencia;
            }
            // Esta tambien aplica para PAAE
            int x = salidaRegistrada - entradaRegistrada;
            int y = salidaHorario - entradaHorario;
            boolean trayectoriaH = x >= (y + HORA);
            if (trayectoriaH) {
                ejecutarTrayectoriaH(x, y, fecha, per);
            }
        }
        return incidencia;
    }

    private void ejecutarTrayectoriaH(int x, int y, LocalDate fecha, Personal per) {
        //Trayectoria H
        TiempoSuplGenerado tiempoSuplGenerado = new TiempoSuplGenerado();
        log.info("TRAYECTORIA H");
        // Calcular suplementario y registrar
        Integer horas = (x-y)/HORA;
        tiempoSuplGenerado.setHoras(LocalTime.of(horas, 0));
        tiempoSuplGenerado.setFechaRegistro(fecha);
        guardarSuplGenerado(tiempoSuplGenerado, per.getIdEmpleado());
    }

    private Integer calcularHorasDocente(Dia dia, Asistencia a) {
        int minutos = a.getHoraEntrada().getMinute();
        Integer horas = dia.getHoraSalida().getHour() - dia.getHoraEntrada().getHour();
        if (minutos >= 11)
            horas = horas - (a.getHoraSalida().getHour() - (a.getHoraEntrada().getHour() + 1));
        else
            horas = horas - (a.getHoraSalida().getHour() - a.getHoraEntrada().getHour());
        return horas;
    }

    private Integer calcularHorasPAAE(Dia dia, Asistencia a) {
        int minutos = a.getHoraEntrada().getMinute();
        Integer horas = dia.getHoraSalida().getHour() - dia.getHoraEntrada().getHour();
        if (minutos >= 31)
            horas = horas - (a.getHoraSalida().getHour() - (a.getHoraEntrada().getHour() + 1));
        else
            horas = horas - (a.getHoraSalida().getHour() - a.getHoraEntrada().getHour());
        return horas;
    }

    private void guardarIncidencia(Incidencia incidencia, Integer idEmpleado) {
        if (incidencia.getTipo() == null || incidencia.getTipo().equals(""))
            return;
        if (incidenciaRepository.existsIncidenciasByFechaRegistroAndPersonal_IdEmpleado
                (incidencia.getFechaRegistro(), idEmpleado)) {
            log.info("YA HAY UNA INCIDENCIA ESE DIA");
            return;
        }
        Integer id = incidenciaRepository.obtenerMaximoIdAsistencia();
        if (id == null)
            id = 1;
        else
            id += 1;
        log.info("id:" + id + " fecha:" + incidencia.getFechaRegistro() + " idEmpleado:" +
                idEmpleado + " horas:" + incidencia.getHorasFaltantes());
        incidenciaRepository.insertarAsistencia(id, incidencia.getFechaRegistro(), incidencia
                .getTipo(), idEmpleado, incidencia.getHorasFaltantes());
    }

    private void guardarSuplGenerado(TiempoSuplGenerado tiempo, Integer idEmpleado) {
        if (tiempoSuplGeneradoRepository.existsByFechaRegistroAndPersonal_IdEmpleado(tiempo
                .getFechaRegistro(), idEmpleado)) {
            log.error("YA EXISTE ESE TIEMPO");
            return;
        }
        Integer id = tiempoSuplGeneradoRepository.obtenerMaximoIdIncidencia();
        if (id == null)
            id = 1;
        else
            id += 1;

        tiempoSuplGeneradoRepository.registrarSuplementario(id, tiempo.getFechaRegistro(), tiempo
                .getHoras(), false, idEmpleado);
    }

    private boolean noTieneAsistencia(Asistencia a) {
        return a == null || a.getHoraEntrada() == null || a.getHoraSalida() == null;
    }

    private String obtenerDia(LocalDate fecha) {
        switch (fecha.getDayOfWeek()) {
            case MONDAY:
                return "LUN";
            case TUESDAY:
                return "MAR";
            case WEDNESDAY:
                return "MIE";
            case THURSDAY:
                return "JUE";
            case FRIDAY:
                return "VIE";
            default:
                return "LUN";
        }
    }
}
