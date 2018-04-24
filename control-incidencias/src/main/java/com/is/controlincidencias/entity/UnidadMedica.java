package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "unidadmedica")
public class UnidadMedica {

    @Id
    @Column(name = "idUnidad")
    private String idUnidad;

    @Column(name = "inicio", nullable = false)
    private String nombre;

    private static final String DEFINITION = "FOREIGN KEY(idZona) REFERENCES Zona (idZona) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idZona", foreignKey = @ForeignKey(name = "zona_fk", foreignKeyDefinition = DEFINITION))
    private Zona zona;


    @OneToMany(mappedBy = "unidadmedica", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoA> tiposa = new ArrayList<>();

    public void addTipoA(TipoA tipoa) {
        tiposa.add(tipoa);
        tipoa.setUnidadMedica(this);
    }

    public void removeTipoA(TipoA tipoa) {
        tiposa.remove(tipoa);
        tipoa.setUnidadMedica(null);
    }


    public String getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(String idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
