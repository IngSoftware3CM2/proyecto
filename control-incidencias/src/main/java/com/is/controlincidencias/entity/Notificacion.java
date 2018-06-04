package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="notificacion")
public class Notificacion {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private static final String DEFINITION = "FOREIGN KEY(idMotivo) REFERENCES motivo (idMotivo) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idMotivo", foreignKey = @ForeignKey(name = "motivo_fk", foreignKeyDefinition = DEFINITION))
    private Motivo motivo;

    @Column(name = "fecha", nullable = false)       //fecha de la solicitud
    private LocalDate fecha;

    @Column(name = "archivo", nullable = false)     //qué archivo será el que suba el usuario dependerá del motivo
    private LocalDate archivo;

    private static final String DEFINITION2 = "FOREIGN KEY(idempleado) REFERENCES personal (idempleado) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idempleado", foreignKey = @ForeignKey(name = "personal_fk", foreignKeyDefinition = DEFINITION2))
    private Personal personal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getArchivo() {
        return archivo;
    }

    public void setArchivo(LocalDate archivo) {
        this.archivo = archivo;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notificacion that = (Notificacion) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getMotivo(), that.getMotivo()) &&
                Objects.equals(getFecha(), that.getFecha()) &&
                Objects.equals(getArchivo(), that.getArchivo()) &&
                Objects.equals(getPersonal(), that.getPersonal());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getMotivo(), getFecha(), getArchivo(), getPersonal());
    }
}
