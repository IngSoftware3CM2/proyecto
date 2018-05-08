package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.TipoA;
import com.is.controlincidencias.model.JustificanteTAModel;

import java.util.List;

public interface JustificanteTAService {

    boolean existsByIdjustificante (int id);

    String findNoTarjetaByNoEmpleado(int noEmpleado);

    int saveJustificanteTA(JustificanteTAModel justificanteTAModel, Justificante justificante);
    List<String> findZonas();
    TipoA findByJustificante(Justificante justificante);
}
