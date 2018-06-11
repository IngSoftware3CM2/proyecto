package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface DepartamentoRepository extends JpaRepository<Departamento, Serializable> {
    List<Departamento> findAllByPermisopaeeIsTrueOrPermisodocpaeeIsTrue();
    List<Departamento> findAllByPermisodocenteIsTrueOrPermisodocpaeeIsTrue();

}
