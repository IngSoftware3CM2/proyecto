package com.is.controlincidencias.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ConsultaAsistenciaJSON {
    private Integer estado;
    private String noTarjeta;
    private LocalDate fecha;
}
