package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@Setter
@Getter
public class PaaeModel {
    @NotNull
    private Integer departamento;
    @NotBlank
    @Size(min = 4, max = 8)
    private String noEmpleado;
    @NotBlank
    private String nombre;
    @NotBlank
    private String apPaterno;
    @NotBlank
    private String apMaterno;
    @NotBlank
    @Email
    private String correo;
    @NotBlank
    @Size(min = 1, max = 1)
    private String sexo;
    @NotNull
    private DiaModel lunes;
    private String contra;
    private String tarjeta;
}
