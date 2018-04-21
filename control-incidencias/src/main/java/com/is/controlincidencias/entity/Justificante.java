package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Justificante {
    @Id
    @Column(length = 4)
    private Integer idJustificante;

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


    @Override
    public int hashCode() {
        return 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Justificante)) return false;
        return idJustificante != null && idJustificante.equals(((Justificante) obj).idJustificante);
    }
}
