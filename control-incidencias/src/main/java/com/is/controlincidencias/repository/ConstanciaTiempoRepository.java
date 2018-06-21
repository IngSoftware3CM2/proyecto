package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.ConstanciaTiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;


@Repository("constanciaTiempoRepository")
public interface ConstanciaTiempoRepository extends JpaRepository<ConstanciaTiempo, Serializable> {

    @Modifying
    @Query(value = "insert into constanciatiempo (id, licenciaarchivo, segfecha, tipo, idjustificante) VALUES (:id, :licenciaarchivo, :segfecha, :tipo, :idjustificante)", nativeQuery = true)
    @Transactional
    void altaConstanciaTiempo(@Param("id") int id, @Param("licenciaarchivo") String licenciaarchivo, @Param("segfecha") LocalDate segfecha, @Param("tipo") String tipo, @Param("idjustificante") int idjustificante);

    @Query(value = "select max(id) from constanciatiempo", nativeQuery = true)
    @Transactional
    Integer selectMaxIdConstanciaTiempo();

    boolean existsByJustificante_IdJustificante (int id);

}
