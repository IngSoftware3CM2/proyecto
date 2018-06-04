package com.is.controlincidencias.entity;

import javax.persistence.*;

@Entity
@Table(name = "omisionentrsal")
public class OmisionEntrSal {
    @Id
    @Column(name = "id", length = 8)
    private Integer id;

    @Column(name = "justificacion", nullable = false, columnDefinition = "character varying(600)")
    private String justificacion;

    @Column(name = "tipo", nullable = false)  //FALSE si es entrada, TRUE si es salida
    private Boolean tipo;

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    private Justificante justificante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }
}
