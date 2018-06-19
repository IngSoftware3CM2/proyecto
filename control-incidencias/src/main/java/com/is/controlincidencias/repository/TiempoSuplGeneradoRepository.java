package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.TiempoSuplGenerado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository("tiempoSuplGeneradoRepository")
public interface TiempoSuplGeneradoRepository extends JpaRepository<TiempoSuplGenerado,Serializable> {

    TiempoSuplGenerado findByIdtiemposuplgenerado(Integer id);

    @Modifying
    @Transactional
    @Query(value="select * from tiemposuplgenerado where idEmpleado= :noempleado and usado=false and fechaRegistro >= :fecha order by fechaRegistro ASC ", nativeQuery = true)
    List<TiempoSuplGenerado> findAllByPersonal(@Param("noempleado") int idempleado, @Param("fecha")LocalDate fecha);

    //UPDATE films SET kind = 'Dramatic' WHERE kind = 'Drama';
    @Modifying
    @Transactional
    @Query(value="update tiemposuplgenerado set usado = :usado, horas = :horas where idtiemposuplgenerado= :idtiempo", nativeQuery = true)
    int updateTiempoSuplGenerado(@Param("idtiempo") int idtiempo, @Param("usado") boolean usado, @Param("horas")LocalTime horas);


    @Modifying
    @Query(value = "insert into tiemposuplgenerado (idtiemposuplgenerado, fecharegistro, horas, usado, idempleado) values (:id, :fecha, :horas, :usado, :empleado)", nativeQuery = true)
    @Transactional
    void registrarSuplementario(@Param("id") Integer id, @Param("fecha") LocalDate fecha,
            @Param("horas") LocalTime horas, @Param("usado") Boolean usado,
            @Param("empleado") Integer idEmpleado);

    @Query(value = "select max(idtiemposuplgenerado) from tiemposuplgenerado", nativeQuery = true)
    @Transactional
    Integer obtenerMaximoIdIncidencia();

    boolean existsByFechaRegistroAndPersonal_IdEmpleado(LocalDate fecha, Integer id);
}
