package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.JustificanteTAModel;

import java.util.List;

public interface JustificanteTAService {
    String findNoTarjetaByNoEmpleado(int noEmpleado);
    List <String> findByIdJustificante (int id);

    void saveJustificanteTA(JustificanteTAModel justificanteTAModel, Justificante justificante);
    List<String> findZonas();
}
