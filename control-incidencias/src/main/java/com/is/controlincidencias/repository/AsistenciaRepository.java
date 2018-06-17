package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    @Query(value = "select distinct(extract(year from fecharegistro)) as anio from asistencia where idempleado=:id order by anio desc", nativeQuery = true)
    @Transactional
    List<Double> obtenerDiferentesAnios(@Param("id") Integer id);

    List<Asistencia> findAllByPersonalNoTarjetaAndFechaRegistroBetween(String tarjeta, LocalDate
            inicio, LocalDate termino);

    boolean existsAsistenciasByPersonalNoTarjetaAndFechaRegistroBetween(String tarjeta, LocalDate
            inicio, LocalDate termino);
}
