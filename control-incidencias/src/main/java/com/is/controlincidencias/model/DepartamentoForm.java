package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartamentoForm {
    private Integer idDepartamento;
    private String nombre;

    public DepartamentoForm(Integer idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }
}
