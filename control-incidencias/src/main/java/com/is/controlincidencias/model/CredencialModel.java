package com.is.controlincidencias.model;

import java.util.Objects;

public class CredencialModel {
    private String userEmail;
    private String userPassword;
    /*
     * Rol
     *  -0: default/
     *  -1: root/admin
     *  *Falta definir mejor los roles.
     */
    private int role;

    public CredencialModel() {
        this("","",0);
    }

    public CredencialModel(String userEmail, String userPassword) {
        this(userEmail, userPassword, 0);
    }

    public CredencialModel(String userEmail, String userPassword, int role) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CredencialModel that = (CredencialModel) o;
        return role == that.role &&
                Objects.equals(userEmail, that.userEmail) &&
                Objects.equals(userPassword, that.userPassword);
    }

    @Override
    public String toString() {
        return "CredencialModel{" +
                "userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", role=" + role +
                '}';
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
