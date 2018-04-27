package com.is.controlincidencias.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "passwordsalt", nullable = false, length = 255)
    private String passwordsalt;

    @Column(name = "passwordhash", nullable = false, length = 255)
    private String passwordhash;

    private static final String DEFINITION = "FOREIGN KEY(noEmpleado) REFERENCES personal (noEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "noEmpleado", foreignKey = @ForeignKey(name = "personal_fk", foreignKeyDefinition = DEFINITION))
    private Personal personal;

    public Login(String passwordsalt, String passwordhash, Personal personal) {
        this.passwordsalt = passwordsalt;
        this.passwordhash = passwordhash;
        this.personal = personal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;
        Login login = (Login) o;
        return Objects.equals(correo, login.correo) &&
                Objects.equals(passwordsalt, login.passwordsalt) &&
                Objects.equals(passwordhash, login.passwordhash) &&
                Objects.equals(personal, login.personal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correo, passwordsalt, passwordhash, personal);
    }
}
