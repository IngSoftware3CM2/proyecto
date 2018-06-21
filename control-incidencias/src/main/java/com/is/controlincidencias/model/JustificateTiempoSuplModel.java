package com.is.controlincidencias.model;

import java.time.LocalDate;
import java.util.List;

public class JustificateTiempoSuplModel {

    private LocalDate fecha;
    private Integer tiempocubrir;
    private Integer idJustificante;
    private List<Integer> idSeleccionados;
    private List<Integer> horasSeleccionadas;

    public List<Integer> getIdSeleccionados() {
        return idSeleccionados;
    }

    public void setIdSeleccionados(List<Integer> idSeleccionados) {
        this.idSeleccionados = idSeleccionados;
    }

    public JustificateTiempoSuplModel(LocalDate fecha, Integer tiempocubrir, Integer idJustificante, List<Integer> idSeleccionados, List<Integer> horasSeleccionadas) {
        super();
        this.fecha = fecha;
        this.tiempocubrir = tiempocubrir;
        this.idJustificante = idJustificante;
        this.idSeleccionados = idSeleccionados;
        this.horasSeleccionadas = horasSeleccionadas;
    }

    public JustificateTiempoSuplModel() {
    }

    public List<Integer> getHorasSeleccionadas() {
        return horasSeleccionadas;
    }

    public void setHorasSeleccionadas(List<Integer> horasSeleccionadas) {
        this.horasSeleccionadas = horasSeleccionadas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getTiempocubrir() {
        return tiempocubrir;
    }

    public void setTiempocubrir(Integer tiempocubrir) {
        this.tiempocubrir = tiempocubrir;
    }

    public Integer getIdJustificante() {
        return idJustificante;
    }

    public void setIdJustificante(Integer idJustificante) {
        this.idJustificante = idJustificante;
    }
}


