package com.is.controlincidencias.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class PeriodoVacacionalJSON {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private ArrayList<Integer> listId;

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ArrayList<Integer> getListId() {
        return listId;
    }

    public void setListId(ArrayList<Integer> listId) {
        this.listId = listId;
    }
}
