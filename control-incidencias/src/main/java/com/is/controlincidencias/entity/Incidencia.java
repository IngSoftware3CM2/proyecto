package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Incidencia {
    @Id
    @Column(length = 4)
    private Integer idIncidencia;

    @Column(nullable = false)
    private LocalDate fechaRegistro;

    @Column(nullable = false, length = 2)
    private String tipo;

    private static final String definition = "FOREIGN KEY(id_quincena) REFERENCES quincena (id_quincena) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_quincena", foreignKey = @ForeignKey(name = "quincena_pk", foreignKeyDefinition = definition))
    private Quincena quincena;

    public Quincena getQuincena(){ return quincena;}

    public void setQuincena(Quincena quincena) {
        this.quincena = quincena;
    }



    private static final String definition2 = "FOREIGN KEY(no_empleado) REFERENCES personal (no_empleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "no_empleado", foreignKey = @ForeignKey(name = "empleado_pk", foreignKeyDefinition = definition2))
    private Personal personal;

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }



    private static final String definition3 = "FOREIGN KEY(id_justificante) REFERENCES justificante (id_justificante) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_justificante", foreignKey = @ForeignKey(name = "justificante_pk", foreignKeyDefinition = definition3))
    private Justificante justificante;

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Incidencia)) return false;
        return idIncidencia != null && idIncidencia.equals(((Incidencia) obj).idIncidencia);
    }
}
