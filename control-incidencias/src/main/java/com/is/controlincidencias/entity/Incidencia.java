package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incidencia")
public class Incidencia {
    @Id
    @Column(name = "idIncidencia", length = 4)
    private Integer idIncidencia;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "tipo", nullable = true, length = 2)
    private String tipo;

    private static final String DEFINITION = "FOREIGN KEY(idQuincena) REFERENCES quincena (idQuincena) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idQuincena", foreignKey = @ForeignKey(name = "quincena_fk", foreignKeyDefinition = DEFINITION))
    private Quincena quincena;

    private static final String DEFINITION_2 = "FOREIGN KEY(idEmpleado) REFERENCES personal (idEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "empleado_fk", foreignKeyDefinition = DEFINITION_2))
    private Personal personal;

    private static final String DEFINITION3 = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE SET NULL";


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION3), columnDefinition = "integer")
    private Justificante justificante;

    public Incidencia() {
    }

    public Incidencia(Integer idIncidencia, LocalDate fechaRegistro, String tipo, Quincena quincena, Personal personal, Justificante justificante) {
        this.idIncidencia = idIncidencia;
        this.fechaRegistro = fechaRegistro;
        this.tipo = tipo;
        this.quincena = quincena;
        this.personal = personal;
        this.justificante = justificante;
    }

    public Incidencia(Integer idIncidencia, LocalDate fechaRegistro, String tipo, Quincena quincena, Personal personal) {
        this.idIncidencia = idIncidencia;
        this.fechaRegistro = fechaRegistro;
        this.tipo = tipo;
        this.quincena = quincena;
        this.personal = personal;
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public String getFechaAsString () {return fechaRegistro.toString();}

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /*Cambios Absalom | agregar metodo para obtener fecha en String*/
    public String getFechaRegistroasString() {
        return fechaRegistro.toString();
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

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Justificante getJustificante() {
        return justificante;
    }

    public Quincena getQuincena() {
        return quincena;
    }


    @Override
    public String toString() {
        return "Incidencia{" +
                "idIncidencia=" + idIncidencia +
                ", fechaRegistro=" + fechaRegistro +
                ", tipo=" + tipo +
                '}';
    }


    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }
    public void setQuincena(Quincena quincena) {
        this.quincena = quincena;
    }

    public int getJustificanteId (){
        return this.justificante.getIdJustificante();
    }
}
