package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ToString
@Setter
@Getter
public class AsistenciaForm {
    @NotNull
    private Integer tarjeta;
    @Pattern(regexp = "^\\d{2}:\\d{2}$")
    private String horaEntrada;
    @Pattern(regexp = "^\\d{2}:\\d{2}$")
    private String horaSalida;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String fecha;

}
