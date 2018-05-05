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
import java.util.List;

@Repository("incidenciaRepository")
public interface IncidenciaRepository extends JpaRepository<Incidencia, Serializable> {

    public abstract Incidencia findByIdIncidencia(int id);

    List <Incidencia> findAllByPersonal (Personal personal);


    @Modifying
    @Query(value = "UPDATE incidencia SET idjustificante = :idjustificante where  idincidencia = :idincidencia", nativeQuery = true)
    @Transactional
    public void updateIdJustificante(@Param("idjustificante") int idjustificante, @Param("idincidencia") int idincidencia);


}
