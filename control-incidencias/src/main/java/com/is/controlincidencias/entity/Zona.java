package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "zona")
public class Zona {
    @Id
    @Column(name = "idZona", length = 35)
    private String idZona;

    @Column(name = "nombre", nullable = false, length = 180)
    private String nombre;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UnidadMedica> unidadesMedicas = new ArrayList<>();

    public void addUnidadMedica(UnidadMedica unidadMedica) {
        unidadesMedicas.add(unidadMedica);
        unidadMedica.setZona(this);
    }

    public void removeUnidadMedica(UnidadMedica unidadMedica) {
        unidadesMedicas.remove(unidadMedica);
        unidadMedica.setZona(null);
    }

    public Zona(String idZona, String nombre) {
        this.idZona = idZona;
        this.nombre = nombre;
    }

    public String getIdZona() {
        return idZona;
    }

    public void setIdZona(String idZona) {
        this.idZona = idZona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zona zona = (Zona) o;
        return Objects.equals(getIdZona(), zona.getIdZona()) &&
                Objects.equals(getNombre(), zona.getNombre());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdZona(), getNombre());
    }
}
