package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.model.CambioHorarioModel;

import java.util.Date;

public interface CambioHorarioService {
    void insertaCambioHorario(CambioHorarioModel cambioHorario, int id);

    CambioHorario getIdCambioHorario(int id);

    void updateCambioHorario(CambioHorarioModel chm);

    int getIdEmpleadoByIdIncidencia(int id);

    int getIdJustificanteByIdEmpleado(int id);

    boolean existsByIdjustificante (int id);

    String getHoraEntrada(int id, String fecha);

    String getHoraSalida(int id, String fecha);

    String getHoraS(int id, String dia);

    String getHoraE(int id, String dia);
}
