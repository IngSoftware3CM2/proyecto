package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class JefeSuperior {
    @Id
    @Column(length = 2)
    private Integer idSuperior;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellidoPaterno;

    @Column(nullable = false, length = 30)
    private String apellidoMaterno;

    @OneToMany(mappedBy = "jefesuperior", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Departamento> departamentos = new ArrayList<>();

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
        departamento.setJefeSuperior(this);
    }

    public void removeDepartamento(Departamento departamento) {
        departamentos.remove(departamento);
        departamento.setJefeSuperior(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof JefeSuperior)) return false;
        return idSuperior != null && idSuperior.equals(((JefeSuperior) obj).idSuperior);
    }
}
