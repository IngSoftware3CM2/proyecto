package com.is.controlincidencias.service;

import com.is.controlincidencias.model.RetardoModel;

public interface RetardoService {

    void addRetardo(RetardoModel m, int id, String f);

    String getJust(int idJust);

    String getFecha(int id);

    void updateJust(String j, int id);

    boolean existsByIdjustificante (int id);

}
