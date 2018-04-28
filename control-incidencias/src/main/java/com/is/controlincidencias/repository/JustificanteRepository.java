package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

@Repository("justificanteRepository")
public interface JustificanteRepository extends JpaRepository <Justificante, Serializable> {

    public abstract Justificante findByIdJustificante(int id);


    List<Justificante> findAllByPersonal (Personal personal);

    @Transactional
    void removeJustificanteByIdJustificante (int id);


    @Modifying
    @Transactional
    @Query (value="delete from justificante where idjustificante=:id", nativeQuery = true)
    void eliminar (@Param("id") int id);

    @Modifying
    @Query(value = "insert into justificante (idjustificante,estado,fecha,noempleado) VALUES (:idjustificante,:estado,:fecha,:noempleado)", nativeQuery = true)
    @Transactional
    public void altaLicPaternidad(@Param("idjustificante") int idjustificante, @Param("estado") String estado, @Param("fecha") String fecha, @Param("noempleado") int noempleado);


}
