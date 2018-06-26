package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.CambioHorario;
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
    @Query(value = "insert into cambiohorario (fecha, horaentrada, horasalida, justificacion, idjustificante) VALUES (:fecha, :horaentrada, :horasalida, :justificacion, :idjustificante)", nativeQuery = true)
    void guardaJustificanteCH(@Param("fecha") LocalDate fecha, @Param("horaentrada") Time horaentrada, @Param("horasalida") Time horaSalida, @Param("justificacion") String justificacion, @Param("idjustificante") int idjustificante);


    @Transactional
    @Query(value = "select * from cambiohorario c where c.idjustificante =  :idjustificante", nativeQuery = true)
    CambioHorario getIdCambioHorario(@Param("idjustificante") int idjustificante);

    @Modifying
    @Transactional
    @Query(value = "update cambiohorario set justificacion = :justificacion where idjustificante = :idjustificante", nativeQuery = true)
    void updateCambioHorario(@Param("justificacion") String justificacion, @Param("idjustificante") int idjustificante);


    @Transactional
    @Query(value = "select idjustificante from justificante where idempleado = :idempleado", nativeQuery = true)
    int getIdJustificanteByIdEmpleado(@Param("idempleado") int idempleado);

    @Transactional
    @Query(value = "select idempleado from incidencia where idincidencia = :idincidencia", nativeQuery = true)
    int getIdEmpleadoByIdIncidencia(@Param("idincidencia") int idincidencia);

    boolean existsByJustificante_IdJustificante(int id);

    @Transactional
    @Query(value = "select horaentrada from asistencia where idempleado = :idempleado and fecharegistro = :fecha",nativeQuery = true)
    String getHoraEntrada(@Param("idempleado") int idempleado, @Param("fecha") LocalDate fecha);

    @Transactional
    @Query(value = "select horasalida from asistencia where idempleado = :idempleado and fecharegistro = :fecha",nativeQuery = true)
    String getHoraSalida(@Param("idempleado") int idempleado, @Param("fecha") LocalDate fecha);

    @Transactional
    @Query(value = "select horaentrada from dia where idhorario =  (select idhorario from personal where idempleado = :idempleado) and nombre = :dia",nativeQuery = true)
    String getHoraE(@Param("idempleado") int idempleado, @Param("dia") String dia);

    @Transactional
    @Query(value = "select horasalida from dia where idhorario =  (select idhorario from personal where idempleado = :idempleado) and nombre = :dia",nativeQuery = true)
    String getHoraS(@Param("idempleado") int idempleado, @Param("dia") String dia);

    @Transactional
    @Query(value = "select nombre from departamento where iddepartamento = :idd", nativeQuery = true)
    String getDepto(@Param("idd") int idd);

}