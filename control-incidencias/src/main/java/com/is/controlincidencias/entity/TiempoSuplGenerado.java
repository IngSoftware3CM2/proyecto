package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "tiemposuplgenerado")
public class TiempoSuplGenerado {
    @Id
    @Column(name = "idAsistencia", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idAsistencia;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "usado", nullable = false) /*para saber si este tiempoSuplementarioGenerado ya fue contempleado en un justificante*/
    private Boolean usado;              /*falso si aun no se ha usado*/

    @Column(name = "horas", nullable = false, columnDefinition = "time without time zone")
    private LocalTime horas;

    private static final String DEFINITION = "FOREIGN KEY(idEmpleado) REFERENCES personal (idEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "personal_fk", foreignKeyDefinition = DEFINITION))
    private Personal personal;

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalTime getHoras() {
        return horas;
    }

    public void setHoras(LocalTime horas) {
        this.horas = horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TiempoSuplGenerado that = (TiempoSuplGenerado) o;
        return Objects.equals(getIdAsistencia(), that.getIdAsistencia()) &&
                Objects.equals(getFechaRegistro(), that.getFechaRegistro()) &&
                Objects.equals(getHoras(), that.getHoras()) &&
                Objects.equals(getPersonal(), that.getPersonal());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdAsistencia(), getFechaRegistro(), getHoras(), getPersonal());
    }
}
