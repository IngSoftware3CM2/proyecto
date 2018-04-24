package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;

import java.util.List;

public interface JustificanteService {

    List <Justificante> getJustificantesByPersonal (Personal personal);

    Justificante findJustificanteById (int id);

    void removeJustificante (int id);
}
