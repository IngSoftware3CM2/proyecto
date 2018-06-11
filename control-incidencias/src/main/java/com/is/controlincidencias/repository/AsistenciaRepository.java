package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Serializable> {
    boolean existsAsistenciaByFechaRegistroAndPersonalNoTarjeta(LocalDate fecha, String noTarjeta);
    Asistencia findAsistenciaByFechaRegistroAndPersonalNoTarjeta(LocalDate fecha, String
            noTarjeta);
    Asistencia findByIdAsistencia(Integer id);
    List<Asistencia> findAllByFechaRegistro(LocalDate fecha);
}
