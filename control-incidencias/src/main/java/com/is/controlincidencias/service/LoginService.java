package com.is.controlincidencias.service;

import com.is.controlincidencias.model.CredencialModel;

public interface LoginService {
    boolean validarAcceso(CredencialModel credencial);
}
