package com.is.controlincidencias.entity;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tipoa")
public class TipoA {

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";
    @Id
    private int id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    @MapsId
    private Justificante justificante;

    @Column(name = "folio", nullable = false)
    private String folio;

    @Column(name = "fechainicio", nullable = false)
    private LocalDate inicio;

    @Column(name = "fechafin", nullable = false)
    private LocalDate fin;

    @Column(name = "licenciamedica", nullable = false)
    private String licenciamedica;

    @Column(name = "tipo", nullable = false)
    private LocalDate tipo;


    private static final String DEFINITION2 = "FOREIGN KEY(idUnidad) REFERENCES unidadmedica (idUnidad) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUnidad", foreignKey = @ForeignKey(name = "unidadmedica_fk", foreignKeyDefinition = DEFINITION2))
    private UnidadMedica unidadmedica;

    public TipoA(int id, Justificante justificante, String folio, LocalDate inicio, LocalDate fin, String licenciamedica, LocalDate tipo, UnidadMedica unidadMedica) {
        this.id = id;
        this.justificante = justificante;
        this.folio = folio;
        this.inicio = inicio;
        this.fin = fin;
        this.licenciamedica = licenciamedica;
        this.tipo = tipo;
        this.unidadmedica = unidadMedica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public String getLicenciamedica() {
        return licenciamedica;
    }

    public void setLicenciamedica(String licenciamedica) {
        this.licenciamedica = licenciamedica;
    }

    public LocalDate getTipo() {
        return tipo;
    }

    public void setTipo(LocalDate tipo) {
        this.tipo = tipo;
    }

    public UnidadMedica getUnidadMedica() {
        return unidadmedica;
    }

    public void setUnidadMedica(UnidadMedica unidadMedica) {
        this.unidadmedica = unidadMedica;
    }
}
