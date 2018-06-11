package com.is.controlincidencias.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Setter
@Getter
public class DocenteModel extends PaaeModel{
    private DiaModel martes;
    private DiaModel miercoles;
    private DiaModel jueves;
    private DiaModel viernes;
    private Boolean abierto;
    private Integer tipo;
}
