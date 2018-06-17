package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.TiempoSuplGenerado;

import java.time.LocalDate;
import java.util.List;

public interface TiempoSuplGeneradoService{
    List<TiempoSuplGenerado> findByPersonal(int idEmpleado, LocalDate fecha);
    TiempoSuplGenerado findById(Integer id);
    int updatetiempoUsados(int idTiempoSupl);
}
