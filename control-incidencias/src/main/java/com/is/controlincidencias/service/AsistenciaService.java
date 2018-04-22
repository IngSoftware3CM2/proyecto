package com.is.controlincidencias.service;

import java.time.LocalDate;

public interface AsistenciaService {
    boolean buscarAsistencia(LocalDate fecha, int noTarjeta);
    boolean buscarTarjeta(int noTarjeta);
}
