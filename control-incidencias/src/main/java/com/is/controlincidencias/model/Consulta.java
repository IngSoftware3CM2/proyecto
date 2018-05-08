package com.is.controlincidencias.model;

import java.time.LocalDate;

public class Consulta {
    private int estado;
    private int noTarjeta;
    private LocalDate fechaRegistro;

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(int noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
