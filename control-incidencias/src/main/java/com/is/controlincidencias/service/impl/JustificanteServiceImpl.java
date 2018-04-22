package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.service.JustificanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("justificanteServiceImpl")
public class JustificanteServiceImpl implements JustificanteService {

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Override
    public List<Justificante> listAllJustificante() {
        return justificanteRepository.findAll();
    }
}
