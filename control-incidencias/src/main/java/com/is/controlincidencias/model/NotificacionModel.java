package com.is.controlincidencias.model;

import java.time.LocalDate;

public class NotificacionModel {
    private Integer id;
    private String motivo;
    private LocalDate fecha;
    private LocalDate archivo;
    private int idEmpleado;

    public NotificacionModel() {
    }

    @Override
    public String toString() {
        return "NotificacionModel{" +
                "id=" + id +
                ", motivo=" + motivo +
                ", fecha=" + fecha +
                ", archivo=" + archivo +
                ", idEmpleado=" + idEmpleado +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getArchivo() {
        return archivo;
    }

    public void setArchivo(LocalDate archivo) {
        this.archivo = archivo;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public NotificacionModel(Integer id, String motivo, LocalDate fecha, LocalDate archivo, int idEmpleado) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
        this.archivo = archivo;
        this.idEmpleado = idEmpleado;
    }
}
