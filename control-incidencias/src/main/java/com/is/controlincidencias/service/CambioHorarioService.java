package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.model.CambioHorarioModel;

public interface CambioHorarioService {
    void insertaCambioHorario(CambioHorarioModel cambioHorario, int id);

    CambioHorario getIdCambioHorario(int id);

    void updateCambioHorario(CambioHorarioModel chm);

    int getIdEmpleadoByIdIncidencia(int id);

    int getIdJustificanteByIdEmpleado(int id);

    boolean existsByIdjustificante (int id);

}
