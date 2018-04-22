package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.LicPaternidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("licPaternidadRepository")
public interface LicPaternidadRepository extends JpaRepository<LicPaternidad, Serializable> {
}
