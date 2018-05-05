package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("incidenciaRepository")
public interface IncidenciaRepository extends JpaRepository<Incidencia, Serializable> {

    Incidencia findByIdIncidencia(int id);

    List <Incidencia> findAllByPersonal (Personal personal);
}
