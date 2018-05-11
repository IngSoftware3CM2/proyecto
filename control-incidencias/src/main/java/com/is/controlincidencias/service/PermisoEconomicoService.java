package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.model.PermisoEconomicoModel;

public interface PermisoEconomicoService {

    int addPermisoEconomico(PermisoEconomicoModel permisoEconomicoModel, Justificante justificante);
    PermisoEconomico findByJustificante(Justificante justificante);
    boolean existsByIdjustificante (int id);

}
