package com.is.controlincidencias.model;


import java.util.ArrayList;
import java.util.List;

public class DepartamentoModel {

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


    private static final String definition2 = "FOREIGN KEY(id_superior) REFERENCES jefe_superior (id_superior) ON UPDATE CASCADE ON DELETE CASCADE";

    private JefeSuperiorModel jefesuperior;

    public void setJefeSuperior(JefeSuperiorModel jefesuperior) {
        this.jefesuperior =jefesuperior;
    }
}
