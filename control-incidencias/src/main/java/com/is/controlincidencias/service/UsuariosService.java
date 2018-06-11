package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.DepartamentoModel;
import com.is.controlincidencias.model.DocenteModel;
import com.is.controlincidencias.model.PaaeModel;

import java.util.List;

public interface UsuariosService {
    List<DepartamentoModel> recuperarDepartamentos(int tipo);
    Personal registarPaee(PaaeModel modelo);
    Personal registrarDocente(DocenteModel modelo);
}
