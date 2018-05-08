package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.model.CambioHorarioModel;

public interface CambioHorarioService {
    void insertaCambioHorario(CambioHorarioModel cambioHorario);
    CambioHorario getIdCambioHorario(int id);
    void updateCambioHorario(CambioHorarioModel chm);
    boolean existsByIdjustificante (int id);
}
