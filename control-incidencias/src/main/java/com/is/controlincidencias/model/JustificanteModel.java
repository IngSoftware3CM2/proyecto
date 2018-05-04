package com.is.controlincidencias.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JustificanteModel {

    public void setFecha(LocalTime fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public PermisoEconomicoModel getPermisoEconomicoModel() {
        return permisoEconomicoModel;
    }

    public void setPermisoEconomicoModel(PermisoEconomicoModel permisoEconomicoModel) {
        this.permisoEconomicoModel = permisoEconomicoModel;
    }

    public LicPaternidadModel getLicPaternidadModel() {
        return licPaternidadModel;
    }

    public void setLicPaternidadModel(LicPaternidadModel licPaternidadModel) {
        this.licPaternidadModel = licPaternidadModel;
    }

    public List<IncidenciaModel> getIncidenciasModel() {
        return incidenciasModel;
    }

    public void setIncidenciasModel(List<IncidenciaModel> incidenciasModel) {
        this.incidenciasModel = incidenciasModel;
    }

    public static String getDefinition() {
        return definition;
    }

    public PersonalModel getPersonalModel() {
        return personalModel;
    }

    public void setPersonalModel(PersonalModel personalModel) {
        this.personalModel = personalModel;
    }

    public JustificanteModel(Integer idJustificante, LocalTime fecha, String estado, List<IncidenciaModel> incidenciasModel, PersonalModel personalModel) {
        this.idJustificante = idJustificante;
        this.fecha = fecha;
        this.estado = estado;
        this.incidenciasModel = incidenciasModel;
        this.personalModel = personalModel;
    }

    private Integer idJustificante;

    private LocalTime fecha;

    private String estado;               //this attrib  can be "Aceptado", "En proceso", "Rechazado"

    private PermisoEconomicoModel permisoEconomicoModel;

    private LicPaternidadModel licPaternidadModel;

    private List<IncidenciaModel> incidenciasModel = new ArrayList<>();

    public void addAsistencia(IncidenciaModel incidenciaModel) {
        incidenciasModel.add(incidenciaModel);
        //incidenciaModel.setJustificante(this);
    }

    public void removeAsistencia(IncidenciaModel incidencia) {
        incidenciasModel.remove(incidencia);
        incidencia.setPersonal(null);
    }


    private static final String definition = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    private PersonalModel personalModel;

    public void setPersonal(PersonalModel personalModel) {
        this.personalModel = personalModel;
    }




    public Integer getIdJustificante() {
        return idJustificante;
    }

    public JustificanteModel() {
    }

    public void setIdJustificante(Integer idJustificante) {
        this.idJustificante = idJustificante;

    }

    public PermisoEconomicoModel getPermisoEconomico() {
        return this.permisoEconomicoModel;
    }

    public void setPermisoEconomico(PermisoEconomicoModel permisoEconomicoModel) {
        this.permisoEconomicoModel = permisoEconomicoModel;
    }

    public LicPaternidadModel getLicenciaPaternidad() {
        return licPaternidadModel;
    }

    public void setLicenciaPaternidad(LicPaternidadModel licPaternidad) {
        this.licPaternidadModel = licPaternidad;
    }

    public List<IncidenciaModel> getIncidencias() {
        return incidenciasModel;
    }

    public void setIncidencias(List<IncidenciaModel> incidencias) {
        this.incidenciasModel = incidencias;
    }

    public PersonalModel getPersonal() {
        return personalModel;
    }

    @Override
    public int hashCode() {
        return 32;
    }

    @Override
    public String toString() {
        return "Justificante{" +
                "idJustificante=" + idJustificante +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", permisoEconomico=" + permisoEconomicoModel +
                ", licPaternidad=" + licPaternidadModel +
                ", incidencias=" + incidenciasModel +
                ", personal=" + personalModel +
                '}';
    }

    public JustificanteModel(Integer idJustificante, PersonalModel personal) {
        this.idJustificante = idJustificante;
        this.personalModel = personal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof JustificanteModel)) return false;
        return idJustificante != null && idJustificante.equals(((JustificanteModel) obj).idJustificante);
    }

    /*Cambios por Absalom | Agregando un método para obtener el tipo de justificante y getter para fecha y estado*/
    public String getJustificanteTipo (){
        String tipo = "";
        if (!(getPermisoEconomico().equals(null))){
            tipo = "Permisos Economicos";
        } else if (!(getLicenciaPaternidad().equals(null)))
        {
            tipo = "Licencias paternidad";
        }

        return tipo;
    }

    public String getEstado() {
        return estado;
    }

    public String getFecha() {
        return fecha.toString();
    }

    /*------------------------------------------------------------------------------------------------------------*/
}
