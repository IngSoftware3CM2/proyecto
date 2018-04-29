package com.is.controlincidencias.model;

import org.springframework.format.annotation.DateTimeFormat;

public class AsistenciaForm {
    private int tarjeta;
    private String horaEntrada;
    private String horaSalida;
    private String fecha;

    public int getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(int tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "AsistenciaForm{" +
                "tarjeta=" + tarjeta +
                ", horaEntrada='" + horaEntrada + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
