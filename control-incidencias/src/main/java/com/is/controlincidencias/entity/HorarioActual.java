package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "horarioactual")
public class HorarioActual implements Serializable {
    @Id
    @Column(name = "idHorario")
    private Integer idHorario;

    @OneToOne(mappedBy = "horarioActual", cascade = CascadeType.ALL, orphanRemoval = true)
    private Personal personal;

    @OneToMany(mappedBy = "horarioActual", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dia> dias = new ArrayList<>();

    public void addDia(Dia dia) {
        dias.add(dia);
        dia.setHorarioActual(this);
    }

    public void removeDia(Dia dia) {
        dias.remove(dia);
        dia.setHorarioActual(null);
    }

    public Integer getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Integer idHorario) {
        this.idHorario = idHorario;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorarioActual that = (HorarioActual) o;
        return Objects.equals(getIdHorario(), that.getIdHorario()) &&
                Objects.equals(getPersonal(), that.getPersonal());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdHorario(), getPersonal());
    }

    @Override
    public String toString() {
        return "HorarioActual{" +
                "idHorario=" + idHorario +
                ", personal=" + personal +
                '}';
    }
}
