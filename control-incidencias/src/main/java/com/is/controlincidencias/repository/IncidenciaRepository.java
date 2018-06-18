package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Repository("incidenciaRepository")
public interface IncidenciaRepository extends JpaRepository<Incidencia, Serializable> {

    Incidencia findByIdIncidencia(int id);

    List <Incidencia> findAllByPersonal (Personal personal);


    @Modifying
    @Query(value = "UPDATE incidencia SET idjustificante = :idjustificante where  idincidencia = :idincidencia", nativeQuery = true)
    @Transactional
    public void updateIdJustificante(@Param("idjustificante") int idjustificante, @Param("idincidencia") int idincidencia);

    @Transactional
    @Query(value = "select idempleado from incidencia where idjustificante = :idjustificante", nativeQuery = true)
    public int getIdEmpleadoByIdJustificante(@Param("idjustificante") int idjustificante);

    @Modifying
    @Query(value = "insert into incidencia (idincidencia, fecharegistro, tipo, idempleado, horasfaltantes) values (:id, :fecha, :tipo, :empleado, :horas)", nativeQuery = true)
    @Transactional
    void insertarAsistencia(@Param("id") Integer id, @Param("fecha") LocalDate fecha,
            @Param("tipo") String tipo, @Param("empleado") Integer empleado,
            @Param("horas") Integer horas);

    @Query(value = "select max(idincidencia) from incidencia", nativeQuery = true)
    @Transactional
    Integer obtenerMaximoIdAsistencia();
}
