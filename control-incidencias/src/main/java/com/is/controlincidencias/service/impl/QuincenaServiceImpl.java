package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.repository.QuincenaRepository;
import com.is.controlincidencias.service.QuincenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service("quincenaServiceImpl")
public class QuincenaServiceImpl implements QuincenaService {

    @Autowired
    @Qualifier("quincenaRepository")
    QuincenaRepository quincenaRepository;

    @Override
    public int idquincenaConFechaDeIncidencia(LocalDate fecha) {
        return quincenaRepository.obtenerIdQuincena(fecha);
    }
}
