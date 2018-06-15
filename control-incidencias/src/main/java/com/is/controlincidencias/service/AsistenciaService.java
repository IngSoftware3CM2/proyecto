package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.model.AsistenciaForm;
import com.is.controlincidencias.model.AsistenciaJSON;
import com.is.controlincidencias.model.AsistenciaMostrar;

import java.time.LocalDate;
import java.util.List;

public interface AsistenciaService {
    Asistencia agregarAsistencia(AsistenciaForm asistenciaForm);
    int existeAsistencia(AsistenciaForm asistenciaForm);
    AsistenciaForm buscarAsistencia(AsistenciaForm asistenciaForm);
    Asistencia modificarAsistencia(AsistenciaForm asistenciaForm);
    List<AsistenciaJSON> obtenerAsistencias(LocalDate fecha);
    void eliminarAsistenciaPorId(Integer id);
    List<String> obtenerAniosPorTarjeta(String tarjeta);
    List<String> obtenerQuincenas(AsistenciaMostrar asistenciaMostrar);
}
