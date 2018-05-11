package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;

import java.util.List;

public interface IncidenciaService {
    List<Incidencia> listAllIncidencia ();

    Incidencia consultarIncidencia(int id);

    List<Incidencia> getIncidenciasByPersonal (Personal personal);

    List<Incidencia> getIncidenciasByJustificanteId(int justificanteId, List<Incidencia> incidencias);

    void updateIdJustificante(int idJustificante, int idIncidencia);

    int getNoEmpleadoByIdJustificante(int id);
}
