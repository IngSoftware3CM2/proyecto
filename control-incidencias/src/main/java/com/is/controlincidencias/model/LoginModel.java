package com.is.controlincidencias.model;


public class LoginModel {
    private String oldPassword;
    private String newPassword;
    private String verifyPassword;

    public LoginModel(){ }

    public LoginModel(String oldPassword, String newPassword, String verifyPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.verifyPassword = verifyPassword;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
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
