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

    @Column(name = "valid_direc")
    private Integer valid_direc;

    @Column(name = "valid_admon")
    private Integer valid_admon;

    @Column(name = "valid_subdir")
    private Integer valid_subdir;

    @Column(name = "valid_superior")
    private Integer valid_superior;

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

    public Integer getValid_direc() {
        return valid_direc;
    }

    public void setValid_direc(Integer valid_direc) {
        this.valid_direc = valid_direc;
    }

    public Integer getValid_admon() {
        return valid_admon;
    }

    public void setValid_admon(Integer valid_admon) {
        this.valid_admon = valid_admon;
    }

    public Integer getValid_subdir() {
        return valid_subdir;
    }

    public void setValid_subdir(Integer valid_subdir) {
        this.valid_subdir = valid_subdir;
    }

    public Integer getValid_superior() {
        return valid_superior;
    }

    public void setValid_superior(Integer valid_superior) {
        this.valid_superior = valid_superior;
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
