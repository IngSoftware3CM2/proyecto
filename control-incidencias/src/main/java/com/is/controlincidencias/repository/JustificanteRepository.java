package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository("justificanteRepository")
public interface JustificanteRepository extends JpaRepository <Justificante, Serializable> {

    Justificante findByIdJustificante(int id);


    @Query(value = "select * from justificante", nativeQuery = true)
    @Transactional
    List <Justificante> selectAllJustificante ();


    List<Justificante> findAllByPersonal (Personal personal);

    @Query(value = "select max(idjustificante) from justificante", nativeQuery = true)
    @Transactional
    Integer selectMaxIdPermisoEconomico();

    @Modifying
    @Transactional
    @Query (value="delete from justificante where idjustificante=:id", nativeQuery = true)
    void removeJustificanteByIdJustificante(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value="SELECT idjustificante FROM justificante", nativeQuery = true)
    List<Integer> ultimoJustificanteAnadido();

    @Modifying
    @Query(value = "insert into justificante (estado,fecha,tipo,idempleado) VALUES (:estado,:fecha,:tipo,:idempleado)", nativeQuery = true)
    @Transactional
    void altaJustificante(@Param("estado") int estado, @Param("fecha") Date fecha, @Param("tipo") int tipo, @Param("idempleado") int idempleado);

    @Modifying
    @Query(value = "update justificante set estado=:estado where idjustificante=:id", nativeQuery = true)
    @Transactional
    void cambiarEstadoJustificante( @Param("id") Integer id, @Param("estado") Integer estado);
}
