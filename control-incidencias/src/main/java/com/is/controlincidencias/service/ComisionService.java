package com.is.controlincidencias.service;

import com.is.controlincidencias.model.OmisionModel;

public interface ComisionService {

    int getLastJustificante();

    void addOmision(OmisionModel om, int id);
}
