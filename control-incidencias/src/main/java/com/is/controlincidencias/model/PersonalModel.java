package com.is.controlincidencias.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalModel {

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

    private static final String definition = "FOREIGN KEY(id_departamento) REFERENCES departamento (id_departamento) ON UPDATE CASCADE ON DELETE CASCADE";

    private DepartamentoModel departamento;

    public DepartamentoModel getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoModel departamento) {
        this.departamento = departamento;
    }


    private String tipo;

}
