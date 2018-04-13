package com.is.controlincidencias.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class Asistencia {
    @Id
    @Column(columnDefinition = "serial")
    @Generated(GenerationTime.INSERT)
    private Integer id;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaEntrada;

    @Column(nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaSalida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_empleado")
    private Personal personal;

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
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
        return id != null && id.equals(((Asistencia) obj).id);
    }
}
