package com.is.controlincidencias.entity;
/*en esta tabla solo se registra si el personal no goza de cierto periodoInhabil o vacacional*/

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "personalperiodoinhabil")
public class    PersonalPeriodoInhabil {
    @Id
    @Column(name = "idPersonalPeriodoInhabil", length = 8)
    private Integer idpersonalperiodoinhabil;
    // @EmbeddedId
    // private  id;
    private static final String DEFINITION = "FOREIGN KEY(idEmpleado) REFERENCES personal (idEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "empleado_fk", foreignKeyDefinition = DEFINITION))
    //@MapsId("noEmpleado")
    private Personal personal;

    private static final String DEFINITION2 = "FOREIGN KEY(idPeriodo) REFERENCES periodoinhabil (idPeriodo) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPeriodo", foreignKey = @ForeignKey(name = "periodoinhabil_fk", foreignKeyDefinition = DEFINITION2))
    private PeriodoInhabil periodoInhabil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalPeriodoInhabil that = (PersonalPeriodoInhabil) o;
        return Objects.equals(idpersonalperiodoinhabil, that.idpersonalperiodoinhabil) &&
                Objects.equals(personal, that.personal) &&
                Objects.equals(periodoInhabil, that.periodoInhabil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpersonalperiodoinhabil, personal, periodoInhabil);
    }
}
