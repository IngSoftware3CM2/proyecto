package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="motivo")
public class Motivo {
    @Id
    @Column(name = "idMotivo", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idMotivo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;                 //será: 1 para Licencia por paternidad, 2 para Licencia medica, 3 para Comisión oficial

    @OneToMany(mappedBy = "motivo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notificacion> notificaciones = new ArrayList<>();

    public void addNotificacion(Notificacion notificacion) {
        notificaciones.add(notificacion);
        notificacion.setMotivo(this);
    }

    public void removeNotificacion(Notificacion notificacion) {
        notificaciones.remove(notificacion);
        notificacion.setMotivo(null);
    }

    public Integer getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(Integer idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motivo motivo = (Motivo) o;
        return Objects.equals(getIdMotivo(), motivo.getIdMotivo()) &&
                Objects.equals(getDescripcion(), motivo.getDescripcion()) &&
                Objects.equals(getNotificaciones(), motivo.getNotificaciones());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdMotivo(), getDescripcion(), getNotificaciones());
    }

    @Override
    public String toString() {
        return "Motivo{" +
                "idMotivo=" + idMotivo +
                ", descripcion='" + descripcion + '\'' +
                ", notificaciones=" + notificaciones +
                '}';
    }
}
