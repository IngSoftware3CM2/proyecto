package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class LicPaternidad implements Serializable{

    private static final String definition = "FOREIGN KEY(id_justificante) REFERENCES justificante (id_justificante) ON UPDATE CASCADE ON DELETE CASCADE";
    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_justificante", foreignKey = @ForeignKey(name = "justificante_pk", foreignKeyDefinition = definition))
    private Justificante justificante;

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }

    @Column(nullable = false)
    private String justificacion;

    @Column(nullable = false)
    private String registrolicencia;

    @Column(nullable = false)
    private String actanacimiento;

    @Column(nullable = false)
    private String actamatrimonio;

    @Column(nullable = false)
    private String constanciacurso;

    @Column(nullable = false)
    private String comprobanteingresos;

    @Column(nullable = false)
    private String copiaidentificacion;


    @Override
    public int hashCode() {
        return 34;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PermisoEconomico)) return false;
        return justificante != null && justificante.equals(((LicPaternidad) obj).justificante);
    }
}
