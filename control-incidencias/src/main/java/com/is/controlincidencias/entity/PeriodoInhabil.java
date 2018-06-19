package com.is.controlincidencias.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="periodoinhabil")
public class PeriodoInhabil {
    @Id
    @Column(name = "idperiodo", length = 8)
    private Integer idperiodo;

    @Column(name = "inicio", nullable = false)  //
    private LocalDate inicio;

    @Column(name = "fin", nullable = false)
    private LocalDate fin;

    @Column(name = "descripcion", nullable = false, columnDefinition = "character varying(500)")
    private String descripcion ;

    @Column(name = "aplicapaee", nullable = false)  //se deja en false si este tipo de personal no tiene este periodoInhabil.
    private Boolean permisopaee;

    @Column(name = "aplicadocente", nullable = false)
    private Boolean permisodocente;

    @Column(name = "justificacionarchivo", nullable = true)
    private String justificacionarchivo;
   /* private static final String DEFINITION = "FOREIGN KEY (idQuincena) REFERENCES  quincena (idQuincena) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)        //Quincena en la que será procesada
    @JoinColumn(name = "idquincena", nullable = true, insertable = false, updatable = false, foreignKey = @ForeignKey(name = "quincena_fk", foreignKeyDefinition = DEFINITION))
    private Quincena quincena;*/

    public Integer getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Integer idperiodo) {
        this.idperiodo = idperiodo;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getPermisopaee() {
        return permisopaee;
    }

    public void setPermisopaee(Boolean permisopaee) {
        this.permisopaee = permisopaee;
    }

    public Boolean getPermisodocente() {
        return permisodocente;
    }

    public void setPermisodocente(Boolean permisodocente) {
        this.permisodocente = permisodocente;
    }

    public String getJustificacionarchivo() {
        return justificacionarchivo;
    }

    public void setJustificacionarchivo(String justificacionarchivo) {
        this.justificacionarchivo = justificacionarchivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodoInhabil that = (PeriodoInhabil) o;
        return Objects.equals(getIdperiodo(), that.getIdperiodo()) &&
                Objects.equals(getInicio(), that.getInicio()) &&
                Objects.equals(getFin(), that.getFin()) &&
                Objects.equals(getDescripcion(), that.getDescripcion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdperiodo(), getInicio(), getFin(), getDescripcion());
    }
}
