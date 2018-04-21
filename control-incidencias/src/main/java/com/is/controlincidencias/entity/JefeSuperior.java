package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class JefeSuperior {
    @Id
    @Column(length = 2)
    private Integer id_superior;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 30)
    private String apellidoPaterno;

    @Column(nullable = false, length = 30)
    private String apellidoMaterno;

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

    private static final String definition = "FOREIGN KEY (id_superior) REFERENCES  jefe_superior (id_superior) ON UPDATE CASCADE ON DELETE CASCADE";
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "jefe", nullable = true ,insertable=false, updatable=false,foreignKey = @ForeignKey(name = "jefe_superior_pk", foreignKeyDefinition = definition))
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
        return id_superior != null && id_superior.equals(((JefeSuperior) obj).id_superior);
    }
}
