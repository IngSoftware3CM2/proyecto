package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Justificante {

    @Id
    @Column(length = 4)
    private Integer idJustificante;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    PermisoEconomico permisoEconomico = new PermisoEconomico();

    public void setPermisoEconomico(PermisoEconomico permisoeconomico) {
        permisoEconomico=permisoeconomico;
        permisoeconomico.setJustificante(this);
    }






    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private LicPaternidad licenciaPaternidad = new LicPaternidad();

    public void addLicPaternidad(LicPaternidad licpaternidad) {
        licenciaPaternidad=licpaternidad;
        licpaternidad.setJustificante(this);
    }


    @OneToMany(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();

    public void addAsistencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        incidencia.setJustificante(this);
    }

    public void removeAsistencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
        incidencia.setPersonal(null);
    }


    private static final String definition = "FOREIGN KEY(no_empleado) REFERENCES personal (no_empleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "no_empleado", foreignKey = @ForeignKey(name = "justificante_pk", foreignKeyDefinition = definition))
    private Personal personal;

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }


    @Column(nullable = false, columnDefinition = "time without time zone")
    private LocalTime fecha;


    @Column(nullable = false, length = 20)
    private LocalTime estado;               //this attrib  can be "Aceptado", "En proceso", "Rechazado"




    public Integer getIdJustificante() {
        return idJustificante;
    }

    public Justificante() {
    }

    public void setIdJustificante(Integer idJustificante) {
        this.idJustificante = idJustificante;

    }

    public PermisoEconomico getPermisosEconomicos() {
        return this.permisoEconomico;
    }

    public void setPermisosEconomicos(PermisoEconomico permisosEconomicos) {
        this.permisoEconomico = permisosEconomicos;
    }

    public LicPaternidad getLicenciasPaternidad() {
        return licenciaPaternidad;
    }

    public void setLicenciasPaternidad(LicPaternidad licenciaPaternidad) {
        this.licenciaPaternidad = licenciaPaternidad;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public Personal getPersonal() {
        return personal;
    }

    @Override
    public int hashCode() {
        return 32;
    }

    public Justificante(Integer idJustificante, Personal personal) {
        this.idJustificante = idJustificante;
        this.personal = personal;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Justificante)) return false;
        return idJustificante != null && idJustificante.equals(((Justificante) obj).idJustificante);
    }

    /*Cambios por Absalom | Agregando un método para obtener el tipo de justificante*/
    public String getJustificanteTipo (){
        String tipo = "";
        if (!(getPermisosEconomicos().equals(null))){
            tipo = "Permisos Economicos";
        }
        else if (!(getLicenciasPaternidad().equals(null)))
        {
            tipo = "Licencias paternidad";
        }

        return tipo;
    }
}
