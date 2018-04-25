package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;

import java.util.List;

public interface IncidenciaService {
    List<Incidencia> getIncidenciasByPersonal (Personal personal);
}
