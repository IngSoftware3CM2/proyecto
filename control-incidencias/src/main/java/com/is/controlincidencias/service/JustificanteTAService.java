package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.TipoA;
import com.is.controlincidencias.model.JustificanteTAModel;

import java.util.List;
import java.util.Optional;

public interface JustificanteTAService {
    public abstract String findNoTarjetaByNoEmpleado (int noEmpleado);
    boolean existsByIdjustificante (int id);

    public abstract void saveJustificanteTA(JustificanteTAModel justificanteTAModel, Justificante justificante);
    public abstract List<String> findZonas();
}
