package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.PeriodoInhabil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDate;

@Repository("periodoInhabilRepository")
public interface PeriodoInhabilRepository extends JpaRepository<PeriodoInhabil, Serializable> {
    boolean existsByInicioIsLessThanEqualAndFinGreaterThanEqual(LocalDate fecha, LocalDate fecha2);
}
