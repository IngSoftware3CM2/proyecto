package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.ComisionOficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("comisionRepository")

public interface ComisionRepository extends JpaRepository<ComisionOficial, Serializable> {

}
