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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("asistenciaServiceImpl")
public class AsistenciaServiceImpl implements AsistenciaService {
    private static LocalTime siete = LocalTime.of(7, 0, 0, 0);
    private static LocalTime veintidos = LocalTime.of(22, 0, 0, 0);
    private static LocalTime seisMedia = LocalTime.of(6, 30, 0);
    private static LocalTime veintitres = LocalTime.of(23, 0, 0, 0);

    private final AsistenciaRepository asistenciaRepository;
    private final PersonalRepository personalRepository;

    @Autowired
    public AsistenciaServiceImpl(
            @Qualifier("asistenciaRepository") AsistenciaRepository asistenciaRepository,
            @Qualifier("personalRepository") PersonalRepository personalRepository) {
        this.asistenciaRepository = asistenciaRepository;
        this.personalRepository = personalRepository;
    }

    @Override
    public boolean buscarAsistencia(LocalDate fecha, String noTarjeta) {
        return asistenciaRepository.existsAsistenciaByFechaRegistroAndPersonalNoTarjeta(fecha,
                noTarjeta);
    }

    @Override
    public boolean buscarTarjeta(String noTarjeta) {
        return personalRepository.existsPersonalByNoTarjeta(noTarjeta);
    }

    @Override
    public void agregarAsistencia(AsistenciaJSON asistenciaJSON) {
        Personal p = personalRepository.getPersonalByNoTarjeta(asistenciaJSON.getTarjeta());
        Asistencia asistencia = new Asistencia();
        asistencia.setPersonal(p);
        asistencia.setFechaRegistro(asistenciaJSON.getFecha());
        if (asistenciaJSON.getHoraEntrada().compareTo(siete) > 0) asistencia.setHoraEntrada(
                asistenciaJSON.getHoraEntrada());
        else asistencia.setHoraEntrada(siete);
        if (asistenciaJSON.getHoraSalida().compareTo(veintidos) < 0) asistencia.setHoraSalida(
                asistenciaJSON.getHoraSalida());
        else asistencia.setHoraSalida(veintidos);
        Asistencia a = asistenciaRepository.save(asistencia);
        log.info("Id Asistencia: " + a.getIdAsistencia());
    }

    @Override
    public int existeAsistencia(AsistenciaForm asistenciaForm) {
        boolean existeTarjeta = personalRepository.existsPersonalByNoTarjeta(
                asistenciaForm.getTarjeta());
        if (!existeTarjeta) return 1; // No existe la tarjeta

        Asistencia a = asistenciaRepository.findAsistenciaByFechaRegistroAndPersonalNoTarjeta(
                asistenciaForm.getFecha(), asistenciaForm.getTarjeta());
        if (a == null) return 2; // No se encontro registro

        return 0; // Salio chido
    }

    @Override
    public AsistenciaForm buscarAsistencia(AsistenciaForm asistenciaForm) {
        Asistencia a = asistenciaRepository.findAsistenciaByFechaRegistroAndPersonalNoTarjeta(
                asistenciaForm.getFecha(), asistenciaForm.getTarjeta());

        asistenciaForm.setHoraSalida(a.getHoraSalida());
        asistenciaForm.setHoraEntrada(a.getHoraEntrada());
        asistenciaForm.setFecha(asistenciaForm.getFecha());
        asistenciaForm.setTarjeta(asistenciaForm.getTarjeta());
        asistenciaForm.setIdAsistencia(asistenciaForm.getIdAsistencia());
        asistenciaForm.setNombre(a.getPersonal().getNombre() + " "
                + a.getPersonal().getApellidoPaterno() + " " + " "
                + a.getPersonal().getApellidoMaterno());

        return asistenciaForm;
    }

    @Override
    public Asistencia modificarAsistencia(AsistenciaForm asistenciaForm) {

        LocalTime horaEntrada = asistenciaForm.getHoraEntrada();
        LocalTime horaSalida = asistenciaForm.getHoraSalida();
        Asistencia a = asistenciaRepository.findAsistenciaByFechaRegistroAndPersonalNoTarjeta(
                asistenciaForm.getFecha(), asistenciaForm.getTarjeta());
        if (a == null) return null;
        if (!validarHoras(horaEntrada, horaSalida)) return null;

        if (horaEntrada.compareTo(siete) > 0) a.setHoraEntrada(horaEntrada);
        else a.setHoraEntrada(siete);
        if (horaSalida.compareTo(veintidos) < 0) a.setHoraSalida(horaSalida);
        else a.setHoraSalida(veintidos);

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

    private boolean validarHoras(LocalTime horaEntrada, LocalTime horaSalida) {
        if (horaEntrada.compareTo(seisMedia) < 0 || horaEntrada.compareTo(veintitres) > 0)
            return false;
        return horaSalida.compareTo(seisMedia) >= 0 && horaSalida.compareTo(veintitres) <= 0;
    }
}
