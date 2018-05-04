package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "dia")
public class Dia {
    @Id
    @Column(name = "idDia")
    private Integer idDia;

    @Column(name = "nombre", nullable = false, length = 3) //LUN MAR MIE JUE VIE
    private String nombre;

    @Column(name = "horaEntrada", nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaEntrada;

    @Column(name = "horaSalida", nullable = false, columnDefinition = "time without time zone")
    private LocalTime horaSalida;

    private static final String DEFINITION = "FOREIGN KEY(idHorario) REFERENCES horarioactual (idHorario) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idHorario", foreignKey = @ForeignKey(name = "horarioactual_fk", foreignKeyDefinition = DEFINITION))
    private HorarioActual horarioActual;                //a que horario pertenece el objeto Dia


    public Integer getIdDia() {
        return idDia;
    }

    public void setIdDia(Integer idDia) {
        this.idDia = idDia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public HorarioActual getHorarioActual() {
        return horarioActual;
    }

    public void setHorarioActual(HorarioActual horarioActual) {
        this.horarioActual = horarioActual;
    }
}

