package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.UnidadMedica;
import com.is.controlincidencias.entity.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("unidadMedicaRepository")
public interface UnidadMedicaRepository extends JpaRepository<UnidadMedica,Serializable> {
    List<UnidadMedica> findByZona(Zona zona);
    UnidadMedica findByNombre(String nombre);
}
