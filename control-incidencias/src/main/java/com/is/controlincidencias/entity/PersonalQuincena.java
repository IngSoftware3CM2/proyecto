package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personalquincena")
public class PersonalQuincena {
    @Id
    @Column(name = "idPersonalQuincena", length = 8)
    private Integer idpersonalquincena;
   // @EmbeddedId
   // private  id;
   private static final String DEFINITION = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noEmpleado", foreignKey = @ForeignKey(name = "empleado_fk", foreignKeyDefinition = DEFINITION))
    //@MapsId("noEmpleado")
    private Personal personal;

    private static final String DEFINITION2 = "FOREIGN KEY(idQuincena) REFERENCES quincena (idQuincena) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuincena", foreignKey = @ForeignKey(name = "quincena_fk", foreignKeyDefinition = DEFINITION2))
    private Quincena quincena;

    @Column(name = "diasEconomicosSolicitados")
    private Integer dias_econ_solic;

    public PersonalQuincena() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalQuincena that = (PersonalQuincena) o;
        return Objects.equals(idpersonalquincena, that.idpersonalquincena) &&
                Objects.equals(personal, that.personal) &&
                Objects.equals(quincena, that.quincena) &&
                Objects.equals(dias_econ_solic, that.dias_econ_solic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idpersonalquincena, personal, quincena, dias_econ_solic);
    }
}
