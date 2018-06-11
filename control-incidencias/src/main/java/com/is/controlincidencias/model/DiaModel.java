package com.is.controlincidencias.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;


@Setter
@Getter
@ToString
public class DiaModel {
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
}
