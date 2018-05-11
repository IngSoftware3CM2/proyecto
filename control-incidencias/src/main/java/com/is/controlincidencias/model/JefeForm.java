package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@ToString
@Setter
@Getter
public class JefeForm {
    @NotNull
    private Integer departamento;
    @NotNull
    private Integer numeroEmpleado;
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
}
