package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "horarioactual")
public class HorarioActual {
    @Id
    @Column(name = "idHorario")
    private Integer idHorario;

    @OneToOne(mappedBy = "horarioActual", cascade = CascadeType.ALL, orphanRemoval = true, fetch
            = FetchType.LAZY)
    private Personal personal;

    @OneToMany(mappedBy = "horarioActual", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dia> dias = new ArrayList<>();



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

    public List<Dia> getDias() {
        return dias;
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
