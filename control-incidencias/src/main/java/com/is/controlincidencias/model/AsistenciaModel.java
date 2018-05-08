package com.is.controlincidencias.model;


import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaModel {

    public AsistenciaModel(){}

    public AsistenciaModel(Integer id, LocalDate fechaRegistro, LocalTime horaEntrada, LocalTime horaSalida, PersonalModel personal) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.personal = personal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    private Integer id;

    private LocalDate fechaRegistro;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;


    private PersonalModel personal;

    public PersonalModel getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalModel personal) {
        this.personal = personal;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AsistenciaModel)) return false;
        return id != null && id.equals(((AsistenciaModel) obj).id);
    }
}
