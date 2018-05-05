package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.LicPaternidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository("licPaternidadRepository")
public interface LicPaternidadRepository extends JpaRepository<LicPaternidad, Serializable> {

    @Modifying
    @Query(value = "insert into licpaternidad (idjustificante, actamatrimonio, actanacimiento, comprobanteingresos, constanciacurso, copiaidentificacion, justificacion, registrolicencia) VALUES (:idjustificante, :actamatrimonio, :actanacimiento, :comprobanteingresos, :constanciacurso, :copiaidentificacion, :justificacion, :registrolicencia)", nativeQuery = true)
    @Transactional
    public void altaLicPaternidad(@Param("idjustificante") int idjustificante, @Param("actamatrimonio") String actamatrimonio, @Param("actanacimiento") String actanacimiento, @Param("comprobanteingresos") String comprobanteingresos, @Param("constanciacurso") String constanciacurso, @Param("copiaidentificacion") String copiaidentificacion, @Param("justificacion") String justificacion, @Param("registrolicencia") String registrolicencia);

    boolean existsByJustificante_IdJustificante (int id);

    public LicPaternidad findById(int id);

}
