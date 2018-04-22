package com.is.controlincidencias.service;

import com.is.controlincidencias.models.AsistenciaJSON;

import java.time.LocalDate;

public interface AsistenciaService {
    boolean buscarAsistencia(LocalDate fecha, int noTarjeta);
    boolean buscarTarjeta(int noTarjeta);
    void agregarAsistencia(AsistenciaJSON asistenciaJSON);
}
