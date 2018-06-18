package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.ComisionOficial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Repository("comisionRepository")

public interface ComisionRepository extends JpaRepository<ComisionOficial, Serializable> {

    @Modifying
    @Transactional
    @Query(value = "insert into comisionoficial (fechafin, fechainicio, invitacionarchivo, idjustificante) values (:fin, :inicio, :archivo, :idj)", nativeQuery = true)
    void addComision(@Param("fin") LocalDate fin, @Param("inicio") LocalDate inicio, @Param("archivo") String archivo, @Param("idj") int idj);
    boolean existsByJustificante_IdJustificante (int id);
}
