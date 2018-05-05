package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistencia> asistencias = new ArrayList<>();

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> incidencias = new ArrayList<>();

    @OneToMany(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Justificante> justificantes = new ArrayList<>();

    private static final String DEFINITION = "FOREIGN KEY(idDepartamento) REFERENCES departamento (idDepartamento) ON UPDATE CASCADE ON DELETE CASCADE";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idDepartamento", foreignKey = @ForeignKey(name = "departamento_fk", foreignKeyDefinition = DEFINITION))
    private Departamento departamento;

    @OneToOne(mappedBy = "personal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Login login;

    public Personal() {
    }

    private static final String DEFINITION2 = "FOREIGN KEY(idHorario) REFERENCES horarioactual (idHorario) ON UPDATE CASCADE ON DELETE CASCADE";

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idHorario", foreignKey = @ForeignKey(name = "horarioactual_fk", foreignKeyDefinition = DEFINITION2))
    private HorarioActual horarioActual;

    public Personal(Integer noEmpleado, Integer noTarjeta, String nombre, String apellidoPaterno, String apellidoMaterno, Departamento departamento, String tipo) {
        this.noEmpleado = noEmpleado;
        this.noTarjeta = noTarjeta;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.departamento = departamento;
        this.tipo = tipo;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
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

    /*Absalom | Método para usarlo en ver-justificantes y ver-incidencias*/
    public String nombreAndTipoToString ()
    {
        String tipo;
        String nombre;
        String nombreAndTipo;

        if (this.getTipo().equals("DOC"))
        {
            tipo = "Docente";
        }
        else
        {
            tipo = "PAAE";
        }
        nombre = this.getNombre()+  " " + this.getApellidoPaterno() + " " + this.getApellidoMaterno() + "";
        nombreAndTipo = tipo + " | " + nombre;
        return nombreAndTipo;
    }

    public void addAsistencia(Asistencia asistencia) {
        asistencias.add(asistencia);
        asistencia.setPersonal(this);
    }

    public void removeAsistencia(Asistencia asistencia) {
        asistencias.remove(asistencia);
        asistencia.setPersonal(null);
    }

    public void addIncidencia(Incidencia incidencia) {
        incidencias.add(incidencia);
        incidencia.setPersonal(this);
    }

    public void removeIncidencia(Incidencia incidencia) {
        incidencias.remove(incidencia);
        incidencia.setPersonal(null);
    }

    public void addJustificante(Justificante justificante) {
        justificantes.add(justificante);
        justificante.setPersonal(this);
    }

    public void removeJustificante(Justificante justificante) {
        justificantes.remove(justificante);
        justificante.setPersonal(null);
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personal personal = (Personal) o;
        return Objects.equals(getNoEmpleado(), personal.getNoEmpleado()) &&
                Objects.equals(getNoTarjeta(), personal.getNoTarjeta()) &&
                Objects.equals(getNombre(), personal.getNombre()) &&
                Objects.equals(getApellidoPaterno(), personal.getApellidoPaterno()) &&
                Objects.equals(getApellidoMaterno(), personal.getApellidoMaterno()) &&
                Objects.equals(getAsistencias(), personal.getAsistencias()) &&
                Objects.equals(getIncidencias(), personal.getIncidencias()) &&
                Objects.equals(getJustificantes(), personal.getJustificantes()) &&
                Objects.equals(getDepartamento(), personal.getDepartamento()) &&
                Objects.equals(getTipo(), personal.getTipo()) &&
                Objects.equals(login, personal.login);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNoEmpleado(), getNoTarjeta(), getNombre(), getApellidoPaterno(), getApellidoMaterno(), getAsistencias(), getIncidencias(), getJustificantes(), getDepartamento(), getTipo(), login);
    }
}
