package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "quincena")
public class Quincena {
    @Id
    @Column(name = "idQuincena", length = 4)
    private Integer idQuincena;

    @Column(name = "inicio", nullable = false)
    private LocalDate inicio;

    @Column(name = "fin", nullable = false)
    private LocalDate fin;

    @OneToMany(mappedBy = "quincena", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();   /*supongo que este arrayList es util para
                                                                desarrollo como lo definieron en Personal.java, en el sentido que decimos "Personal tiene n Asistencias",
                                                                pero no sucede asi con Quincena e Incidencia
                                                                es decir, no tiene mucho sentido decir "Una quincena tiene n Incidencias"
                                                                */

    @OneToMany(mappedBy = "quincena", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonalQuincena> empleados = new ArrayList<>();

    /*@ManyToMany(mappedBy = "quincena")
    private Set<Personal> personal = new HashSet<Personal>();

    public Set<Personal> getGroups() {
        return personal;
    }*/

    public Quincena() {
    }

    public Quincena(Integer idQuincena, LocalDate inicio, LocalDate fin) {
        this.idQuincena = idQuincena;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Integer getIdQuincena() {
        return idQuincena;
    }

    public void setIdQuincena(Integer idQuincena) {
        this.idQuincena = idQuincena;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public void addIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        incidencia.setQuincena(this);
    }

    public void removeIncidencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
        incidencia.setQuincena(null);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getIdQuincena(), getInicio(), getFin(), incidencias);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quincena)) return false;
        return idQuincena != null && idQuincena.equals(((Quincena) obj).idQuincena);
    }
}
