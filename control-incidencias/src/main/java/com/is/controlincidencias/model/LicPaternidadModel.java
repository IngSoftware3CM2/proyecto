package com.is.controlincidencias.model;


import java.io.Serializable;


public class LicPaternidadModel implements Serializable{

    public LicPaternidadModel(){}

    public JustificanteModel getJustificante() {
        return justificante;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getRegistrolicencia() {
        return registrolicencia;
    }

    public void setRegistrolicencia(String registrolicencia) {
        this.registrolicencia = registrolicencia;
    }

    public String getActanacimiento() {
        return actanacimiento;
    }

    public void setActanacimiento(String actanacimiento) {
        this.actanacimiento = actanacimiento;
    }

    public String getActamatrimonio() {
        return actamatrimonio;
    }

    public void setActamatrimonio(String actamatrimonio) {
        this.actamatrimonio = actamatrimonio;
    }

    public String getConstanciacurso() {
        return constanciacurso;
    }

    public void setConstanciacurso(String constanciacurso) {
        this.constanciacurso = constanciacurso;
    }

    public String getComprobanteingresos() {
        return comprobanteingresos;
    }

    public void setComprobanteingresos(String comprobanteingresos) {
        this.comprobanteingresos = comprobanteingresos;
    }

    public String getCopiaidentificacion() {
        return copiaidentificacion;
    }

    public void setCopiaidentificacion(String copiaidentificacion) {
        this.copiaidentificacion = copiaidentificacion;
    }

    public LicPaternidadModel(JustificanteModel justificante, String justificacion, String registrolicencia, String actanacimiento, String actamatrimonio, String constanciacurso, String comprobanteingresos, String copiaidentificacion) {
        this.justificante = justificante;
        this.justificacion = justificacion;
        this.registrolicencia = registrolicencia;
        this.actanacimiento = actanacimiento;
        this.actamatrimonio = actamatrimonio;
        this.constanciacurso = constanciacurso;
        this.comprobanteingresos = comprobanteingresos;
        this.copiaidentificacion = copiaidentificacion;
    }

    @Override
    public String toString() {
        return "LicPaternidadModel{" +
                "justificante=" + justificante +
                ", justificacion='" + justificacion + '\'' +
                ", registrolicencia='" + registrolicencia + '\'' +
                ", actanacimiento='" + actanacimiento + '\'' +
                ", actamatrimonio='" + actamatrimonio + '\'' +
                ", constanciacurso='" + constanciacurso + '\'' +
                ", comprobanteingresos='" + comprobanteingresos + '\'' +
                ", copiaidentificacion='" + copiaidentificacion + '\'' +
                '}';
    }

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
