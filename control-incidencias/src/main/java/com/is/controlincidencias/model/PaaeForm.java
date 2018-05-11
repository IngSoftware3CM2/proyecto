package com.is.controlincidencias.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class PaaeForm extends JefeForm{
    private DiaForm dia;
    @NotBlank
    private String turno;

}
