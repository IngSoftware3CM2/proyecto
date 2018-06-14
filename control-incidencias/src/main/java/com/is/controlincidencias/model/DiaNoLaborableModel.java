package com.is.controlincidencias.model;

import java.time.LocalDate;
import java.util.List;

public class DiaNoLaborableModel {
    private LocalDate fecha;
    private String justificacion;
    List<Integer> idPersonal;
    private String archivo;


    public DiaNoLaborableModel() {
    }

    public DiaNoLaborableModel(LocalDate fecha, String justificacion, List<Integer> idPersonal, String archivo) {
        super();
        this.fecha = fecha;
        this.justificacion = justificacion;
        this.idPersonal = idPersonal;
        this.archivo = archivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public List<Integer> getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(List<Integer> idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
}
