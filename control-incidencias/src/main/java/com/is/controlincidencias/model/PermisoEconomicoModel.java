package com.is.controlincidencias.model;

import java.io.Serializable;

public class PermisoEconomicoModel implements Serializable {

    private JustificanteModel justificante;

    public void setJustificante(JustificanteModel justificante) {
        this.justificante = justificante;
    }


    @Override
    public int hashCode() {
        return 33;
    }

    public PermisoEconomicoModel(){
    }

    public JustificanteModel getJustificante() {
        return justificante;
    }

    public PermisoEconomicoModel(JustificanteModel justificante) {
        this.justificante = justificante;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PermisoEconomicoModel)) return false;
        return justificante != null && justificante.equals(((PermisoEconomicoModel) obj).justificante);
    }


}
