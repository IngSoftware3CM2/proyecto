package com.is.controlincidencias.model;


import java.io.Serializable;


public class LicPaternidadModel implements Serializable{

    private static final String definition = "FOREIGN KEY(id_justificante) REFERENCES justificante (id_justificante) ON UPDATE CASCADE ON DELETE CASCADE";
    private JustificanteModel justificante;

    public void setJustificante(JustificanteModel justificante) {
        this.justificante = justificante;
    }

    private String justificacion;

    private String registrolicencia;

    private String actanacimiento;

    private String actamatrimonio;

    private String constanciacurso;

    private String comprobanteingresos;

    private String copiaidentificacion;


    @Override
    public int hashCode() {
        return 34;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PermisoEconomicoModel)) return false;
        return justificante != null && justificante.equals(((LicPaternidadModel) obj).justificante);
    }
}
