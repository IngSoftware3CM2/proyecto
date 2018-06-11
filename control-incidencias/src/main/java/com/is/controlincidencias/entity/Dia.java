package com.is.controlincidencias.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Setter
@Getter
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
}

