package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "permisoeconomico")
public class PermisoEconomico implements Serializable {

    private static final String DEFINITION = "FOREIGN KEY(idJustificante) REFERENCES justificante (idJustificante) ON UPDATE CASCADE ON DELETE CASCADE";

    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idJustificante", foreignKey = @ForeignKey(name = "justificante_fk", foreignKeyDefinition = DEFINITION))
    @MapsId
    private Justificante justificante;

    public PermisoEconomico(){}

    public Justificante getJustificante() {
        return justificante;
    }

    public PermisoEconomico(Justificante justificante) {
        this.justificante = justificante;
    }

    public void setJustificante(Justificante justificante) {
        this.justificante = justificante;
    }


    @Override
    public int hashCode() {
        return 33;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PermisoEconomico)) return false;
        return justificante != null && justificante.equals(((PermisoEconomico) obj).justificante);
    }


}
