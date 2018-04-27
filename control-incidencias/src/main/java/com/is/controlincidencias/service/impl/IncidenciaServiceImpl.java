package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.repository.IncidenciaRepository;
import com.is.controlincidencias.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("incidenciaServiceImpl")
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    @Qualifier("incidenciaRepository")
    private IncidenciaRepository incidenciaRepository;

    @Override
    public List<Incidencia> listAllIncidencia() {
        return incidenciaRepository.findAll();
    }

    @Override
    public Incidencia consultarIncidencia(int id) {
        return incidenciaRepository.findByIdIncidencia(id);
    }


}
