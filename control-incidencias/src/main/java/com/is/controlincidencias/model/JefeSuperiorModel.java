package com.is.controlincidencias.model;

import java.util.ArrayList;
import java.util.List;

public class JefeSuperiorModel {

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

    private static final String definition = "FOREIGN KEY (id_superior) REFERENCES  jefe_superior (id_superior) ON UPDATE CASCADE ON DELETE CASCADE";
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
