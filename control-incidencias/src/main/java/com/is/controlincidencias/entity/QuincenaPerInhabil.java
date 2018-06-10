package com.is.controlincidencias.entity;

import javax.persistence.*;

@Entity
@Table(name = "quincenaperhabil")
public class QuincenaPerInhabil {
    @Id
    @Column(name = "idPersonalQuincena", length = 8)
    private Integer idpersonalquincena;
    // @EmbeddedId
    // private  id;
    private static final String DEFINITION = "FOREIGN KEY(idPeriodo) REFERENCES periodoinhabil (idPeriodo) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeriodo", foreignKey = @ForeignKey(name = "periodo_fk", foreignKeyDefinition = DEFINITION))
    //@MapsId("noEmpleado")
    private PeriodoInhabil periodoInhabil;

    private static final String DEFINITION2 = "FOREIGN KEY(idQuincena) REFERENCES quincena (idQuincena) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuincena", foreignKey = @ForeignKey(name = "quincena_fk", foreignKeyDefinition = DEFINITION2))
    private Quincena quincena;
}
