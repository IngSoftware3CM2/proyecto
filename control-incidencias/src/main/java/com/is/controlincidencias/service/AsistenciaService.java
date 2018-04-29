package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.model.AsistenciaJSON;

import java.time.LocalDate;

public interface AsistenciaService {
    boolean buscarAsistencia(LocalDate fecha, int noTarjeta);
    boolean buscarTarjeta(int noTarjeta);
    void agregarAsistencia(AsistenciaJSON asistenciaJSON);
    int existeAsistencia(AsistenciaForm asistenciaForm);
    AsistenciaForm buscarAsistencia(AsistenciaForm asistenciaForm);
    Asistencia modificarAsistencia(AsistenciaForm asistenciaForm);
}
