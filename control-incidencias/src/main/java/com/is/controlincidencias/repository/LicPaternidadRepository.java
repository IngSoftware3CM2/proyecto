package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.LicPaternidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;

@Repository("licPaternidadRepository")
public interface LicPaternidadRepository extends JpaRepository<LicPaternidad, Serializable> {

    @Modifying
    @Query(value = "insert into licpaternidad (idjustificante, actamatrimonio, actanacimiento, comprobanteingresos, constanciacurso, copiaidentificacion, justificacion, registrolicencia) VALUES (:idjustificante, :actamatrimonio, :actanacimiento, :comprobanteingresos, :constanciacurso, :copiaidentificacion, :justificacion, :registrolicencia)", nativeQuery = true)
    @Transactional
    void altaLicPaternidad(@Param("idjustificante") int idjustificante, @Param("actamatrimonio") String actamatrimonio, @Param("actanacimiento") String actanacimiento, @Param("comprobanteingresos") String comprobanteingresos, @Param("constanciacurso") String constanciacurso, @Param("copiaidentificacion") String copiaidentificacion, @Param("justificacion") String justificacion, @Param("registrolicencia") String registrolicencia);

    boolean existsByJustificante_IdJustificante (int id);

    LicPaternidad findById(int id);

    @Query(value = "select * from licpaternidad where idjustificante=:idjustificante", nativeQuery = true)
    @Transactional
    LicPaternidad selectByIdjustificante(@Param("idjustificante") int idjustificante);


    @Modifying
    @Query(value = "UPDATE licpaternidad SET justificacion = :justificacion, actamatrimonio=:actamatrimonio, actanacimiento=:actanacimiento,comprobanteingresos=:comprobanteingresos, constanciacurso=:constanciacurso, copiaidentificacion=:copiaidentificacion,registrolicencia=:registrolicencia where  idjustificante = :idjustificante", nativeQuery = true)
    @Transactional
    public void updateLicPaternidad(@Param("idjustificante") int idjustificante,@Param("justificacion") String justificacion, @Param("actamatrimonio") String actamatrimonio, @Param("actanacimiento") String actanacimiento, @Param("comprobanteingresos") String comprobanteingresos, @Param("constanciacurso") String constanciacurso, @Param("registrolicencia") String registrolicencia);
}
