package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.model.AsistenciaJSON;
import com.is.controlincidencias.repository.AsistenciaRepository;
import com.is.controlincidencias.repository.PersonalRepository;
import com.is.controlincidencias.service.AsistenciaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service("asistenciaServiceImpl")
public class AsistenciaServiceImpl implements AsistenciaService {
    private static LocalTime siete = LocalTime.of(7, 0, 0, 0);
    private static LocalTime veintidos = LocalTime.of(22,  0, 0, 0);
    private static LocalTime seisMedia = LocalTime.of(6, 30, 00);
    private static LocalTime veintitres = LocalTime.of(23,  0, 0, 0);
    private DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");

    @Autowired
    @Qualifier("asistenciaRepository")
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    @Qualifier("personalRepository")
    private PersonalRepository personalRepository;

    @Override
    public boolean buscarAsistencia(LocalDate fecha, int noTarjeta) {
        return asistenciaRepository.existsAsistenciaByFechaRegistroAndPersonal_NoTarjeta(fecha, noTarjeta);
    }

    @Override
    public boolean buscarTarjeta(int noTarjeta) {
        return personalRepository.existsPersonalByNoTarjeta(noTarjeta);
    }

    @Override
    public void agregarAsistencia(AsistenciaJSON asistenciaJSON) {
        Personal p = personalRepository.getPersonalByNoTarjeta(asistenciaJSON.getNoTarjeta());
        Asistencia asistencia = new Asistencia();
        asistencia.setPersonal(p);
        asistencia.setFechaRegistro(asistenciaJSON.getFechaRegistro());
        if (asistenciaJSON.getHoraEntrada().compareTo(siete) > 0)
            asistencia.setHoraEntrada(asistenciaJSON.getHoraEntrada());
        else
            asistencia.setHoraEntrada(siete);
        if (asistenciaJSON.getHoraSalida().compareTo(veintidos) < 0)
            asistencia.setHoraSalida(asistenciaJSON.getHoraSalida());
        else
            asistencia.setHoraSalida(veintidos);
        Asistencia a = asistenciaRepository.save(asistencia);
        log.info("Id Asistencia: " + a.getIdAsistencia());
    }

    @Override
    public int existeAsistencia(AsistenciaForm asistenciaForm) {
        boolean existeTarjeta = personalRepository.existsPersonalByNoTarjeta(asistenciaForm.getTarjeta());
        if (!existeTarjeta)
            return 1; // No existe la tarjeta

        LocalDate fecha = LocalDate.parse(asistenciaForm.getFecha(), formatterDate);
        Asistencia a = asistenciaRepository
                .findAsistenciaByFechaRegistroAndPersonal_NoTarjeta(fecha, asistenciaForm.getTarjeta());
        if (a == null)
            return 2; // No se encontro registro

        return 0; // Salio chido
    }

    @Override
    public AsistenciaForm buscarAsistencia(AsistenciaForm asistenciaForm) {
        LocalDate fecha = LocalDate.parse(asistenciaForm.getFecha(), formatterDate);
        Asistencia a = asistenciaRepository
                .findAsistenciaByFechaRegistroAndPersonal_NoTarjeta(fecha, asistenciaForm.getTarjeta());

        asistenciaForm.setHoraSalida(a.getHoraSalida().format(formatterHour));
        asistenciaForm.setHoraEntrada(a.getHoraEntrada().format(formatterHour));

        return asistenciaForm;
    }

    @Override
    public Asistencia modificarAsistencia(AsistenciaForm asistenciaForm) {
        LocalDate fecha = LocalDate.parse(asistenciaForm.getFecha(), formatterDate);
        LocalTime horaEntrada = LocalTime.parse(asistenciaForm.getHoraEntrada(), formatterHour);
        LocalTime horaSalida = LocalTime.parse(asistenciaForm.getHoraSalida(), formatterHour);
        Asistencia a = asistenciaRepository
                .findAsistenciaByFechaRegistroAndPersonal_NoTarjeta(fecha, asistenciaForm.getTarjeta());
        if (a == null)
            return null;
        if (!validarHoras(horaEntrada, horaSalida))
            return null;

        if (horaEntrada.compareTo(siete) > 0)
            a.setHoraEntrada(horaEntrada);
        else
            a.setHoraEntrada(siete);
        if (horaSalida.compareTo(veintidos) < 0)
            a.setHoraSalida(horaSalida);
        else
            a.setHoraSalida(veintidos);

        return asistenciaRepository.save(a);
    }

    private boolean validarHoras(LocalTime horaEntrada, LocalTime horaSalida) {
        if (horaEntrada.compareTo(seisMedia) < 0 || horaEntrada.compareTo(veintitres) > 0)
            return false;
        return horaSalida.compareTo(seisMedia) >= 0 && horaSalida.compareTo(veintitres) <= 0;
    }
}
