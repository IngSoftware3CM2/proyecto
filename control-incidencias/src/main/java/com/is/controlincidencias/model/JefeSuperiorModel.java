package com.is.controlincidencias.model;

import java.util.ArrayList;
import java.util.List;

public class JefeSuperiorModel {
    public Integer getId_superior() {
        return id_superior;
    }

    public void setId_superior(Integer id_superior) {
        this.id_superior = id_superior;
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

    public List<DepartamentoModel> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentoModel> departamentos) {
        this.departamentos = departamentos;
    }

    public JefeSuperiorModel getJefe() {
        return jefe;
    }

    public void setJefe(JefeSuperiorModel jefe) {
        this.jefe = jefe;
    }

    public List<JefeSuperiorModel> getSubordinado() {
        return subordinado;
    }

    public void setSubordinado(List<JefeSuperiorModel> subordinado) {
        this.subordinado = subordinado;
    }

    public JefeSuperiorModel(){}

    public JefeSuperiorModel(Integer id_superior, String nombre, String apellidoPaterno, String apellidoMaterno, JefeSuperiorModel jefe) {
        this.id_superior = id_superior;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.jefe = jefe;
    }

    private Integer id_superior;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private List<DepartamentoModel> departamentos = new ArrayList<>();

    public void addDepartamento(DepartamentoModel departamento) {
        departamentos.add(departamento);
        departamento.setJefeSuperior(this);
    }

    public void removeDepartamento(DepartamentoModel departamento) {
        departamentos.remove(departamento);
        departamento.setJefeSuperior(null);
    }

    private JefeSuperiorModel jefe;

    public JefeSuperiorModel getJefeSuperior() {
        return jefe;
    }

    public void setJefeSuperior(JefeSuperiorModel jefeSuperior) {
        this.jefe = jefeSuperior;
    }


    private List<JefeSuperiorModel> subordinado = new ArrayList<>();

    public void addSubordinado(JefeSuperiorModel jefesuperior) {
        subordinado.add(jefesuperior);
        jefesuperior.setJefeSuperior(this);
    }

    public void removeSubordinado(JefeSuperiorModel jefesuperior) {
        subordinado.remove(jefesuperior);
        jefesuperior.setJefeSuperior(null);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof JefeSuperiorModel)) return false;
        return id_superior != null && id_superior.equals(((JefeSuperiorModel) obj).id_superior);
    }
}
