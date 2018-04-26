package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "justificante")
public class Justificante {

    @Id
    @Column(name = "idJustificante", length = 4)
    private Integer idJustificante;

    @Column(name = "fecha", nullable = false, columnDefinition = "date")
    private LocalDate fecha;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;               //this attrib  can be "Aceptado", "En proceso", "Rechazado"

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private PermisoEconomico permisoEconomico;

    @Override
    public String toString() {
        return "Justificante{" +
                "idJustificante=" + idJustificante +
                ", fecha=" + fecha +
                ", estado='" + estado + '\'' +
                ", permisoEconomico=" + permisoEconomico +
                ", licPaternidad=" + licPaternidad +
                ", incidencias=" + incidencias +
                ", personal=" + personal +
                '}';
    }

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private LicPaternidad licPaternidad;

    @OneToMany(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();

    private static final String DEFINITION = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "noEmpleado", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    private Personal personal;

    public Justificante() {
    }

    public void addAsistencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        incidencia.setJustificante(this);
    }

    public void removeAsistencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
        incidencia.setPersonal(null);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Integer getIdJustificante() {
        return idJustificante;
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

    /*Cambios por Absalom | Agregando un método para obtener el tipo de justificante y getter para fecha y estado*/
    public String getJustificanteTipo() {
        String tipo = "";
        if (!(getPermisoEconomico().equals(null))) {
            tipo = "Permisos Economicos";
        } else if (!(getLicenciaPaternidad().equals(null))) {
            tipo = "Licencias paternidad";
        }

        return tipo;
    }
}
