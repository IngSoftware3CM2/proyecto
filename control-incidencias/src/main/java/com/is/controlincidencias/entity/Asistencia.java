package com.is.controlincidencias.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
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

    private static final String DEFINITION = "FOREIGN KEY(idEmpleado) REFERENCES personal (idEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "personal_fk", foreignKeyDefinition = DEFINITION))
    private Personal personal;

    public Asistencia() {}

    public Asistencia(Integer idAsistencia, LocalDate fechaRegistro, LocalTime horaEntrada, LocalTime horaSalida, Personal personal) {
        this.idAsistencia = idAsistencia;
        this.fechaRegistro = fechaRegistro;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.personal = personal;
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
