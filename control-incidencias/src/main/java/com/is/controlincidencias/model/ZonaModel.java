package com.is.controlincidencias.model;

public class ZonaModel {
    private String idZona;
    private String nombre;

    public ZonaModel(String idZona, String nombre) {
        this.idZona = idZona;
        this.nombre = nombre;
    }


    public ZonaModel() {

    }

    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
