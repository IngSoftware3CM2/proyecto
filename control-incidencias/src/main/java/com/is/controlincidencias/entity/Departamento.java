package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    private static final String DEFINITION2 = "FOREIGN KEY(idSuperior) REFERENCES jefesuperior (idSuperior) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idSuperior", foreignKey = @ForeignKey(name = "jefesuperior_fk", foreignKeyDefinition = DEFINITION2))
    private JefeSuperior jefesuperior;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(getIdDepartamento(), that.getIdDepartamento()) &&
                Objects.equals(getNombre(), that.getNombre()) &&
                Objects.equals(getPersonales(), that.getPersonales()) &&
                Objects.equals(getJefesuperior(), that.getJefesuperior());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdDepartamento(), getNombre(), getPersonales(), getJefesuperior());
    }

    public void setJefeSuperior(JefeSuperior jefesuperior) {
        this.jefesuperior =jefesuperior;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Personal> getPersonales() {
        return personales;
    }

    public void setPersonales(List<Personal> personales) {
        this.personales = personales;
    }

    public static String getDEFINITION2() {
        return DEFINITION2;
    }

    public JefeSuperior getJefesuperior() {
        return jefesuperior;
    }

    public void setJefesuperior(JefeSuperior jefesuperior) {
        this.jefesuperior = jefesuperior;
    }
}
