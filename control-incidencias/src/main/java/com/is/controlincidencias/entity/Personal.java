package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Personal {
    @Id
    @Column(length = 8)
    private Integer noEmpleado;

    @Column(nullable = false, length = 6, unique = true)
    private Integer noTarjeta;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 80)
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
