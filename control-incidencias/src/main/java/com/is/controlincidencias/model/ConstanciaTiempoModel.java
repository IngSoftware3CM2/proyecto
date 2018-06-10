package com.is.controlincidencias.model;

import com.is.controlincidencias.entity.Justificante;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

public class ConstanciaTiempoModel {
    private Integer id;

    private String constanciaArchivo;

    private LocalDate segfecha;

    private String tipo;

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";
    private Justificante justificante;

    public static String getDEFINITION() {
        return DEFINITION;
    }

    public ConstanciaTiempoModel() {
    }

    public ConstanciaTiempoModel(Integer id, String constanciaArchivo, LocalDate segfecha, String tipo) {
        this.constanciaArchivo = constanciaArchivo;
        this.segfecha = segfecha;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConstanciaArchivo() {
        return constanciaArchivo;
    }

    public void setConstanciaArchivo(String constanciaArchivo) {
        this.constanciaArchivo = constanciaArchivo;
    }

    public LocalDate getSegfecha() {
        return segfecha;
    }

    public void setSegfecha(LocalDate segfecha) {
        this.segfecha = segfecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Override
    public String toString() {
        return "ConstanciaTiempoModel{" +
                "id=" + id +
                ", constanciaArchivo='" + constanciaArchivo + '\'' +
                ", segfecha=" + segfecha +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getConstanciaArchivo(), getSegfecha(), getTipo(), getJustificante());
    }
}