package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("incidenciaRepository")
public interface IncidenciaRepository extends JpaRepository<Incidencia, Serializable> {
}
