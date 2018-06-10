package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@ToString
@Setter
@Getter
public class AsistenciaForm {
    @NotBlank
    @Size(max = 8)
    @Pattern(regexp = "^0[1-8]\\d{4}$")
    private String tarjeta;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaEntrada;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaSalida;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;
    private String nombre;
    private Integer id;
}
