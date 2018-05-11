package com.is.controlincidencias.model;

import java.io.Serializable;

public class PermisoEconomicoModel implements Serializable {

    private int idjustificante;
    private String fechaIncidencia;

    public int getIdjustificante() {
        return idjustificante;
    }

    public void setIdjustificante(int idjustificante) {
        this.idjustificante = idjustificante;
    }

    public String getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(String fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    public PermisoEconomicoModel(int idjustificante, String fechaIncidencia) {
        this.idjustificante = idjustificante;
        this.fechaIncidencia = fechaIncidencia;
    }

    public PermisoEconomicoModel() {
    }

    @Override
    public String toString() {
        return "PermisoEconomicoModel{" +
                "idjustificante=" + idjustificante +
                ", fechaIncidencia='" + fechaIncidencia + '\'' +
                '}';
    }
}
