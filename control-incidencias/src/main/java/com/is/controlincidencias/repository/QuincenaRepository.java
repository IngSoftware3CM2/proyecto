package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Quincena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;

public interface QuincenaRepository extends JpaRepository<Quincena, Serializable> {
    @Query(value="select idquincena from quincena where :fecharegistro  between inicio and fin;", nativeQuery = true)
    @Transactional
    Integer obtenerIdQuincena(@Param("fecharegistro") LocalDate fecharegistro);

}
