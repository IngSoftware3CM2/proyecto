package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.LicPaternidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface LicenciaPaternidadRepository extends JpaRepository <LicPaternidad,Serializable> {
}
