package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.entity.TipoA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Repository("permisoEconomicoRepository")
public interface PermisoEconomicoRepository extends JpaRepository<TipoA,Serializable> {

    @Modifying
    @Transactional
    @Query(value="insert into permisoeconomico (fechaIncidencia, idjustificante) VALUES (:fechaIncidencia, :idjustificante)", nativeQuery = true)
    void addPermisoEconomico(@Param("fechaIncidencia") LocalDate fechaIncidencia, @Param("idjustificante") int idjustificante);

    boolean existsByJustificante_IdJustificante (int id);

    PermisoEconomico findByJustificante(Justificante justificante);
}
