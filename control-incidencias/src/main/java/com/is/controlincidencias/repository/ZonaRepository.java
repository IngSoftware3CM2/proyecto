package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("zonaRepository")
public interface ZonaRepository extends JpaRepository <Zona,Serializable> {
    Zona findByNombre(String nombre);
}
