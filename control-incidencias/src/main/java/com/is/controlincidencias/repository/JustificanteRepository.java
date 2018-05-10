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


    List<Justificante> findAllByPersonal (Personal personal);

    @Modifying
    @Transactional
    @Query (value="delete from justificante where idjustificante=:id", nativeQuery = true)
    void removeJustificanteByIdJustificante(@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value="SELECT idjustificante FROM justificante", nativeQuery = true)
    List<Integer> ultimoJustificanteAnadido();

    @Modifying
    @Query(value = "insert into justificante (estado,fecha,tipo,noempleado) VALUES (:estado,:fecha,:tipo,:noempleado)", nativeQuery = true)
    @Transactional
    void altaJustificante(@Param("estado") String estado, @Param("fecha") Date fecha, @Param("tipo") int tipo, @Param("noempleado") int noempleado);


}
