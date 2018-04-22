package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class Asistencia {
    // Tuve que modificar esto porque en el insert no me generaba el id
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaEntrada;

    @Column(nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaSalida;

    private static final String definition = "FOREIGN KEY(no_empleado) REFERENCES personal (no_empleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "no_empleado", foreignKey = @ForeignKey(name = "personal_pk", foreignKeyDefinition = definition))
    private Personal personal;

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Asistencia() {}

    public Asistencia(Integer id, LocalDate fechaRegistro, LocalTime horaEntrada, LocalTime horaSalida, Personal personal) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.personal = personal;
    }

    public static String getDefinition() {
        return definition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return id != null && id.equals(((Asistencia) obj).id);
    }
}
