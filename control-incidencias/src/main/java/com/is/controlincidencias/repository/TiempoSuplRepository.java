package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.TiempoSuplementario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Repository("tiempoSuplRepository")
public interface TiempoSuplRepository extends JpaRepository <TiempoSuplementario,Serializable> {
    @Modifying
    @Transactional
    @Query(value="insert into tiemposuplementario (fecha,tiempocubrir,idjustificante) VALUES (:fecha, :tiempocubrir,:idjustificante)", nativeQuery = true)
    void saveJustificanteTS(@Param("fecha") LocalDate fecha, @Param("tiempocubrir") Integer tiempocubrir,@Param("idjustificante") int idjustificante);

    boolean existsByJustificante_IdJustificante (int id);


    @Query(value = "select * from tiemposuplementario where idjustificante=:idjustificante", nativeQuery = true)
    @Transactional
    TiempoSuplementario selectByIdjustificante(@Param("idjustificante") int idjustificante);
}
