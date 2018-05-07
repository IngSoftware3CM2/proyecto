package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "licpaternidad")
public class LicPaternidad implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "justificacion", nullable = false)
    private String justificacion;

    @Column(name = "registrolicencia", nullable = false)
    private String registrolicencia;

    @Column(name = "actanacimiento", nullable = false)
    private String actanacimiento;

    @Column(name = "actamatrimonio", nullable = false)
    private String actamatrimonio;

    @Column(name = "constanciacurso", nullable = false)
    private String constanciacurso;

    @Column(name = "comprobanteingresos", nullable = false)
    private String comprobanteingresos;

    @Column(name = "copiaidentificacion", nullable = false)
    private String copiaidentificacion;

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    private Justificante justificante;

    public LicPaternidad() {
    }

    public LicPaternidad(String justificacion, String registrolicencia, String actanacimiento, String actamatrimonio, String constanciacurso, String comprobanteingresos, String copiaidentificacion, Justificante justificante) {
        this.justificacion = justificacion;
        this.registrolicencia = registrolicencia;
        this.actanacimiento = actanacimiento;
        this.actamatrimonio = actamatrimonio;
        this.constanciacurso = constanciacurso;
        this.comprobanteingresos = comprobanteingresos;
        this.copiaidentificacion = copiaidentificacion;
        this.justificante = justificante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getRegistrolicencia() {
        return registrolicencia;
    }

    public void setRegistrolicencia(String registrolicencia) {
        this.registrolicencia = registrolicencia;
    }

    public String getActanacimiento() {
        return actanacimiento;
    }

    public void setActanacimiento(String actanacimiento) {
        this.actanacimiento = actanacimiento;
    }

    public String getActamatrimonio() {
        return actamatrimonio;
    }

    public void setActamatrimonio(String actamatrimonio) {
        this.actamatrimonio = actamatrimonio;
    }

    public String getConstanciacurso() {
        return constanciacurso;
    }

    public void setConstanciacurso(String constanciacurso) {
        this.constanciacurso = constanciacurso;
    }

    public String getComprobanteingresos() {
        return comprobanteingresos;
    }

    public void setComprobanteingresos(String comprobanteingresos) {
        this.comprobanteingresos = comprobanteingresos;
    }

    public String getCopiaidentificacion() {
        return copiaidentificacion;
    }

    public void setCopiaidentificacion(String copiaidentificacion) {
        this.copiaidentificacion = copiaidentificacion;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LicPaternidad that = (LicPaternidad) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(justificacion, that.justificacion) &&
                Objects.equals(registrolicencia, that.registrolicencia) &&
                Objects.equals(actanacimiento, that.actanacimiento) &&
                Objects.equals(actamatrimonio, that.actamatrimonio) &&
                Objects.equals(constanciacurso, that.constanciacurso) &&
                Objects.equals(comprobanteingresos, that.comprobanteingresos) &&
                Objects.equals(copiaidentificacion, that.copiaidentificacion) &&
                Objects.equals(justificante, that.justificante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, justificacion, registrolicencia, actanacimiento, actamatrimonio, constanciacurso, comprobanteingresos, copiaidentificacion, justificante);
    }

    @Override
    public String toString() {
        return "LicPaternidad{" +
                "id=" + id +
                ", justificacion='" + justificacion + '\'' +
                ", registrolicencia='" + registrolicencia + '\'' +
                ", actanacimiento='" + actanacimiento + '\'' +
                ", actamatrimonio='" + actamatrimonio + '\'' +
                ", constanciacurso='" + constanciacurso + '\'' +
                ", comprobanteingresos='" + comprobanteingresos + '\'' +
                ", copiaidentificacion='" + copiaidentificacion + '\'' +
                ", justificante=" + justificante +
                '}';
    }
}
