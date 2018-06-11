package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.model.AsistenciaJSON;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaService {
    boolean buscarAsistencia(LocalDate fecha, String noTarjeta);
    boolean buscarTarjeta(String noTarjeta);
    Asistencia agregarAsistencia(AsistenciaForm asistenciaForm);
    int existeAsistencia(AsistenciaForm asistenciaForm);
    AsistenciaForm buscarAsistencia(AsistenciaForm asistenciaForm);
    Asistencia modificarAsistencia(AsistenciaForm asistenciaForm);
    List<AsistenciaJSON> obtenerAsistencias(LocalDate fecha);
    void eliminarAsistenciaPorId(Integer id);
}
