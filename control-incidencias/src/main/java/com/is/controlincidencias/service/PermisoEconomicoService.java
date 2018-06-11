package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;

public interface PermisoEconomicoService {

    int preguntarAnoQuincena(int idempleado, int idquincena, String tipo);
    boolean existsByIdjustificante (int id);

    void registrarJustificante(int idEmpleado, Incidencia incidencia);
}
