package com.is.controlincidencias.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Quincena implements Serializable {
    @Id
    @Column (name = "idQuincena", nullable = false, columnDefinition = "integer")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer idQuincena;

    @Column (name = "inicioQuincena", nullable = false, columnDefinition = "date")
    private Date inicioQuincena;

    @Column (name = "finQuincena", nullable =  = false, columnDefinition = "date")
    private Date finQuincena;

    @OneToMany (mappedBy = "Quincena", cascade = CascadeType.ALL, orpanremoval = true)

}
