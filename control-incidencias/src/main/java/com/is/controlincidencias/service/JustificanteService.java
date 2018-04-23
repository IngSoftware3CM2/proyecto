package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;

import java.util.List;

public interface JustificanteService {

    List <Justificante> listAllJustificante ();

    Justificante findJustificanteById (int id);

    void removeJustificante (int id);
}
