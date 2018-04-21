package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Quincena {
    @Id
    @Column(length = 4)
    private Integer idQuincena;

    @Column(nullable = false)
    private LocalDate inicio;

    @Column(nullable = false)
    private LocalDate fin;

    @OneToMany(mappedBy = "quincena", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();   /*supongo que este arrayList es util para
                                                                desarrollo como lo definieron en Personal.java, en el sentido que decimos "Personal tiene n Asistencias",
                                                                pero no sucede asi con Quincena e Incidencia
                                                                es decir, no tiene mucho sentido decir "Una quincena tiene n Incidencias"
                                                                */
    public void addIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        incidencia.setQuincena(this);
    }

    public void removeIncidencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
        incidencia.setQuincena(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quincena)) return false;
        return idQuincena != null && idQuincena.equals(((Quincena) obj).idQuincena);
    }
}
