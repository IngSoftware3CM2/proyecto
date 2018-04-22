package com.is.controlincidencias.model;

import java.util.ArrayList;
import java.util.List;


public class JustificanteModel {

    private Integer idJustificante;

    private List<PermisoEconomicoModel> permisosEconomicos = new ArrayList<>();

    public JustificanteModel(){}

    public JustificanteModel(Integer idJustificante, PersonalModel personal) {
        this.idJustificante = idJustificante;
        this.personal = personal;
    }

    public void addPermisoEconomico(PermisoEconomicoModel permisoeconomico) {
        permisosEconomicos.add(permisoeconomico);
        permisoeconomico.setJustificante(this);
    }

    public void removePermisoEconomico(PermisoEconomicoModel permisoeconomico) {
        permisosEconomicos.remove(permisoeconomico);
        permisoeconomico.setJustificante(null);
    }



    private List<LicPaternidadModel> licenciasPaternidad = new ArrayList<>();

    public void addLicPaternidad(LicPaternidadModel licpaternidad) {
        licenciasPaternidad.add(licpaternidad);
        licpaternidad.setJustificante(this);
    }

    public void removeLicPaternidad(LicPaternidadModel licpaternidad) {
        licenciasPaternidad.remove(licpaternidad);
        licpaternidad.setJustificante(null);
    }


    private List<IncidenciaModel> incidencias = new ArrayList<>();

    public void addAsistencia(IncidenciaModel incidencia) {
        incidencias.add(incidencia);
        incidencia.setJustificante(this);
    }

    public void removeAsistencia(IncidenciaModel incidencia) {
        incidencias.remove(incidencia);
        incidencia.setPersonal(null);
    }


    private static final String definition = "FOREIGN KEY(no_empleado) REFERENCES personal (no_empleado) ON UPDATE CASCADE ON DELETE CASCADE";

    private PersonalModel personal;

    public void setPersonal(PersonalModel personal) {
        this.personal = personal;
    }


    @Override
    public int hashCode() {
        return 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof JustificanteModel)) return false;
        return idJustificante != null && idJustificante.equals(((JustificanteModel) obj).idJustificante);
    }
}
