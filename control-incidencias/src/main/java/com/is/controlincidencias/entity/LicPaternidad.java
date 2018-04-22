package com.is.controlincidencias.entity;

import javax.persistence.*;

@Entity
@Table
public class LicPaternidad {

    private static final String definition = "FOREIGN KEY(id_justificante) REFERENCES justificante (id_justificante) ON UPDATE CASCADE ON DELETE CASCADE";

    @Id
    private int id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_justificante", foreignKey = @ForeignKey(name = "justificante_pk", foreignKeyDefinition = definition))
    @MapsId
    private Justificante justificante;

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Column(nullable = false)
    private String justificacion;

    @Column(nullable = false)
    private String registrolicencia;

    @Column(nullable = false)
    private String actanacimiento;

    @Column(nullable = false)
    private String actamatrimonio;

    @Column(nullable = false)
    private String constanciacurso;

    @Column(nullable = false)
    private String comprobanteingresos;

    @Column(nullable = false)
    private String copiaidentificacion;


    public LicPaternidad(){}

    public Justificante getJustificante() {
        return justificante;
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

    @Override
    public int hashCode() {
        return 34;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PermisoEconomico)) return false;
        return justificante != null && justificante.equals(((LicPaternidad) obj).justificante);
    }
}
