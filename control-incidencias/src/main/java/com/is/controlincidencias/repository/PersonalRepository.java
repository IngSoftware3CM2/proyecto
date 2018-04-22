package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Asistencia;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PersonalRepository extends JpaRepository<Personal, Serializable> {
    boolean existsPersonalByNoTarjeta(int noTarjeta);
}
