package com.is.controlincidencias.model;


import java.time.LocalDate;


public class IncidenciaModel {
    public IncidenciaModel() {
    }

    public Integer getIdIncidencia() {
        return idIncidencia;
    }

    public void setIdIncidencia(Integer idIncidencia) {
        this.idIncidencia = idIncidencia;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public PersonalModel getPersonal() {
        return personal;
    }

    public JustificanteModel getJustificante() {
        return justificante;
    }

    public IncidenciaModel(Integer idIncidencia, LocalDate fechaRegistro, String tipo, QuincenaModel quincena, PersonalModel personal, JustificanteModel justificante) {
        this.idIncidencia = idIncidencia;
        this.fechaRegistro = fechaRegistro;
        this.tipo = tipo;
        this.quincena = quincena;
        this.personal = personal;
        this.justificante = justificante;
    }

    private Integer idIncidencia;

    private LocalDate fechaRegistro;

    private String tipo;

    private static final String definition = "FOREIGN KEY(id_quincena) REFERENCES quincena (id_quincena) ON UPDATE CASCADE ON DELETE CASCADE";

    private QuincenaModel quincena;

    public QuincenaModel getQuincena(){ return quincena;}

    public void setQuincena(QuincenaModel quincena) {
        this.quincena = quincena;
    }

    @Override
    public String toString() {
        return "IncidenciaModel{" +
                "idIncidencia=" + idIncidencia +
                ", fechaRegistro=" + fechaRegistro +
                ", tipo=" + tipo +
                ", personal=" + personal +
                ", justificante=" + justificante +
                '}';
    }

    private static final String definition2 = "FOREIGN KEY(no_empleado) REFERENCES personal (no_empleado) ON UPDATE CASCADE ON DELETE CASCADE";

    private PersonalModel personal;

    public void setPersonal(PersonalModel personal) {
        this.personal = personal;
    }



    private static final String definition3 = "FOREIGN KEY(id_justificante) REFERENCES justificante (id_justificante) ON UPDATE CASCADE ON DELETE CASCADE";

    private JustificanteModel justificante;

    public void setJustificante(JustificanteModel justificante) {
        this.justificante = justificante;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IncidenciaModel)) return false;
        return idIncidencia != null && idIncidencia.equals(((IncidenciaModel) obj).idIncidencia);
    }
}
