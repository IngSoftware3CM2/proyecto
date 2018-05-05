package com.is.controlincidencias.model;

public class CambioHorarioModel {
    private String horaEntrada;
    private String horaSalida;
    private String nuevaEntrada;
    private String nuevaSalida;
    private String justificacion;
    private String fechaIncidencia;
    private int idJustificante;


    public CambioHorarioModel(String horaEntrada, String horaSalida, String nuevaEntrada, String nuevaSalida, String justificacion, String fechaIncidencia, int idJustificante) {
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.nuevaEntrada = nuevaEntrada;
        this.nuevaSalida = nuevaSalida;
        this.justificacion = justificacion;
        this.fechaIncidencia = fechaIncidencia;
        this.idJustificante = idJustificante;
    }

    @Override
    public String toString() {
        return "CambioHorarioModel{" +
                "horaEntrada='" + horaEntrada + '\'' +
                ", horaSalida='" + horaSalida + '\'' +
                ", nuevaEntrada='" + nuevaEntrada + '\'' +
                ", nuevaSalida='" + nuevaSalida + '\'' +
                ", justificación='" + justificacion + '\'' +
                ", fechaIncidencia='" + fechaIncidencia + '\'' +
                ", idJustificante=" + idJustificante +
                '}';
    }

    public int getIdJustificante() {
        return idJustificante;
    }

    public void setIdJustificante(int idJustificante) {
        this.idJustificante = idJustificante;
    }


    public String getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(String fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }


    public CambioHorarioModel() {
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

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }
}
