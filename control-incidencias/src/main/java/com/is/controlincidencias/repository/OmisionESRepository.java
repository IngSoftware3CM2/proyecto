package com.is.controlincidencias.repository;


import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.entity.OmisionEntrSal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@Repository("OmisionRepository")


public interface OmisionESRepository extends JpaRepository<OmisionEntrSal, Serializable> {


    @Modifying
    @Transactional
    @Query(value = "insert into omisionentrsal (justificacion, tipo, idjustificante) VALUES (:justificacion, :tipo, :idjustificante)", nativeQuery = true)
    void guardaJustificanteOmision(@Param("justificacion") String justificacion, @Param("tipo") boolean tipo, @Param("idjustificante") int idjustificante);

}
