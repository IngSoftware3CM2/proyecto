package com.is.controlincidencias.model;


import java.time.LocalDate;
import java.time.LocalTime;

public class AsistenciaModel {
    private Integer id;

    private LocalDate fechaRegistro;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    private static final String definition = "FOREIGN KEY(no_empleado) REFERENCES personal (no_empleado) ON UPDATE CASCADE ON DELETE CASCADE";

    private PersonalModel personal;

    public PersonalModel getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalModel personal) {
        this.personal = personal;
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AsistenciaModel)) return false;
        return id != null && id.equals(((AsistenciaModel) obj).id);
    }
}
