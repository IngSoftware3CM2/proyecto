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

    @Column(name = "tipo", nullable = false, length = 2)
    private String tipo;

    private static final String definition = "FOREIGN KEY(idQuincena) REFERENCES quincena (idQuincena) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idQuincena", foreignKey = @ForeignKey(name = "quincena_fk", foreignKeyDefinition = definition))
    private Quincena quincena;

    public Quincena getQuincena(){ return quincena;}

    public void setQuincena(Quincena quincena) {
        this.quincena = quincena;
    }



    private static final String definition2 = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "noEmpleado", foreignKey = @ForeignKey(name = "empleado_fk", foreignKeyDefinition = definition2))
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



    private static final String definition3 = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = definition3))
    private Justificante justificante;

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Incidencia)) return false;
        return idIncidencia != null && idIncidencia.equals(((Incidencia) obj).idIncidencia);
    }
}
