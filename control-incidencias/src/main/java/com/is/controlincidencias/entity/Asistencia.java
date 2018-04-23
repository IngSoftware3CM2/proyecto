package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "asistencia")
public class Asistencia {
    @Id
    @Column(name = "idAsistencia", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idAsistencia;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "horaEntrada", nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaEntrada;

    @Column(name = "horaSalida", nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaSalida;

    private static final String definition = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "noEmpleado", foreignKey = @ForeignKey(name = "personal_fk", foreignKeyDefinition = definition))
    private Personal personal;

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Asistencia() {}

    public Asistencia(Integer idAsistencia, LocalDate fechaRegistro, LocalTime horaEntrada, LocalTime horaSalida, Personal personal) {
        this.idAsistencia = idAsistencia;
        this.fechaRegistro = fechaRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.personal = personal;
    }

    public static String getDefinition() {
        return definition;
    }

    public Integer getId() {
        return idAsistencia;
    }

    public void setId(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Asistencia)) return false;
        return idAsistencia != null && idAsistencia.equals(((Asistencia) obj).idAsistencia);
    }
}
