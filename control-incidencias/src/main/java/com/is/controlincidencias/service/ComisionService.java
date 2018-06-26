package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.ComisionOficial;
import com.is.controlincidencias.model.ComisionModel;


public interface ComisionService {

    int getLastJustificante();

    void addComision(ComisionModel com, int id);

    boolean existsByIdjustificante(int id);

    ComisionOficial getCO(int id);
}
