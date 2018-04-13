package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Personal {
    @Id
    private String noEmpleado;

    @Column(nullable = false)
    private String noTarjeta;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellidos;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistencia> asistencias = new ArrayList<>();

    public void addAsistencia(Asistencia asistencia) {
        asistencias.add(asistencia);
        asistencia.setPersonal(this);
    }

    public void removeAsistencia(Asistencia asistencia) {
        asistencias.remove(asistencia);
        asistencia.setPersonal(null);
    }


}
