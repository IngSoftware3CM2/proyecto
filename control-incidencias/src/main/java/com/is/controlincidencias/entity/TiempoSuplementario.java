package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tiemposuplementario")
public class TiempoSuplementario {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "fecha", nullable = false)                   //dia a justificar
    private LocalDate fecha;

    @Column(name = "tiempocubrir", nullable = false)                   //dia a justificar
    private Integer tiempocubrir;

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getTiempocubrir() {
        return tiempocubrir;
    }

    public void setTiempocubrir(Integer tiempocubrir) {
        this.tiempocubrir = tiempocubrir;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }
}
