package com.is.controlincidencias.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tipoa")
public class TipoA {
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

    @Column(name = "licenciaArchivo", nullable = false)
    private String licenciaArchivo;

    @Column(name = "tipo", nullable = false)
    private String tipo;

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

    public TipoA(String folio, LocalDate inicio, LocalDate fin, String licenciaArchivo, String tipo, Justificante justificante, UnidadMedica unidadmedica) {
        this.folio = folio;
        this.inicio = inicio;
        this.fin = fin;
        this.licenciaArchivo = licenciaArchivo;
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

    public String getlicenciaArchivo() {
        return licenciaArchivo;
    }

    public void setlicenciaArchivo(String licenciaArchivo) {
        this.licenciaArchivo = licenciaArchivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
                Objects.equals(licenciaArchivo, tipoA.licenciaArchivo) &&
                Objects.equals(tipo, tipoA.tipo) &&
                Objects.equals(justificante, tipoA.justificante) &&
                Objects.equals(unidadmedica, tipoA.unidadmedica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, folio, inicio, fin, licenciaArchivo, tipo, justificante, unidadmedica);
    }

    @Override
    public String toString() {
        return "TipoA{" +
                "id=" + id +
                ", folio='" + folio + '\'' +
                ", inicio=" + inicio +
                ", fin=" + fin +
                ", licenciaArchivo='" + licenciaArchivo + '\'' +
                ", tipo=" + tipo +
                ", justificante=" + justificante +
                ", unidadmedica=" + unidadmedica +
                '}';
    }
}
