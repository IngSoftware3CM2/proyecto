package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Motivo;
import com.is.controlincidencias.entity.Notificacion;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

@Repository("notificacionRepository")
public interface NotificacionRepository extends JpaRepository<Notificacion, Serializable> {
    boolean existsByPersonal (Personal personal);
    boolean existsByPersonal_IdEmpleado(int id);
    Notificacion findByPersonal_IdEmpleado(int id);
    Notificacion findByPersonal (Personal personal);

    @Modifying
    @Transactional
    @Query (value="delete from notificacion where idmotivo=:idmotivo and idempleado =:idempleado", nativeQuery = true)
    void deleteByPersonalAndMotivo(@Param("idmotivo") int idMotivo, @Param("idempleado") int idEmpleado);

    @Query(value = "select max(id) from notificacion", nativeQuery = true)
    @Transactional
    Integer selectMaxIdPersonalQuincena();

    @Modifying
    @Query(value = "insert into notificacion (id, archivo, fecha, idmotivo, idempleado) VALUES (:id, :archivo, :fecha, :idmotivo, :idempleado)", nativeQuery = true)
    @Transactional
    void insertNotificacion(@Param("id") int id, @Param("archivo") String archivo, @Param("fecha") Date fecha, @Param("idmotivo") int idmotivo, @Param("idempleado") int idempleado);

    @Query(value = "select count(*) from notificacion where idempleado = :idEmpleado", nativeQuery = true)
    @Transactional
    Integer soloUnaNotificacion(@Param("idEmpleado") int idEmpleado);

    @Query(value = "select * from notificacion where idempleado = :idEmpleado", nativeQuery = true)
    @Transactional
    Notificacion selectNotificacion(@Param("idEmpleado") int idEmpleado);

}
