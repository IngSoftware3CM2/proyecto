package com.is.controlincidencias.service;

import com.is.controlincidencias.model.ConstanciaTiempoModel;

public interface ConstanciaTiempoService {
    void guardarConstanciaTiempo(ConstanciaTiempoModel constanciaTiempoModel, int idIncidencia, int noEmpleado);

    boolean existByidjustificante (int id);
}
