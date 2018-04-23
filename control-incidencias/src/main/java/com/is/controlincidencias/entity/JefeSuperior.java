package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jefesuperior")
public class JefeSuperior {
    @Id
    @Column(name = "idSuperior", length = 2)
    private Integer idSuperior;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellidoPaterno", nullable = false, length = 30)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false, length = 30)
    private String apellidoMaterno;

    public JefeSuperior(){}

    public JefeSuperior(Integer idSuperior, String nombre, String apellidoPaterno, String apellidoMaterno, JefeSuperior jefe) {
        this.idSuperior = idSuperior;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.jefe = jefe;
    }

    @OneToMany(mappedBy = "jefesuperior", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Departamento> departamentos = new ArrayList<>();

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
        departamento.setJefeSuperior(this);
    }

    public void removeDepartamento(Departamento departamento) {
        departamentos.remove(departamento);
        departamento.setJefeSuperior(null);
    }

    private static final String definition = "FOREIGN KEY (idSuperior) REFERENCES  jefesuperior (idSuperior) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "jefe", nullable = true ,insertable=false, updatable=false,foreignKey = @ForeignKey(name = "jefesuperior_fk", foreignKeyDefinition = definition))
    private JefeSuperior jefe;

    public JefeSuperior getJefeSuperior() {
        return jefe;
    }

    public void setJefeSuperior(JefeSuperior jefeSuperior) {
        this.jefe = jefeSuperior;
    }


    @OneToMany(mappedBy="jefe", cascade = CascadeType.ALL, orphanRemoval = true) /*Personales, o subordinados de este jefe*/
    private List<JefeSuperior> subordinado = new ArrayList<>();

    public void addSubordinado(JefeSuperior jefesuperior) {
        subordinado.add(jefesuperior);
        jefesuperior.setJefeSuperior(this);
    }

    public void removeSubordinado(JefeSuperior jefesuperior) {
        subordinado.remove(jefesuperior);
        jefesuperior.setJefeSuperior(null);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof JefeSuperior)) return false;
        return idSuperior != null && idSuperior.equals(((JefeSuperior) obj).idSuperior);
    }
}
