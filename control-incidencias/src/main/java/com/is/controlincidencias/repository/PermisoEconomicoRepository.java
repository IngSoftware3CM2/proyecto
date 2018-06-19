package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.PermisoEconomico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository("permisoEconomicoRepository")
public interface PermisoEconomicoRepository extends JpaRepository<PermisoEconomico,Serializable> {

    @Query(value = "select max(id) from permisoeconomico", nativeQuery = true)
    @Transactional
    Integer selectMaxIdPermisoEconomico();

    boolean existsByJustificante_IdJustificante (int id);

    @Modifying
    @Query(value = "insert into permisoeconomico (id, idjustificante) VALUES (:id, :idjustificante)", nativeQuery = true)
    @Transactional
    void insertRegistro(@Param("id") int id,  @Param("idjustificante") int idjustificante);

}
