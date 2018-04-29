package com.is.controlincidencias.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class QuincenaModel {
    private Integer idQuincena;

    private LocalDate inicio;

    private LocalDate fin;

    public Integer getIdQuincena() {
        return idQuincena;
    }

    public QuincenaModel() {
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

    public QuincenaModel(Integer idQuincena, LocalDate inicio, LocalDate fin) {
        this.idQuincena = idQuincena;
        this.inicio = inicio;
        this.fin = fin;
    }

    private List<IncidenciaModel> incidencias = new ArrayList<>();   /*supongo que este arrayList es util para
                                                                desarrollo como lo definieron en Personal.java, en el sentido que decimos "Personal tiene n Asistencias",
                                                                pero no sucede asi con Quincena e Incidencia
                                                                es decir, no tiene mucho sentido decir "Una quincena tiene n Incidencias"
                                                                */
    public void addIncidencia(IncidenciaModel incidencia) {
        incidencias.add(incidencia);
        incidencia.setQuincena(this);
    }

    public void removeIncidencia(IncidenciaModel incidencia) {
        incidencias.remove(incidencia);
        incidencia.setQuincena(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuincenaModel)) return false;
        return idQuincena != null && idQuincena.equals(((QuincenaModel) obj).idQuincena);
    }
}
