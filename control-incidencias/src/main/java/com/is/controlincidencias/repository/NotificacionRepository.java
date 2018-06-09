package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Motivo;
import com.is.controlincidencias.entity.Notificacion;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository("notificacionRepository")
public interface NotificacionRepository extends JpaRepository<Notificacion, Serializable> {
    boolean existsByPersonal (Personal personal);
    Notificacion findByPersonal (Personal personal);
}
