package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.model.AsistenciaJSON;

import java.time.LocalDate;

public interface AsistenciaService {
    boolean buscarAsistencia(LocalDate fecha, String noTarjeta);
    boolean buscarTarjeta(String noTarjeta);
    void agregarAsistencia(AsistenciaJSON asistenciaJSON);
    int existeAsistencia(AsistenciaForm asistenciaForm);
    AsistenciaForm buscarAsistencia(AsistenciaForm asistenciaForm);
    Asistencia modificarAsistencia(AsistenciaForm asistenciaForm);
}
