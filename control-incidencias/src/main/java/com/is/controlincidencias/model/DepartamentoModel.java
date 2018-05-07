package com.is.controlincidencias.model;


import java.util.ArrayList;
import java.util.List;

public class DepartamentoModel {

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<PersonalModel> getPersonales() {
        return personales;
    }

    public void setPersonales(List<PersonalModel> personales) {
        this.personales = personales;
    }


    public JefeSuperiorModel getJefesuperior() {
        return jefesuperior;
    }

    public void setJefesuperior(JefeSuperiorModel jefesuperior) {
        this.jefesuperior = jefesuperior;
    }

    public DepartamentoModel(){}

    public DepartamentoModel(Integer idDepartamento, String nombre,  JefeSuperiorModel jefesuperior) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.jefesuperior = jefesuperior;
    }

    private Integer idDepartamento;

    private String nombre;

    private List<PersonalModel> personales = new ArrayList<>();

    public void addPersonal(PersonalModel personal) {
        personales.add(personal);
        personal.setDepartamento(this);
    }

    public void removePersonal(PersonalModel personal) {
        personales.remove(personal);
        personal.setDepartamento(null);
    }


    private JefeSuperiorModel jefesuperior;

}
