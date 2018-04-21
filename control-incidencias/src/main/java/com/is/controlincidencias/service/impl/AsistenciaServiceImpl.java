package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.repository.AsistenciaRepository;
import com.is.controlincidencias.repository.PersonalRepository;
import com.is.controlincidencias.service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("asistenciaServiceImpl")
public class AsistenciaServiceImpl implements AsistenciaService {
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
}
