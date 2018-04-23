package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personal")
public class Personal {
    @Id
    @Column(name = "noEmpleado", length = 8)
    private Integer noEmpleado;

    @Column(name = "noTarjeta", nullable = false, length = 6, unique = true)
    private Integer noTarjeta;

    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;

    @Column(name = "apellidoPaterno", nullable = false, length = 30)
    private String apellidoPaterno;

    @Column(name = "apellidoMaterno", nullable = false, length = 30)
    private String apellidoMaterno;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistencia> asistencias = new ArrayList<>();

    public void addAsistencia(Asistencia asistencia) {
        asistencias.add(asistencia);
        asistencia.setPersonal(this);
    }

    public void removeAsistencia(Asistencia asistencia) {
        asistencias.remove(asistencia);
        asistencia.setPersonal(null);
    }

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();

    public void addIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        incidencia.setPersonal(this);
    }

    public void removeIncidencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
        incidencia.setPersonal(null);
    }


    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Justificante> justificantes = new ArrayList<>();

    public void addJustificante(Justificante justificante) {
        justificantes.add(justificante);
        justificante.setPersonal(this);
    }

    public void removeJustificante(Justificante justificante) {
        justificantes.remove(justificante);
        justificante.setPersonal(null);
    }

    private static final String definition = "FOREIGN KEY(idDepartamento) REFERENCES departamento (idDepartamento) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idDepartamento", foreignKey = @ForeignKey(name = "departamento_fk", foreignKeyDefinition = definition))
    private Departamento departamento;

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Column(nullable = false, length = 4)
    private String tipo;

    public Personal() {}

    public Personal(Integer noEmpleado, Integer noTarjeta, String nombre, String apellidoPaterno, String apellidoMaterno, Departamento departamento, String tipo) {
        this.noEmpleado = noEmpleado;
        this.noTarjeta = noTarjeta;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.departamento = departamento;
        this.tipo = tipo;
    }

    public Integer getNoEmpleado() {
        return noEmpleado;
    }

    public void setNoEmpleado(Integer noEmpleado) {
        this.noEmpleado = noEmpleado;
    }

    public Integer getNoTarjeta() {
        return noTarjeta;
    }

    public void setNoTarjeta(Integer noTarjeta) {
        this.noTarjeta = noTarjeta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public List<Asistencia> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia> asistencias) {
        this.asistencias = asistencias;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public List<Justificante> getJustificantes() {
        return justificantes;
    }

    public void setJustificantes(List<Justificante> justificantes) {
        this.justificantes = justificantes;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
