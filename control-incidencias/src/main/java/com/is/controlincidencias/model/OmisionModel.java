package com.is.controlincidencias.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OmisionModel {
    @NotNull
    @Size(min = 10)
    private String justificacion;
    private boolean tipo;
    private int idJustificante;

    public OmisionModel() {
    }

    @Override
    public String toString() {
        return "OmisionModel{" +
                ", justificacion='" + justificacion + '\'' +
                ", tipo=" + tipo +
                ", idJustificante=" + idJustificante +
                '}';
    }

    public OmisionModel(@NotNull @Size(min = 10) String justificacion, String fechaIncidencia, boolean tipo, int idJustificante) {
        this.justificacion = justificacion;
        this.tipo = tipo;
        this.idJustificante = idJustificante;
    }


    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public int getIdJustificante() {
        return idJustificante;
    }

    public void setIdJustificante(int idJustificante) {
        this.idJustificante = idJustificante;
    }
}
