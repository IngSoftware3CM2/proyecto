package com.is.controlincidencias.repository;


import com.is.controlincidencias.entity.OmisionEntrSal;
import com.is.controlincidencias.entity.Retardo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository("RetardoRepository")


public interface RetardoRepository extends JpaRepository<Retardo, Serializable> {


    @Modifying
    @Transactional
    @Query(value = "insert into retardo (justificacion,  idjustificante) VALUES (:justificacion, :idjustificante)", nativeQuery = true)
    void guardaJustificanteRetardo(@Param("justificacion") String justificacion, @Param("idjustificante") int idjustificante);


    @Transactional
    @Query(value = "select justificacion from retardo where idjustificante = :idjustificante", nativeQuery = true)
    String getJustificacion(@Param("idjustificante") int idjustificante);


    @Transactional
    @Query(value = "select coalesce(to_char(fecha, 'YYYY-MM-DD'), '') as fecha from justificante j where j.idjustificante = :idjustificante", nativeQuery = true)
    String getFecha(@Param("idjustificante") int idjustificante);

    @Modifying
    @Transactional
    @Query(value = "update retardo set justificacion = :justificacion where idjustificante = :idjustificante", nativeQuery = true)
    void updateRetardo(@Param("justificacion") String justificacion, @Param("idjustificante") int idjustificante);


}
