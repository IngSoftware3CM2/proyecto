package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "cambiohorario")
public class CambioHorario {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "justificacion", nullable = false, columnDefinition = "character varying(500)")
    private String justificacion;

    @Column(name = "fecha", nullable = false)                   //fecha a la que aplicará el cambio de horario
    private LocalDate fecha;

    @Column(name = "horaEntrada", nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaEntrada;

    @Column(name = "horaSalida", nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaSalida;

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public static String getDEFINITION() {
        return DEFINITION;
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
        CambioHorario that = (CambioHorario) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(justificacion, that.justificacion) &&
                Objects.equals(fecha, that.fecha) &&
                Objects.equals(horaEntrada, that.horaEntrada) &&
                Objects.equals(horaSalida, that.horaSalida) &&

                Objects.equals(justificante, that.justificante);
    }

    @Override
    public String toString() {
        return "CambioHorario{" +
                "id=" + id +
                ", justificacion='" + justificacion + '\'' +
                ", fecha=" + fecha +
                ", horaEntrada=" + horaEntrada +
                ", horaSalida=" + horaSalida +
                ", justificante=" + justificante +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, justificacion, fecha, horaEntrada, horaSalida, justificante);
    }

}
