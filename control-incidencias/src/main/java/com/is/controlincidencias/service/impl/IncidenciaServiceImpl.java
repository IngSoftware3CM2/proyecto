package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.repository.IncidenciaRepository;
import com.is.controlincidencias.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("incidenciaServiceImpl")
public class IncidenciaServiceImpl implements IncidenciaService {

    @Autowired
    @Qualifier("incidenciaRepository")
    private IncidenciaRepository incidenciaRepository;

    @Override
    public List<Incidencia> getIncidenciasByPersonal(Personal personal) {
        return incidenciaRepository.findAllByPersonal(personal);
    }

    @Override
    public List<Incidencia> getIncidenciasByJustificanteId(int justificanteId, List<Incidencia> incidencias) {
        List<Incidencia> incidenciasConIdJustificante = new ArrayList<>();
        for (Incidencia incidencia : incidencias){
            if (incidencia.getJustificanteId() == justificanteId){
                incidenciasConIdJustificante.add(incidencia);
            }
        }
        return incidenciasConIdJustificante;
    }

    @Override
    public void updateIdJustificante(int idJustificante, int idIncidencia) {
        incidenciaRepository.updateIdJustificante(idJustificante,idIncidencia);
    }

    @Override
    public List<Incidencia> listAllIncidencia() {
        return null;
    }

    @Override
    public Incidencia consultarIncidencia(int id) {
        return incidenciaRepository.findByIdIncidencia(id);
    }


    @Override
    public int getNoEmpleadoByIdJustificante(int id)
        {
            return incidenciaRepository.getNoEmpleadoByIdJustificante(id);
        }
}
