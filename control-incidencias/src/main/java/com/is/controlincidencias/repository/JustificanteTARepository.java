package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.TipoA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Repository("justificanteTARepository")
public interface JustificanteTARepository extends JpaRepository<TipoA,Serializable> {

    @Transactional
    @Query(value = "SELECT t.folio FROM tipoa t WHERE idjustificante = :id", nativeQuery = true)
    List <String> findByIdJustificante (@Param("id") int id);

    @Modifying
    @Transactional
    @Query(value="insert into tipoa (fechafin, folio, fechainicio, licenciaarchivo, tipo, idjustificante, idunidad) VALUES (:fechafin, :folio, :fechainicio, :licenciaarchivo, :tipo, :idjustificante, :idunidad)", nativeQuery = true)
    void saveJustificanteTA(@Param("fechafin") LocalDate fechafin, @Param("folio") String folio, @Param("fechainicio") LocalDate fechainicio, @Param("licenciaarchivo") String licenciaarchivo, @Param("tipo") String tipo, @Param("idjustificante") int idjustificante, @Param("idunidad") String idunidad);

    @Modifying
    @Transactional
    @Query(value="select notarjeta from personal where noempleado= :noempleado", nativeQuery = true)
    int findNotarjetaByNoempleado(@Param("noempleado") int noempleado);

    @Modifying
    @Transactional
    @Query(value="select nombre from zona ", nativeQuery = true)
    List<String> findZonas();
}
