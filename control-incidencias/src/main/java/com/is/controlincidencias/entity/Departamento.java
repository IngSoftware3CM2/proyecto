package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "idDepartamento", length = 2)
    private Integer idDepartamento;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    /*Las siguientes columnas booleanas de Departamento son útiles cuando se registra un ususario, por la RN donde se especifica a que
    * ciertos tipos de usuarios solos pueden estar en ciertos Departamentos, e.g Un docente no puede pertenecer a Gestión Escolar*/

    @Column(name = "permisopaee", nullable = false)  //se deja en false si este tipo de usuario no puede trabajar en este depto.
    private Boolean permisopaee;

    @Column(name = "permisodocente", nullable = false)
    private Boolean permisodocente;

    @Column(name = "permisodocpaee", nullable = false)  //se deja en false si este tipo de usuario no puede trabajar en este depto.
    private Boolean permisodocpaee;


    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personal> personales = new ArrayList<>();

    private static final String DEFINITION2 = "FOREIGN KEY(idSuperior) REFERENCES jefesuperior (idSuperior) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idSuperior", foreignKey = @ForeignKey(name = "jefesuperior_fk", foreignKeyDefinition = DEFINITION2))
    private JefeSuperior jefesuperior;

    public Departamento() {
    }

    public Departamento(Integer idDepartamento, String nombre, JefeSuperior jefesuperior) {
        this.idDepartamento = idDepartamento;
        this.nombre = nombre;
        this.jefesuperior = jefesuperior;
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

    public JefeSuperior getJefeSuperior() {
        return jefesuperior;
    }

    public void setJefeSuperior(JefeSuperior jefesuperior) {
        this.jefesuperior = jefesuperior;
    }

    public void addPersonal(Personal personal) {
        personales.add(personal);
        personal.setDepartamento(this);
    }

    public void removePersonal(Personal personal) {
        personales.remove(personal);
        personal.setDepartamento(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departamento that = (Departamento) o;
        return Objects.equals(getIdDepartamento(), that.getIdDepartamento()) &&
                Objects.equals(getNombre(), that.getNombre()) &&
                Objects.equals(getPersonales(), that.getPersonales()) &&
                Objects.equals(getJefeSuperior(), that.getJefeSuperior());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdDepartamento(), getNombre(), getPersonales(), getJefeSuperior());
    }
}
