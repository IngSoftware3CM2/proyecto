package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.TiempoSuplGenerado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("tiempoSuplGeneradoRepository")
public interface TiempoSuplGeneradoRepository extends JpaRepository<TiempoSuplGenerado,Serializable> {
    List<TiempoSuplGenerado> findAllByPersonal(Personal personal);
}
