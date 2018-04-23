package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento {

    public Departamento(){}

    public Departamento(Integer idDepartamento, String nombre, JefeSuperior jefesuperior) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.jefesuperior = jefesuperior;
    }

    @Id
    @Column(name = "idDepartamento", length = 2)
    private Integer idDepartamento;


    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personal> personales = new ArrayList<>();

    public void addPersonal(Personal personal) {
        personales.add(personal);
        personal.setDepartamento(this);
    }

    public void removePersonal(Personal personal) {
        personales.remove(personal);
        personal.setDepartamento(null);
    }


    private static final String definition2 = "FOREIGN KEY(idSuperior) REFERENCES jefesuperior (idSuperior) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idSuperior", foreignKey = @ForeignKey(name = "jefesuperior_fk", foreignKeyDefinition = definition2))
    private JefeSuperior jefesuperior;

    public void setJefeSuperior(JefeSuperior jefesuperior) {
        this.jefesuperior =jefesuperior;
    }
}
