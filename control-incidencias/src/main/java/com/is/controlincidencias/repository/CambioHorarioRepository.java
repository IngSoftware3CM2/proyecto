package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.CambioHorario;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@Repository("CambioHorarioRepository")

public interface CambioHorarioRepository extends JpaRepository<CambioHorario, Serializable> {

    @Modifying
    @Transactional
    @Query(value="insert into cambiohorario (fecha, horaentrada, horasalida, justificacion) VALUES (:fecha, :horaentrada, :horasalida, :justificacion)", nativeQuery = true)
    public void guardaJustificanteCH(@Param("fecha") LocalDate fecha, @Param("horaentrada") Time horaentrada, @Param("horasalida") Time horaSalida, @Param("justificacion") String justificacion);
}