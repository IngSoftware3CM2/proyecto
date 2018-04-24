package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("justificanteRepository")
public interface JustificanteRepository extends JpaRepository <Justificante, Serializable> {

    Justificante findByIdJustificante (int id);
    List<Justificante> findAllByPersonal (Personal personal);
}
