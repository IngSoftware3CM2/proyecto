package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;

import java.util.List;

public interface IncidenciaService {
<<<<<<< HEAD
    List<Incidencia> listAllIncidencia ();
    Incidencia consultarIncidencia(int id);
=======
    List<Incidencia> getIncidenciasByPersonal (Personal personal);
    List<Incidencia> getIncidenciasByJustificanteId (int justificanteId, List<Incidencia> incidencias);
>>>>>>> f6b5f8322aa0b43614846410c8d135a4ef08ce17
}
