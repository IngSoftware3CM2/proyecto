package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Justificante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("justificanteRepository")
public interface JustificanteRepository extends JpaRepository <Justificante, Serializable> {
}
