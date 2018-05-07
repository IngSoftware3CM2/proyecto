package com.is.controlincidencias.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DiaForm {
    private LocalTime horaEntrada;
    private static final DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("HH:mm");

    private LocalTime horaSalida;

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = LocalTime.parse(horaEntrada, formatterHour);
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = LocalTime.parse(horaSalida, formatterHour);
    }

    @Override
    public String toString() {
        return "DiaForm{" +
                "horaEntrada=" + horaEntrada +
                ", horaSalida=" + horaSalida +
                '}';
    }
}
