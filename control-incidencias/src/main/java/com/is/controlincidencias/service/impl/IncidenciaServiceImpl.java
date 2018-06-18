package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.*;
import com.is.controlincidencias.repository.AsistenciaRepository;
import com.is.controlincidencias.repository.DiaRepository;
import com.is.controlincidencias.repository.IncidenciaRepository;
import com.is.controlincidencias.repository.PersonalRepository;
import com.is.controlincidencias.service.IncidenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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

    private static final int MINUTO = 60;
    private static final int TREINTA_MIN = MINUTO * 30;
    private static final int TREINTA_UNO = MINUTO * 31;
    private static final int ONCE = MINUTO * 11;

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
        log.info("The time is now {}", new Date());

        // Si la fecha actual corresponde a un dia habil

        List<Personal> listaPersonal = personalRepository.findAll();
        // Uso localdate porque es como esta mapeada la base
        LocalDate actual = LocalDate.now();
        LocalDate fecha = actual.minusDays(2); // resta
        Date date = new Date();
        Calendar c = Calendar.getInstance();

        // Obteniendo la fecha del dia que marca la RN48

        c.add(Calendar.DATE, -2);
        date = c.getTime();
        // Validar si esa fecha es un dia inhabil o fin de semana

        // Obtener el nombre del dia en el formato que esta en la base de datos (LUN, MAR, MIE, JUE,
        // VIE) para hacer la consulta
        String diaSemana = obtenerDia(fecha);
        // Obtenemos todas las tuplas de personal
        int hrEntrada, hrSalida, entradaHorario, salidaHorario;
        for(Personal per:listaPersonal){
            // Obtiene el numero de empleado y tipo de personal
            log.info("Nombre: "+per.getNombre()+" ID: "+per.getIdEmpleado()+ " Tipo: " +
                    ""+per.getTipo());
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
            if (per.getTipo().equals("ROLE_DOC") || per.getTipo().equals("ROLE_DCADM")) {
                // Trayectoria F
                if (a == null || a.getHoraEntrada() == null || a.getHoraSalida() == null) {

                    if (per.getTipo().equals("ROLE_DCADM")) {
                        // Trayectoria G
                    } else {
                    }
                    // Continua en 5 de la principal
                    // continue;
                } else {
                    // Trayectoria A
                    if (per.getHabierto()) {
                        float resta = (float) a.getHoraSalida().toSecondOfDay() - a.getHoraEntrada()
                                .toSecondOfDay();
                        resta = resta / 3600f;
                        if (resta < 8) {
                            // Trayectoria I
                            log.info("Te faltaron horas " + resta + " Trayectoria I");
                        } else if (resta > 9) {
                            // Trayectoria L
                            log.info("Te pasaste de horas " + resta + " Trayectoria L");
                        } else {
                            // Trayectoria J
                            log.info("TODO CHIDO - Trayectoria J");
                        }
                        // Continuar punto 5 trayectoria principal
                        //continue;
                    } else {
                        boolean trayectoriaE =;
                        if (trayectoriaE) {
                            // Trayectoria E
                            // 
                        }
                    }
                }
            } else if (per.getTipo().equals("ROLE_PAAE")) {
                if (a == null || a.getHoraEntrada() == null || a.getHoraSalida() == null) {
                    //Trayectoria D
                } else {
                    hrEntrada = a.getHoraEntrada().toSecondOfDay();
                    hrSalida = a.getHoraSalida().toSecondOfDay();
                    entradaHorario = dia.getHoraEntrada().toSecondOfDay();
                    salidaHorario = dia.getHoraSalida().toSecondOfDay();
                    boolean esTrayectoriaB = entradaHorario + MINUTO <= hrEntrada;
                    esTrayectoriaB = esTrayectoriaB && hrEntrada <= entradaHorario + TREINTA_MIN;
                    esTrayectoriaB = esTrayectoriaB && salidaHorario <= hrSalida;
                    if (esTrayectoriaB) {
                        // Trayectoria B
                        // Continua 5 principal
                        // continue;
                    }
                    boolean esTrayectoriaC = hrEntrada >= entradaHorario + TREINTA_UNO;
                    esTrayectoriaC = esTrayectoriaC || hrSalida < salidaHorario;
                    if (esTrayectoriaC) {
                        // Trayectoria C
                        // Continua en 5 de la principal
                        // continue;
                    }
                }

            }

            break;
        }

        return 0;
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
