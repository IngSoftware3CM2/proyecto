package com.is.controlincidencias.model;

public class CambioHorarioModel {
    private String horaEntrada;
    private String horaSalida;
    private String nuevaEntrada;
    private String nuevaSalida;
    private String justificación;
    private String fechaIncidencia;

    public String getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(String fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    public CambioHorarioModel(String horaEntrada, String horaSalida, String nuevaEntrada, String nuevaSalida, String justificación, String fechaIncidencia) {
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.nuevaEntrada = nuevaEntrada;
        this.nuevaSalida = nuevaSalida;
        this.justificación = justificación;
        this.fechaIncidencia = fechaIncidencia;

    }

    public CambioHorarioModel() {
    }

    @Override
    public String toString() {
        return "CambioHorarioModel{" +
                "horaEntrada='" + horaEntrada + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", nuevaEntrada='" + nuevaEntrada + '\'' +
                ", nuevaSalida='" + nuevaSalida + '\'' +
                ", justificación='" + justificación + '\'' +
                ", fechaIncidencia='" + fechaIncidencia + '\'' +
                '}';
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

    public String getNuevaEntrada() {
        return nuevaEntrada;
    }

    public void setNuevaEntrada(String nuevaEntrada) {
        this.nuevaEntrada = nuevaEntrada;
    }

    public String getNuevaSalida() {
        return nuevaSalida;
    }

    public void setNuevaSalida(String nuevaSalida) {
        this.nuevaSalida = nuevaSalida;
    }

    public String getJustificación() {
        return justificación;
    }

    public void setJustificación(String justificación) {
        this.justificación = justificación;
    }
}
