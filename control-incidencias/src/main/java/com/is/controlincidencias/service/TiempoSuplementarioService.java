package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.model.JustificateTiempoSuplModel;

public interface TiempoSuplementarioService {
    boolean existsByIdjustificante (int id);
    int saveJustificanteTS(JustificateTiempoSuplModel justificateTiempoSuplModel, Incidencia incidencia);
}
