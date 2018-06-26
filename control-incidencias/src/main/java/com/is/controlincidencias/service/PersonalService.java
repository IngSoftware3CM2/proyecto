package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Personal;

import java.util.List;

public interface PersonalService {
    Personal getPersonalByIdEmpleado (int idEmpleado);
    Personal getPersonalByEmail(String email);
    Personal getPersonalByEmailAndPwd(String email, String pwd);
    void actualizarContra(Personal personal);
    List<Personal> getPersonalByTipo(String tipo);

    Personal getPersonalByIdJustificante(Integer id);
}
