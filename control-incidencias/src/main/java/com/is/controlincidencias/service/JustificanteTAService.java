package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.JustificanteTAModel;

import java.util.List;

public interface JustificanteTAService {
<<<<<<< HEAD
    public abstract String findNoTarjetaByNoEmpleado (int noEmpleado);
    boolean existsByIdjustificante (int id);
=======
    String findNoTarjetaByNoEmpleado(int noEmpleado);
    List <String> findByIdJustificante (int id);
>>>>>>> 3d953574cdf98648a9683806527d4603b8a52c0b

    void saveJustificanteTA(JustificanteTAModel justificanteTAModel, Justificante justificante);
    List<String> findZonas();
}
