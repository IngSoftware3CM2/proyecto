package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class AsistenciaForm {
    private Integer tarjeta;
    private String horaEntrada;
    private String horaSalida;
    private String fecha;

}
