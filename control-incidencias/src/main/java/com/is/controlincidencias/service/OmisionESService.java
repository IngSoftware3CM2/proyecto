package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.OmisionEntrSal;
import com.is.controlincidencias.model.OmisionModel;

public interface OmisionESService {

    void addOmision(OmisionModel m, int id, String f);

    String getJust(int idJust);

    String getFecha(int id);

    void updateJust(String j, int id);



    boolean existsByIdjustificante (int id);

    OmisionEntrSal getOmisionByIdJustificante(Integer idJustificante);
}
