package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Departamento {

    public Departamento(){}

    public Departamento(Integer idDepartamento, String nombre, JefeSuperior jefesuperior) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.jefesuperior = jefesuperior;
    }

    @Id

    @Column(length = 2)
    private Integer idDepartamento;


    @Column(nullable = false, length = 60)
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


    private static final String definition2 = "FOREIGN KEY(id_superior) REFERENCES jefe_superior (id_superior) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_superior", foreignKey = @ForeignKey(name = "jefesuperior_pk", foreignKeyDefinition = definition2))
    private JefeSuperior jefesuperior;

    public void setJefeSuperior(JefeSuperior jefesuperior) {
        this.jefesuperior =jefesuperior;
    }
}
