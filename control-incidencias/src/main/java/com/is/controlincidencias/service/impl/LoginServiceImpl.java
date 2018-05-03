package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.model.CredencialModel;
import com.is.controlincidencias.service.LoginService;
import org.springframework.stereotype.Service;

@Service("loginServiceImpl")

public class LoginServiceImpl implements LoginService {


    @Override
    public boolean validarAcceso(CredencialModel credencial) {
        return false;
    }
}
