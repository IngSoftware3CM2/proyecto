package com.is.controlincidencias.service;

import com.is.controlincidencias.model.AsistenciaJSON;

import java.time.LocalDate;

public interface AsistenciaService {
    boolean buscarAsistencia(LocalDate fecha, int noTarjeta);
    boolean buscarTarjeta(int noTarjeta);
    void agregarAsistencia(AsistenciaJSON asistenciaJSON);
}
