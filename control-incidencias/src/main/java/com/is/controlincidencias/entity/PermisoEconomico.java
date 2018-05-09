package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "permisoeconomico")
public class PermisoEconomico {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "fechaIncidencia", nullable = false)
    private LocalDate fechaIncidencia;

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    private Justificante justificante;

    public PermisoEconomico() {
    }

    public PermisoEconomico(LocalDate fechaIncidencia, Justificante justificante) {
        this.fechaIncidencia = fechaIncidencia;
        this.justificante = justificante;
    }

    public LocalDate getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(LocalDate fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
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
        PermisoEconomico that = (PermisoEconomico) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(justificante, that.justificante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, justificante);
    }

    @Override
    public String toString() {
        return "PermisoEconomico{" +
                "id=" + id +
                ", fechaIncidencia='" + fechaIncidencia + '\'' +
                ", justificante=" + justificante +
                '}';
    }
}