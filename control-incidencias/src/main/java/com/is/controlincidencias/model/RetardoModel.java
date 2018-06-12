package com.is.controlincidencias.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RetardoModel {
    @NotNull
    @Size(min = 10)
    private String justificacion;
    private int idJustificante;

    public RetardoModel() {
    }

    @Override
    public String toString() {
        return "OmisionModel{" +
                " justificacion='" + justificacion + '\'' +
                ", idJustificante=" + idJustificante +
                '}';
    }

    public RetardoModel(@NotNull @Size(min = 10) String justificacion, int idJustificante) {
        this.justificacion = justificacion;
        this.idJustificante = idJustificante;
    }


    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public int getIdJustificante() {
        return idJustificante;
    }

    public void setIdJustificante(int idJustificante) {
        this.idJustificante = idJustificante;
    }
}
