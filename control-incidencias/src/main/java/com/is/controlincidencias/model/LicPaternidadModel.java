package com.is.controlincidencias.model;

import java.io.Serializable;


public class LicPaternidadModel implements Serializable{

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

    @Override
    public String toString() {
        return "LicPaternidadModel{" +
                ", justificacion='" + justificacion + '\'' +
                ", registrolicencia='" + registrolicencia + '\'' +
                ", actanacimiento='" + actanacimiento + '\'' +
                ", actamatrimonio='" + actamatrimonio + '\'' +
                ", constanciacurso='" + constanciacurso + '\'' +
                ", comprobanteingresos='" + comprobanteingresos + '\'' +
                ", copiaidentificacion='" + copiaidentificacion + '\'' +
                '}';
    }

    private String justificacion;

    private String registrolicencia;

    private String actanacimiento;

    private String actamatrimonio;

    private String constanciacurso;

    private String comprobanteingresos;

    private String copiaidentificacion;
}
