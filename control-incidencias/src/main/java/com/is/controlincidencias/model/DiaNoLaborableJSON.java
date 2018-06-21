package com.is.controlincidencias.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class DiaNoLaborableJSON {
    private LocalDate fechaNH;
    private String justificacionNH;
    private ArrayList<Integer> listId;

    public LocalDate getFechaNH() {
        return fechaNH;
    }

    public void setFechaNH(LocalDate fechaNH) {
        this.fechaNH = fechaNH;
    }

    public String getJustificacionNH() {
        return justificacionNH;
    }

    public void setJustificacionNH(String justificacionNH) {
        this.justificacionNH = justificacionNH;
    }

    public ArrayList<Integer> getListId() {
        return listId;
    }

    public void setListId(ArrayList<Integer> listId) {
        this.listId = listId;
    }
}
