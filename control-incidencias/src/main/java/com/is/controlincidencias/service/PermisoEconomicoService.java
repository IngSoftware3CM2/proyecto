package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.model.PermisoEconomicoModel;

public interface PermisoEconomicoService {

    int preguntarAnoQuincena(int idempleado, int idquincena, String tipo);
    boolean existsByIdjustificante (int id);

}
