package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.model.OmisionModel;
import com.is.controlincidencias.repository.ComisionRepository;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.service.ComisionService;
import com.is.controlincidencias.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("comisionServiceImpl")

public class ComisionServiceImpl  implements ComisionService{

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Autowired
    @Qualifier("comisionRepository")
    private ComisionRepository comisionRepository;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;


    @Override
    public int getLastJustificante()
        {
            List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
            return ids.get(ids.size()-1);
        }

    @Override
    public void addOmision(OmisionModel om, int id)
        {

        }
}
