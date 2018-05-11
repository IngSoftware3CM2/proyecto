package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Setter
@Getter
public class AsistenciaForm {
    @NotNull
    private Integer tarjeta;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaEntrada;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaSalida;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;
}
