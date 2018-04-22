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

<<<<<<< HEAD
    @Column(nullable = false, columnDefinition = "time without time zone")
    private LocalTime fecha;


    @Column(nullable = false, length = 20)
    private LocalTime estado;               //this attrib  can be "Aceptado", "En proceso", "Rechazado"


    @OneToMany(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PermisoEconomico> permisosEconomicos = new ArrayList<>();

    public void addPermisoEconomico(PermisoEconomico permisoeconomico) {
        permisosEconomicos.add(permisoeconomico);
        permisoeconomico.setJustificante(this);
    }

    public void removePermisoEconomico(PermisoEconomico permisoeconomico) {
        permisosEconomicos.remove(permisoeconomico);
        permisoeconomico.setJustificante(null);
    }



    @OneToMany(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LicPaternidad> licenciasPaternidad = new ArrayList<>();

    public void addLicPaternidad(LicPaternidad licpaternidad) {
        licenciasPaternidad.add(licpaternidad);
        licpaternidad.setJustificante(this);
    }

    public void removeLicPaternidad(LicPaternidad licpaternidad) {
        licenciasPaternidad.remove(licpaternidad);
        licpaternidad.setJustificante(null);
    }
=======
    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private PermisoEconomico permisoEconomico;
>>>>>>> 89378ba8710734c6413898cfce4d17b85b6a1524

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private LicPaternidad licPaternidad;

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




    public Integer getIdJustificante() {
        return idJustificante;
    }

    public Justificante() {
    }

    public void setIdJustificante(Integer idJustificante) {
        this.idJustificante = idJustificante;

    }

    public PermisoEconomico getPermisoEconomico() {
        return this.permisoEconomico;
    }

    public void setPermisoEconomico(PermisoEconomico permisoEconomico) {
        this.permisoEconomico = permisoEconomico;
    }

    public LicPaternidad getLicenciaPaternidad() {
        return licPaternidad;
    }

    public void setLicenciaPaternidad(LicPaternidad licPaternidad) {
        this.licPaternidad = licPaternidad;
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
        if (!(getPermisoEconomico().equals(null))){
            tipo = "Permisos Economicos";
        } else if (!(getLicenciaPaternidad().equals(null)))
        {
            tipo = "Licencias paternidad";
        }

        return tipo;
    }
}
