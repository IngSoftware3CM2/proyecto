package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.model.OmisionModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.OmisionESRepository;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.OmisionESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service("omisionServiceImpl")
public class OmisionESServiceImpl implements OmisionESService{


    @Autowired
    @Qualifier("OmisionRepository")
    private OmisionESRepository omisionRepository;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;


    @Override
    public void addOmision(OmisionModel om, int idincidencia, String fesha)
        {
            Date fecha2 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //enn el formato que el justificante lo quiere -3-
            justificanteRepository.altaJustificante("Espera",fecha2,0, om.getIdJustificante()); //idJustificante es el noempleado :3
            List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
            omisionRepository.guardaJustificanteOmision(om.getJustificacion(), om.isTipo(),  ids.get(ids.size() - 1));
            incidenciaService.updateIdJustificante(ids.get(ids.size() - 1), idincidencia);
        }

    @Override
    public String getJust(int idJust)
        {
            return omisionRepository.getJustificacion(idJust);
        }

    @Override
    public String getFecha(int id)
        {
            return omisionRepository.getFecha(id);
        }

    @Override
    public void updateJust(String just, int id)
        {
            omisionRepository.updateOmision(just, id);
        }

    @Override
    public boolean existsByIdjustificante(int id) {
        return omisionRepository.existsByJustificante_IdJustificante(id);
    }
}
