package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("personalRepository")
public interface PersonalRepository extends JpaRepository<Personal, Serializable> {
    boolean existsPersonalByNoTarjeta(int noTarjeta);
    Personal getPersonalByNoTarjeta(int noTarjeta);
    Personal findByNoEmpleado(int noEmpleado);
}
