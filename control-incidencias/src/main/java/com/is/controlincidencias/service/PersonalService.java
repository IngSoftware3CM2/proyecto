package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Personal;

public interface PersonalService {
    Personal getPersonalByNoEmpleado (int noEmpleado);
    Personal getPersonalByEmail(String email);
    Personal getPersonalByEmailAndPwd(String email, String pwd);
    void actualizarContra(Personal personal);
}
