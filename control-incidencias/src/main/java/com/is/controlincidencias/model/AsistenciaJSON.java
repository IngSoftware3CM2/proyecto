package com.is.controlincidencias.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Setter
@Getter
public class AsistenciaJSON {
    private String tarjeta;
    private LocalTime horaEntrada;
    private LocalTime horaSalida;
    private LocalDate fecha;
    private Integer id;
}
