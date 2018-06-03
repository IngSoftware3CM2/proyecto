package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.PermisoEconomico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Repository("permisoEconomicoRepository")
public interface PermisoEconomicoRepository extends JpaRepository<PermisoEconomico,Serializable> {



    boolean existsByJustificante_IdJustificante (int id);
}
