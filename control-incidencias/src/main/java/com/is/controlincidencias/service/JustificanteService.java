package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;

import java.util.List;

public interface JustificanteService {

    public abstract List <Justificante> getJustificantesByPersonal (Personal personal);

    public abstract Justificante findJustificanteById (int id);

    public abstract void removeJustificanteByIdJustificante (int id);

    public abstract void guardarJustificante(int noEmpleado);
}
