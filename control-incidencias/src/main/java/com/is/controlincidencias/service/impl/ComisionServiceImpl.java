package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.ComisionOficial;
import com.is.controlincidencias.model.ComisionModel;
import com.is.controlincidencias.repository.ComisionRepository;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.service.ComisionService;
import com.is.controlincidencias.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
            return ids.size();
        }

    @Override
    public void addComision(ComisionModel com, int idincidencia)
        {
            String inicio;
            String fin;
            String archivo;

            inicio = com.getInicio();
            fin = com.getFin();
            archivo = com.getInvitacionArchivo();
            Date fecha2 = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()); //enn el formato que el jsutificante lo quiere -3- antes el LocalDate.now() era "fecha"
            justificanteRepository.altaJustificante(3,fecha2,8,com.getIdComision()); //idJustificante es el noempleado :3
            List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            LocalDate fechai = LocalDate.parse(inicio, formatter);
            LocalDate fechaf = LocalDate.parse(fin, formatter);
            comisionRepository.addComision(fechai, fechaf, archivo, ids.get(ids.size() - 1));
            incidenciaService.updateIdJustificante(ids.get(ids.size() - 1), idincidencia);
        }

    @Override
    public boolean existsByIdjustificante(int id) {
        return comisionRepository.existsByJustificante_IdJustificante(id);
    }

    @Override
    public ComisionOficial getCO(int id){return  comisionRepository.getCO(id);}

}
