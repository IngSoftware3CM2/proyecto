package com.is.controlincidencias.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "passwordhash", nullable = false)
    private String passwordhash;

    private static final String DEFINITION = "FOREIGN KEY(idEmpleado) REFERENCES personal (idEmpleado) ON UPDATE CASCADE ON DELETE CASCADE";

    @OneToOne
    @JoinColumn(name = "idEmpleado", foreignKey = @ForeignKey(name = "personal_fk", foreignKeyDefinition = DEFINITION))
    private Personal personal;

    public Login(String passwordhash, Personal personal) {
        this.passwordhash = passwordhash;
        this.personal = personal;
    }

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
