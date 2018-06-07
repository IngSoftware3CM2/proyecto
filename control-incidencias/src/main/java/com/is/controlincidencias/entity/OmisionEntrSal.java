package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "omisionentrsal")
public class OmisionEntrSal {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "justificacion", nullable = false, columnDefinition = "character varying(600)")
    private String justificacion;

    @Column(name = "tipo", nullable = false)  //FALSE si es entrada, TRUE si es salida
    private Boolean tipo;

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

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
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
        OmisionEntrSal that = (OmisionEntrSal) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(justificacion, that.justificacion) &&
                Objects.equals(tipo, that.tipo) &&
                Objects.equals(justificante, that.justificante);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, justificacion, tipo, justificante);
    }

    @Override
    public String toString() {
        return "OmisionEntrSal{" +
                "id=" + id +
                ", justificacion='" + justificacion + '\'' +
                ", tipo=" + tipo +
                ", justificante=" + justificante +
                '}';
    }
}
