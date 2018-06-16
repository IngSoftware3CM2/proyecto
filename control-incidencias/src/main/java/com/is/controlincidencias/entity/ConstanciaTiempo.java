package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="constanciatiempo")
public class ConstanciaTiempo {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "licenciaArchivo", nullable = false)
    private String constanciaArchivo;

    @Column(name = "segfecha", nullable = true, unique = true)
    private LocalDate segfecha;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    private Justificante justificante;

    public static String getDEFINITION() {
        return DEFINITION;
    }

    public ConstanciaTiempo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConstanciaArchivo() {
        return constanciaArchivo;
    }

    public void setConstanciaArchivo(String constanciaArchivo) {
        this.constanciaArchivo = constanciaArchivo;
    }

    public LocalDate getSegfecha() {
        return segfecha;
    }

    public void setSegfecha(LocalDate segfecha) {
        this.segfecha = segfecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstanciaTiempo that = (ConstanciaTiempo) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getConstanciaArchivo(), that.getConstanciaArchivo()) &&
                Objects.equals(getSegfecha(), that.getSegfecha()) &&
                Objects.equals(getTipo(), that.getTipo()) &&
                Objects.equals(getJustificante(), that.getJustificante());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getConstanciaArchivo(), getSegfecha(), getTipo(), getJustificante());
    }
}
