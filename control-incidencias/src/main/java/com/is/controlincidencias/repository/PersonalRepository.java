package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("personalRepository")
public interface PersonalRepository extends JpaRepository<Personal, Serializable> {
    boolean existsPersonalByNoTarjeta(String noTarjeta);
    Personal getPersonalByNoTarjeta(String noTarjeta);
    Personal findByIdEmpleado(int idEmpleado);
    Personal findByLogin_Correo(String email);
    Personal findByLogin_CorreoAndLogin_Passwordhash(String email, String pwd);
    Personal findByNoTarjeta(String tarjeta);
}
