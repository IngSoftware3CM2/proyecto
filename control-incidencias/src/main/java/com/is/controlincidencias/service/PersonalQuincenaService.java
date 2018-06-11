package com.is.controlincidencias.service;

public interface PersonalQuincenaService {
    Integer preguntarPorDiasSuplementariosQuincena(int idempleado,int idquincena);
    Integer selectMaxIdPersonalQuincena();
    void insertRegistroSupl(int idpersonalquincena,int tiemposuplsolicitados,int idempleado,int idquincena);
    void updateSuplementarioQuincena(int tiempossuplsolicitados,int idempleado,int idquincena);
}
