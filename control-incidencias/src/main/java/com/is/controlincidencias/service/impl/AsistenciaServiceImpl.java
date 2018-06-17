package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.entity.Dia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.Quincena;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.model.AsistenciaJSON;
import com.is.controlincidencias.model.AsistenciaMostrar;
import com.is.controlincidencias.repository.AsistenciaRepository;
import com.is.controlincidencias.repository.PeriodoInhabilRepository;
import com.is.controlincidencias.repository.PersonalRepository;
import com.is.controlincidencias.repository.QuincenaRepository;
import com.is.controlincidencias.service.AsistenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("asistenciaServiceImpl")
public class AsistenciaServiceImpl implements AsistenciaService {
    private static LocalTime siete = LocalTime.of(7, 0, 0, 0);
    private static LocalTime veintidos = LocalTime.of(22, 0, 0, 0);
    private static LocalTime seis = LocalTime.of(6, 0, 0);

    private static final int NO_EXISTE_TARJETA = 1;
    private static final int REGISTRO_DUPLICADO = 2;
    private static final int FECHA_ASISTENCIA_INVALIDA = 5;

    private final AsistenciaRepository asistenciaRepository;
    private final PersonalRepository personalRepository;
    private final PeriodoInhabilRepository periodoInhabilRepository;
    private final QuincenaRepository quincenaRepository;

    @Autowired
    public AsistenciaServiceImpl(
            @Qualifier("asistenciaRepository") AsistenciaRepository asistenciaRepository,
            @Qualifier("personalRepository") PersonalRepository personalRepository,
            @Qualifier("periodoInhabilRepository") PeriodoInhabilRepository periodoInhabilRepository,
            QuincenaRepository quincenaRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.personalRepository = personalRepository;
        this.periodoInhabilRepository = periodoInhabilRepository;
        this.quincenaRepository = quincenaRepository;
    }

    @Override
    public Asistencia agregarAsistencia(AsistenciaForm form) {
        Personal p = personalRepository.findFirstByNoTarjeta(form.getTarjeta());
        Asistencia asistencia = new Asistencia();
        asistencia.setPersonal(p);
        if (form.getHoraEntrada() == null || form.getHoraSalida() == null) {
            log.info("OMISION DE HORARIO");
        } else {
            if (!horasValidas(form.getHoraEntrada(), form.getHoraSalida(), p.getTipo()))
                return null;
        }
        asistencia.setFechaRegistro(form.getFecha());
        asistencia.setHoraEntrada(form.getHoraEntrada());
        asistencia.setHoraSalida(form.getHoraSalida());

        return asistenciaRepository.save(asistencia);
    }

    @Override
    public int existeAsistencia(AsistenciaForm asistenciaForm) {
        Personal p = personalRepository.findByNoTarjeta(asistenciaForm.getTarjeta());
        if (p == null) return NO_EXISTE_TARJETA; // No existe la tarjeta

        LocalDate fechaFinal = LocalDate.now();
        LocalDate fechaIncial = fechaFinal;
        if (esInhabil(asistenciaForm.getFecha()))
            return FECHA_ASISTENCIA_INVALIDA;
        int contador = 0;
        while (contador < 2) {
            fechaIncial = fechaIncial.minusDays(1);
            if (!esInhabil(fechaIncial)) {
                contador++;
            }
        }
        log.info("existeAsistencia() fechaInicial =" + fechaIncial);
        log.info("existeAsistencia() fechaFinal =" + fechaFinal);
        if (asistenciaForm.getFecha().isAfter(fechaFinal) || asistenciaForm.getFecha().isBefore(
                fechaIncial)) return FECHA_ASISTENCIA_INVALIDA;

        boolean existe = asistenciaRepository
                .existsAsistenciaByFechaRegistroAndPersonalNoTarjeta(asistenciaForm.getFecha(),
                        asistenciaForm.getTarjeta());

        if (existe) return REGISTRO_DUPLICADO;

        return 0; // Salio chido
    }

    @Override
    public AsistenciaForm buscarAsistencia(AsistenciaForm asistenciaForm) {
        Asistencia a = asistenciaRepository.findAsistenciaByFechaRegistroAndPersonalNoTarjeta(
                asistenciaForm.getFecha(), asistenciaForm.getTarjeta());
        Personal p = personalRepository.findFirstByNoTarjeta(asistenciaForm.getTarjeta());

        asistenciaForm.setFecha(asistenciaForm.getFecha());
        asistenciaForm.setTarjeta(asistenciaForm.getTarjeta());
        asistenciaForm.setId(asistenciaForm.getId());
        asistenciaForm.setNombre(p.getNombre() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());

        if (a != null) {
            asistenciaForm.setHoraSalida(a.getHoraSalida());
            asistenciaForm.setHoraEntrada(a.getHoraEntrada());
        } else {
            if (p.getHorarioActual() != null) {
                List<Dia> dias = p.getHorarioActual().getDias();
                for (Dia d : dias) {
                    if (d.getNombre().equals(obtenerDia(asistenciaForm.getFecha()))) {
                        asistenciaForm.setHoraSalida(d.getHoraSalida());
                        asistenciaForm.setHoraEntrada(d.getHoraEntrada());
                        log.info("El dia es " + d.getNombre() + " " + asistenciaForm.getHoraEntrada());
                        break;
                    }
                }
            }
        }
        return asistenciaForm;
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

    @Override
    public Asistencia modificarAsistencia(AsistenciaForm form) {
        Personal p = personalRepository.findByNoTarjeta(form.getTarjeta());
        Asistencia a = asistenciaRepository.findByIdAsistencia(form.getId());

        if (!horasValidas(form.getHoraEntrada(), form.getHoraSalida(), p.getTipo()))
            return null;
        else
            log.info("modificarAsistencia() horas validas");

        a.setHoraEntrada(form.getHoraEntrada());
        a.setHoraSalida(form.getHoraSalida());

        return asistenciaRepository.save(a);
    }

    @Override
    public List<AsistenciaJSON> obtenerAsistencias(LocalDate fecha) {
        List<AsistenciaJSON> lista = new ArrayList<>();
        asistenciaRepository.findAllByFechaRegistro(fecha).forEach(item -> {
            AsistenciaJSON asistencia = new AsistenciaJSON();
            asistencia.setFecha(item.getFechaRegistro());
            asistencia.setHoraEntrada(item.getHoraEntrada());
            asistencia.setHoraSalida(item.getHoraSalida());
            asistencia.setTarjeta(item.getPersonal().getNoTarjeta());
            asistencia.setId(item.getIdAsistencia());
            lista.add(asistencia);
        });
        return lista;
    }

    @Override
    public void eliminarAsistenciaPorId(Integer id) {
        if (asistenciaRepository.existsById(id)) asistenciaRepository.deleteById(id);
    }

    @Override
    public List<AsistenciaMostrar> obtenerAniosPorTarjeta(String tarjeta) {
        Personal p = personalRepository.findFirstByNoTarjeta(tarjeta);
        List<AsistenciaMostrar> lista =  new ArrayList<>();
        if (p == null) {
            AsistenciaMostrar error = new AsistenciaMostrar();
            error.setTarjeta("errorC");
            lista.add(error);
        } else {
            asistenciaRepository.obtenerDiferentesAnios(p.getIdEmpleado()).forEach(item -> {
                AsistenciaMostrar a = new AsistenciaMostrar();
                a.setAnio(String.valueOf(item.intValue()));
                a.setNombre(p.getNombre() + " " + p.getApellidoPaterno() + " " + p.getApellidoMaterno());
                lista.add(a);
            });
        }
        return lista;
    }

    @Override
    public List<String> obtenerQuincenas(AsistenciaMostrar asistenciaMostrar) {
        String expresion = asistenciaMostrar.getAnio() + "-%";
        List<Quincena> quincenas = quincenaRepository
                .findAllByQuincenaReportadaIsLikeOrderByQuincenaReportadaDesc(expresion);
        List<String> resultado = new ArrayList<>();
        if (personalRepository.existsPersonalByNoTarjeta(asistenciaMostrar.getTarjeta())) {
            quincenas.forEach(item -> {
                // existe asistencia en la quincena que esta entre X & y del personal con tarjeta
                boolean existe = asistenciaRepository.
                        existsAsistenciasByPersonalNoTarjetaAndFechaRegistroBetween(asistenciaMostrar
                                .getTarjeta(), item.getInicio(), item.getFin());
                log.info("obtenerQuincenas() Existe? =" + existe);
                if (existe)
                    resultado.add(item.getQuincenaReportada());
            });
        }
        return resultado;
    }

    @Override
    public List<AsistenciaJSON> obtenerAsistenciasParaMostrar(AsistenciaMostrar asistenciaMostrar) {
        Quincena quincena = quincenaRepository
                .findFirstByQuincenaReportadaIs(asistenciaMostrar.getQuincena());
        List<Asistencia> asistencias = new ArrayList<>();
        if (quincena != null) {
            asistencias = asistenciaRepository
                    .findAllByPersonalNoTarjetaAndFechaRegistroBetween(asistenciaMostrar.getTarjeta()
                            , quincena.getInicio(), quincena.getFin());
        }
        List<AsistenciaJSON> lista = new ArrayList<>();
        asistencias.forEach(item -> {
            AsistenciaJSON a = new AsistenciaJSON();
            a.setFecha(item.getFechaRegistro());
            a.setHoraEntrada(item.getHoraEntrada());
            a.setHoraSalida(item.getHoraSalida());
            lista.add(a);
        });
        return lista;
    }

    private boolean horasValidas(LocalTime entrada, LocalTime salida, String rol) {
        if (rol.equals("ROLE_PAAE")) {
            if (entrada.isBefore(seis) || salida.isAfter(veintidos) || entrada.isAfter(salida))
                return false;
            log.info("ES PAAE");
        } else {
            if (entrada.isBefore(siete) || salida.isAfter(veintidos) || entrada.isAfter(salida))
                return false;
            log.info("NO ES PAAE");
        }

        log.info("horasValidas() es true :" + entrada.isBefore(siete) + " " + salida.isAfter
                (veintidos) + " " + entrada.isAfter(salida));
        return true;
    }

    private boolean esInhabil(LocalDate fecha) {
        boolean inhabil = periodoInhabilRepository
                .existsByInicioIsLessThanEqualAndFinGreaterThanEqual(fecha, fecha);
        log.info("esInhabil() 1= " + inhabil);
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
            inhabil = true;
        }
        log.info("esInhabil() 2= " + inhabil);
        return inhabil;
    }
}
