package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "retardo")
public class Retardo {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "justificacion", nullable = false, columnDefinition = "character varying(600)")
    private String justificacion;

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    private Justificante justificante;

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
        Retardo retardo = (Retardo) o;
        return Objects.equals(getId(), retardo.getId()) &&
                Objects.equals(getJustificacion(), retardo.getJustificacion()) &&
                Objects.equals(getJustificante(), retardo.getJustificante());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getJustificacion(), getJustificante());
    }
}
