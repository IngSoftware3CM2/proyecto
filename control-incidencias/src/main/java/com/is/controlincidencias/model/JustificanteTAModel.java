package com.is.controlincidencias.model;



import java.time.LocalDate;

public class JustificanteTAModel {
    private String folio;
    private LocalDate inicio;
    private LocalDate fin;
    private String licenciaArchivo;
    private String tipo;
    private int idjustificante;
    private String unidadmedica;

    public JustificanteTAModel() {
    }

    public JustificanteTAModel(String folio, LocalDate inicio, LocalDate fin, String licenciaArchivo, String tipo, int idjustificante, String unidadmedica) {
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

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
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
