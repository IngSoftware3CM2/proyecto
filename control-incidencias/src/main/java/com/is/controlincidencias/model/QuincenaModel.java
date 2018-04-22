package com.is.controlincidencias.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class QuincenaModel {
    private Integer idQuincena;

    private LocalDate inicio;

    private LocalDate fin;

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
