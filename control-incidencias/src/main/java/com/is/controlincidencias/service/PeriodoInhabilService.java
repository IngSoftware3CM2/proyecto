package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.PeriodoInhabil;

public interface PeriodoInhabilService {
    PeriodoInhabil savePeriodoInhabil(PeriodoInhabil periodoInhabil);
    Integer findMaxIdPeriodo();
    void updatePeriodoInhabil(String nombreArchivo, int idPeriodo);
}
