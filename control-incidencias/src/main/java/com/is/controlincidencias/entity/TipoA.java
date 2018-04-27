package com.is.controlincidencias.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tipoa")
public class TipoA implements Serializable {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

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

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    private Justificante justificante;

    private static final String DEFINITION2 = "FOREIGN KEY(idUnidad) REFERENCES unidadmedica (idUnidad) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUnidad", foreignKey = @ForeignKey(name = "unidadmedica_fk", foreignKeyDefinition = DEFINITION2))
    private UnidadMedica unidadmedica;

    public TipoA() {
    }

    public TipoA(String folio, LocalDate inicio, LocalDate fin, String licenciamedica, LocalDate tipo, Justificante justificante, UnidadMedica unidadmedica) {
        this.folio = folio;
        this.inicio = inicio;
        this.fin = fin;
        this.licenciamedica = licenciamedica;
        this.tipo = tipo;
        this.justificante = justificante;
        this.unidadmedica = unidadmedica;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoA tipoA = (TipoA) o;
        return Objects.equals(id, tipoA.id) &&
                Objects.equals(folio, tipoA.folio) &&
                Objects.equals(inicio, tipoA.inicio) &&
                Objects.equals(fin, tipoA.fin) &&
                Objects.equals(licenciamedica, tipoA.licenciamedica) &&
                Objects.equals(tipo, tipoA.tipo) &&
                Objects.equals(justificante, tipoA.justificante) &&
                Objects.equals(unidadmedica, tipoA.unidadmedica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, folio, inicio, fin, licenciamedica, tipo, justificante, unidadmedica);
    }

    @Override
    public String toString() {
        return "TipoA{" +
                "id=" + id +
                ", folio='" + folio + '\'' +
                ", inicio=" + inicio +
                ", fin=" + fin +
                ", licenciamedica='" + licenciamedica + '\'' +
                ", tipo=" + tipo +
                ", justificante=" + justificante +
                ", unidadmedica=" + unidadmedica +
                '}';
    }
}
