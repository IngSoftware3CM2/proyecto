package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "justificante")
public class Justificante {
    @Id
    @Column(name = "idJustificante", length = 4, columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idJustificante;

    @Column(name = "fecha", nullable = false, columnDefinition = "date")
    private LocalDate fecha;
    // De momento lo puse true para que no tuvieran problemas
    @Column(name = "estado", nullable = true, length = 20)
    private String estado;               //this attrib  can be "Aceptado", "En proceso", "Rechazado"

    @Column (name="tipo")
    private int tipo;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private PermisoEconomico permisoEconomico;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private LicPaternidad licPaternidad;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private TipoA tipoA;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private CambioHorario cambioHorario;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private ConstanciaTiempo contanciaTiempo;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private TiempoSuplementario tiempoSuplementario;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private OmisionEntrSal omisionEntrSal;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Retardo retardo;

    @OneToOne(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private ComisionOficial comisionOficial;

    @OneToMany(mappedBy = "justificante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();

    private static final String DEFINITION = "FOREIGN KEY(idEmpleado) REFERENCES personal (idEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "empleado_fk", foreignKeyDefinition = DEFINITION))
    private Personal personal;

    public Justificante() {
    }

    public void addAsistencia(Incidencia incidencia) {
        incidencias.add(incidencia);
    }

    public void removeAsistencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
        incidencia.setPersonal(null);
    }

    public LicPaternidad getLicPaternidad() { return licPaternidad; }

    public void setLicPaternidad(LicPaternidad licPaternidad) { this.licPaternidad = licPaternidad; }

    public TipoA getTipoA() { return tipoA; }

    public void setTipoA(TipoA tipoA) { this.tipoA = tipoA; }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaAsString (){
        return fecha.toString();
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

    public CambioHorario getCambioHorario() { return cambioHorario; }

    public void setCambioHorario(CambioHorario cambioHorario) { this.cambioHorario = cambioHorario; }

    public void setIdJustificante(Integer idJustificante) {
        this.idJustificante = idJustificante;

    }

    public PermisoEconomico getPermisoEconomico() {
        return this.permisoEconomico;
    }

    public void setPermisoEconomico(PermisoEconomico permisoEconomico) {
        this.permisoEconomico = permisoEconomico;
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

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
