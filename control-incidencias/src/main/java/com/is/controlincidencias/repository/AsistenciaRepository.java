package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.time.LocalDate;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Serializable> {
    boolean existsAsistenciaByFechaRegistroAndPersonalNoTarjeta(LocalDate fecha, String noTarjeta);
    Asistencia findAsistenciaByFechaRegistroAndPersonalNoTarjeta(LocalDate fecha, String
            noTarjeta);
}
