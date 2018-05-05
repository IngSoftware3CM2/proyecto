package com.is.controlincidencias.model;


import javax.validation.constraints.NotNull;


public class CambioPasswordModel {
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String verifyPassword;

    public CambioPasswordModel(){}

    public CambioPasswordModel(String oldPassword, String newPassword, String verifyPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.verifyPassword = verifyPassword;
    }

    @Override
    public String toString() {
        return "CambioPasswordModel{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", verifyPassword='" + verifyPassword + '\'' +
                '}';
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

}
