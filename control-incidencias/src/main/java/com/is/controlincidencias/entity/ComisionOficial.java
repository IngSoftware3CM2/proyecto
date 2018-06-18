package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "comisionoficial")
public class ComisionOficial {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "fechainicio", nullable = false)
    private LocalDate inicio;

    @Column(name = "fechafin", nullable = false)
    private LocalDate fin;

    @Column(name = "invitacionArchivo", nullable = false)
    private String invitacionArchivo;

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

    public String getInvitacionArchivo() {
        return invitacionArchivo;
    }

    public void setInvitacionArchivo(String invitacionArchivo) {
        this.invitacionArchivo = invitacionArchivo;
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
        ComisionOficial that = (ComisionOficial) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getInicio(), that.getInicio()) &&
                Objects.equals(getFin(), that.getFin()) &&
                Objects.equals(getInvitacionArchivo(), that.getInvitacionArchivo()) &&
                Objects.equals(getJustificante(), that.getJustificante());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getInicio(), getFin(), getInvitacionArchivo(), getJustificante());
    }
}
