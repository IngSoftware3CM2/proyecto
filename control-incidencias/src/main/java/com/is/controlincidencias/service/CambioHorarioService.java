package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.model.CambioHorarioModel;

public interface CambioHorarioService {
    void insertaCambioHorario(CambioHorarioModel cambioHorario);
    CambioHorario getSolicitudCambioHorario();
}
