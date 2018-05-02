package com.is.controlincidencias.model;


public class JustificanteTAModel {
    private String folio;
    private String inicio;
    private String fin;
    private String licenciaArchivo;
    private String tipo;
    private int idjustificante;
    private String unidadmedica;

    public JustificanteTAModel() {
    }

    public JustificanteTAModel(String folio, String inicio, String fin, String licenciaArchivo, String tipo, int idjustificante, String unidadmedica) {
        super();
        this.folio = folio;
        this.inicio = inicio;
        this.fin = fin;
        this.licenciaArchivo = licenciaArchivo;
        this.tipo = tipo;
        this.idjustificante = idjustificante;
        this.unidadmedica = unidadmedica;
    }


    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
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

    public String getLicenciaArchivo() {
        return licenciaArchivo;
    }

    public void setLicenciaArchivo(String licenciaArchivo) {
        this.licenciaArchivo = licenciaArchivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdjustificante() {
        return idjustificante;
    }

    public void setIdjustificante(int idjustificante) {
        this.idjustificante = idjustificante;
    }

    public String getIdunidadmedica() {
        return unidadmedica;
    }

    public void setIdunidadmedica(String unidadmedica) {
        this.unidadmedica = unidadmedica;
    }

    @Override
    public String toString() {
        return "JustificanteTAModel{" +
                "folio='" + folio + '\'' +
                ", inicio=" + inicio +
                ", fin=" + fin +
                ", licenciaArchivo='" + licenciaArchivo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idjustificante=" + idjustificante +
                ", idunidadmedica=" + unidadmedica +
                '}';
    }

}
