package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "incidencia")
public class Incidencia {
    @Id
    @Column(name = "idIncidencia", length = 4)
    private Integer idIncidencia;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "tipo", nullable = false, length = 2)
    private String tipo;

    private static final String DEFINITION = "FOREIGN KEY(idQuincena) REFERENCES quincena (idQuincena) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idQuincena", foreignKey = @ForeignKey(name = "quincena_fk", foreignKeyDefinition = DEFINITION))
    private Quincena quincena;

    public Quincena getQuincena(){ return quincena;}

    public void setQuincena(Quincena quincena) {
        this.quincena = quincena;
    }

    @Override
    public String toString() {
        return "Incidencia{" +
                "idIncidencia=" + idIncidencia +
                ", fechaRegistro=" + fechaRegistro +
                ", tipo='" + tipo + '\'' +
                ", quincena=" + quincena +
                ", personal=" + personal +
                ", justificante=" + justificante +
                '}';
    }

    private static final String DEFINITION_2 = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "noEmpleado", foreignKey = @ForeignKey(name = "empleado_fk", foreignKeyDefinition = DEFINITION_2))
    private Personal personal;

    public Incidencia(){}

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /*Cambios Absalom | agregar metodo para obtener fecha en String*/
    public String getFecha() {
        return fechaRegistro.toString();
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Personal getPersonal() {
        return personal;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public Incidencia(Integer idIncidencia, LocalDate fechaRegistro, String tipo, Quincena quincena, Personal personal, Justificante justificante) {
        this.idIncidencia = idIncidencia;
        this.fechaRegistro = fechaRegistro;
        this.tipo = tipo;
        this.quincena = quincena;
        this.personal = personal;
        this.justificante = justificante;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }



    private static final String DEFINITION3 = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE SET NULL";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION3))
    private Justificante justificante;

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdIncidencia(), getFechaRegistro(), getTipo(), getQuincena(), getPersonal(), getJustificante());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Incidencia)) return false;
        return idIncidencia != null && idIncidencia.equals(((Incidencia) obj).idIncidencia);
    }

    public int getJustificanteId (){
        return this.justificante.getIdJustificante();
    }

}
