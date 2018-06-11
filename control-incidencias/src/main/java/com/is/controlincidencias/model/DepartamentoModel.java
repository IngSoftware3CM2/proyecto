package com.is.controlincidencias.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartamentoModel {
    private Integer idDepartamento;
    private String nombre;

    public DepartamentoModel(Integer idDepartamento, String nombre) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
    }
}
