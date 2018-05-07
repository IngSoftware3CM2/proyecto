package com.is.controlincidencias.model;


import java.util.ArrayList;
import java.util.List;

public class PersonalModel {
    public PersonalModel(){}

    public Integer getNoEmpleado() {
        return noEmpleado;
    }

    public void setNoEmpleado(Integer noEmpleado) {
        this.noEmpleado = noEmpleado;
    }

    public Integer getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(Integer noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public PersonalModel(Integer noEmpleado, Integer noTarjeta, String nombre, String apellidoPaterno, String apellidoMaterno, DepartamentoModel departamento, String tipo) {
        this.noEmpleado = noEmpleado;
        this.noTarjeta = noTarjeta;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.departamento = departamento;
        this.tipo = tipo;
    }

    private Integer noEmpleado;

    private Integer noTarjeta;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private List<AsistenciaModel> asistencias = new ArrayList<>();

    public void addAsistencia(AsistenciaModel asistencia) {
        asistencias.add(asistencia);
        asistencia.setPersonal(this);
    }

    public void removeAsistencia(AsistenciaModel asistencia) {
        asistencias.remove(asistencia);
        asistencia.setPersonal(null);
    }




    private List<IncidenciaModel> incidencias = new ArrayList<>();

    public void addIncidencia(IncidenciaModel incidencia) {
        incidencias.add(incidencia);
        incidencia.setPersonal(this);
    }

    public void removeIncidencia(IncidenciaModel incidencia) {
        incidencias.remove(incidencia);
        incidencia.setPersonal(null);
    }


    private List<JustificanteModel> justificantes = new ArrayList<>();

    public void addJustificante(JustificanteModel justificante) {
        justificantes.add(justificante);
        justificante.setPersonal(this);
    }

    public void removeJustificante(JustificanteModel justificante) {
        justificantes.remove(justificante);
        justificante.setPersonal(null);
    }


    private DepartamentoModel departamento;

    public DepartamentoModel getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoModel departamento) {
        this.departamento = departamento;
    }


    private String tipo;

}
