package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("/CambioHorarioRepository")

public interface CambioHorarioRepository extends JpaRepository<Personal, Serializable> {

}