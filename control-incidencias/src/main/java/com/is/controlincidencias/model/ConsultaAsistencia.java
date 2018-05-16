package com.is.controlincidencias.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ConsultaAsistencia {
    private Integer estado;
    private String noTarjeta;
    private LocalDate fechaRegistro;
}
