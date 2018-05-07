package com.is.controlincidencias.model;


import java.util.ArrayList;
import java.util.List;

public class PersonalForm {
    private int departamento;
    private int numeroEmpleado;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private boolean abierto;
    private int horas;
    private List<DiaForm> dias = new ArrayList<>();
    // Falta sexo y turno

    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public int getHoras() {
        return horas;
    }

    public void addDia(DiaForm dia) {
        dias.add(dia);
    }

    public List<DiaForm> getDias() {
        return dias;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
