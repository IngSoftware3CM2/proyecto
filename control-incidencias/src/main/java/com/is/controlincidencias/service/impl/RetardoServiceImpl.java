package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.model.OmisionModel;
import com.is.controlincidencias.model.RetardoModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.OmisionESRepository;
import com.is.controlincidencias.repository.RetardoRepository;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.OmisionESService;
import com.is.controlincidencias.service.RetardoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service("retardoServiceImpl")
public class RetardoServiceImpl implements RetardoService{


    @Autowired
    @Qualifier("RetardoRepository")
    private RetardoRepository retardoRepository;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;


    @Override
    public void addRetardo(RetardoModel om, int idincidencia, String fesha)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        LocalDate fecha = LocalDate.parse(fesha, formatter);
        Date fecha2 = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()); //enn el formato que el jsutificante lo quiere -3-
        justificanteRepository.altaJustificante("Espera",fecha2,0, om.getIdJustificante()); //idJustificante es el noempleado :3
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        retardoRepository.guardaJustificanteRetardo(om.getJustificacion(),  ids.get(ids.size() - 1));

        incidenciaService.updateIdJustificante(ids.get(ids.size() - 1), idincidencia);
    }

    @Override
    public String getJust(int idJust)
    {
        return retardoRepository.getJustificacion(idJust);
    }

    @Override
    public String getFecha(int id)
    {
        return retardoRepository.getFecha(id);
    }

    @Override
    public void updateJust(String just, int id)
    {
        retardoRepository.updateRetardo(just, id);
    }
}
