package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.Objects;
/*esta entidad intermedia sirve para llevar el registro de cuántos dias económicos ha pedido Personal por cada quincena
(para validar que no haya pedido más de los permitidos). No usamos @Embedded porque generaba cuetro llaves foraneas en vez de cuatro,
ademas que éstas no tenían nombre*/
@Entity
@Table(name = "personalquincena")
public class PersonalQuincena {
    @Id
    @Column(name = "idPersonalQuincena", length = 8)
    private Integer idpersonalquincena;
   // @EmbeddedId
   // private  id;
   private static final String DEFINITION = "FOREIGN KEY(idEmpleado) REFERENCES personal (idEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "empleado_fk", foreignKeyDefinition = DEFINITION))
    //@MapsId("noEmpleado")
    private Personal personal;

    private static final String DEFINITION2 = "FOREIGN KEY(idQuincena) REFERENCES quincena (idQuincena) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idQuincena", foreignKey = @ForeignKey(name = "quincena_fk", foreignKeyDefinition = DEFINITION2))
    private Quincena quincena;

    @Column(name = "diasEconomicosSolicitados")
    private Integer dias_econ_solic;

    @Column(name = "justificacionesSuplementario")
    private Integer justiftiemposuplementario;

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

    public Integer getIdpersonalquincena() {
        return idpersonalquincena;
    }

    public void setIdpersonalquincena(Integer idpersonalquincena) {
        this.idpersonalquincena = idpersonalquincena;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Quincena getQuincena() {
        return quincena;
    }

    public void setQuincena(Quincena quincena) {
        this.quincena = quincena;
    }

    public Integer getDias_econ_solic() {
        return dias_econ_solic;
    }

    public void setDias_econ_solic(Integer dias_econ_solic) {
        this.dias_econ_solic = dias_econ_solic;
    }

    public Integer getJustiftiemposuplementario() {
        return justiftiemposuplementario;
    }

    public void setJustiftiemposuplementario(Integer justiftiemposuplementario) {
        this.justiftiemposuplementario = justiftiemposuplementario;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idpersonalquincena, personal, quincena, dias_econ_solic);
    }
}
