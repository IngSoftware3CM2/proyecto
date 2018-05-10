package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "passwordhash", nullable = false, length = 255)
    private String passwordhash;

    private static final String DEFINITION = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @OneToOne
    @JoinColumn(name = "noEmpleado", foreignKey = @ForeignKey(name = "personal_fk", foreignKeyDefinition = DEFINITION))
    private Personal personal;

    public Login(String passwordhash, Personal personal) {
        this.passwordhash = passwordhash;
        this.personal = personal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Login(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;
        Login login = (Login) o;
        return Objects.equals(correo, login.correo) &&
                Objects.equals(passwordhash, login.passwordhash) &&
                Objects.equals(personal, login.personal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correo, passwordhash, personal);
    }
}
