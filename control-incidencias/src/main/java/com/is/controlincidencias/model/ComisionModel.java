package com.is.controlincidencias.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class ComisionModel {

    private int idComision;

    private String inicio;

    private String fin;

    private String invitacionArchivo;

    public ComisionModel() {
    }

    public ComisionModel(int idComision, String inicio, String fin, String invitacionArchivo) {
        this.idComision = idComision;
        this.inicio = inicio;
        this.fin = fin;
        this.invitacionArchivo = invitacionArchivo;
    }

    public int getIdComision() {
        return idComision;
    }

    public void setIdComision(int idComision) {
        this.idComision = idComision;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getInvitacionArchivo() {
        return invitacionArchivo;
    }

    public void setInvitacionArchivo(String invitacionArchivo) {
        this.invitacionArchivo = invitacionArchivo;
    }

    @Override
    public String toString() {
        return "ComisionModel{" +
                "idComision=" + idComision +
                ", inicio='" + inicio + '\'' +
                ", fin='" + fin + '\'' +
                ", invitacionArchivo='" + invitacionArchivo + '\'' +
                '}';
    }
}
