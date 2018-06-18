package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.PeriodoInhabil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

@Repository("periodoInhabilRepository")
public interface PeriodoInhabilRepository extends JpaRepository<PeriodoInhabil, Serializable> {

    boolean existsByInicioIsLessThanEqualAndFinGreaterThanEqual(LocalDate fecha, LocalDate
            fecha2);

    @Query(value = "select max(idperiodo) from periodoinhabil", nativeQuery = true)
    @Transactional
    Integer selectMaxIdPeriodoInhabil();

    @Modifying
    @Query(value = "UPDATE periodoinhabil SET justificacionarchivo = :nombrearchivo where  idperiodo =:idperiodo", nativeQuery = true)
    @Transactional
    void updatePeriodoInhabil(@Param("nombrearchivo") String nombreArchivo, @Param("idperiodo") int idperiodo);




}
