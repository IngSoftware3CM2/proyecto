package com.is.controlincidencias.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaJSON {
    private int noTarjeta;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private LocalDate fechaRegistro;

    public AsistenciaJSON() {
    }

    public int getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(int noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
