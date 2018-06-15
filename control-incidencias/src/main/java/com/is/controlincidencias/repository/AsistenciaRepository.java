package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Serializable> {
    boolean existsAsistenciaByFechaRegistroAndPersonalNoTarjeta(LocalDate fecha, String noTarjeta);
    Asistencia findAsistenciaByFechaRegistroAndPersonalNoTarjeta(LocalDate fecha, String
            noTarjeta);
    Asistencia findByIdAsistencia(Integer id);
    List<Asistencia> findAllByFechaRegistro(LocalDate fecha);
    @Query(value = "select distinct(extract(year from fecharegistro)) from asistencia where idempleado=:idjustificante", nativeQuery = true)
    @Transactional
    List<String> obtenerDiferentesAnios(Integer id);
}
