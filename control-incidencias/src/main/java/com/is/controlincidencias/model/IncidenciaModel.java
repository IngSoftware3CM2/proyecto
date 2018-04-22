package com.is.controlincidencias.model;


import java.time.LocalDate;


public class IncidenciaModel {
    private Integer idIncidencia;

    private LocalDate fechaRegistro;

    private String tipo;

    private static final String definition = "FOREIGN KEY(id_quincena) REFERENCES quincena (id_quincena) ON UPDATE CASCADE ON DELETE CASCADE";

    private QuincenaModel quincena;

    public QuincenaModel getQuincena(){ return quincena;}

    public void setQuincena(QuincenaModel quincena) {
        this.quincena = quincena;
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
