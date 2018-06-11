package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.PersonalQuincena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface PersonalQuincenaRepository  extends JpaRepository<PersonalQuincena,Serializable> {

    @Query(value="select diaseconomicossolicitados from personalquincena where idempleado =:idempleado and idquincena=:idquincena", nativeQuery = true)
    @Transactional
    Integer preguntarPorDiasQuincena(@Param("idempleado") int idempleado, @Param("idquincena") int idquincena);

    @Query(value="select justificacionessuplementario from personalquincena where idempleado =:idempleado and idquincena=:idquincena", nativeQuery = true)
    @Transactional
    Integer preguntarPorDiasSuplementariosQuincena(@Param("idempleado") int idempleado, @Param("idquincena") int idquincena);

    @Query(value="select sum(diaseconomicossolicitados) from personalquincena where idempleado =:idempleado", nativeQuery = true)
    @Transactional
    Integer preguntarPorDiasAno(@Param("idempleado") int idempleado);

    @Modifying
    @Query(value = "UPDATE personalquincena SET justificacionessuplementario = :tiemposuplementarios where  idempleado =:idempleado and idquincena = :idquincena", nativeQuery = true)
    @Transactional
    void updateSuplementarioQuincena(@Param("tiemposuplementarios") int tiempossuplsolicitados,@Param("idempleado") int idempleado,@Param("idquincena") int idquincena);


    @Modifying
    @Query(value = "UPDATE personalquincena SET diaseconomicossolicitados = :diaseconomicossolicitados where  idempleado =:idempleado and idquincena = :idquincena", nativeQuery = true)
    @Transactional
    void updateQuincena(@Param("diaseconomicossolicitados") int diaseconomicossolicitados,@Param("idempleado") int idempleado,@Param("idquincena") int idquincena);

    @Query(value = "select max(idpersonalquincena) from personalquincena", nativeQuery = true)
    @Transactional
    Integer selectMaxIdPersonalQuincena();


    @Modifying
    @Query(value = "insert into personalquincena (idpersonalquincena, diaseconomicossolicitados,idempleado,idquincena) VALUES (:idpersonalquincena, :diaseconomicossolicitados, :idempleado, :idquincena)", nativeQuery = true)
    @Transactional
    void insertRegistro(@Param("idpersonalquincena") int idpersonalquincena,@Param("diaseconomicossolicitados") int diaseconomicossolicitados,@Param("idempleado") int idempleado,@Param("idquincena") int idquincena);

    @Modifying
    @Query(value = "insert into personalquincena (idpersonalquincena,justificacionessuplementario,idempleado,idquincena) VALUES (:idpersonalquincena,:tiemposuplsolicitados, :idempleado, :idquincena)", nativeQuery = true)
    @Transactional
    void insertRegistroSupl(@Param("idpersonalquincena") int idpersonalquincena,@Param("tiemposuplsolicitados") int tiemposuplsolicitados,@Param("idempleado") int idempleado,@Param("idquincena") int idquincena);

}
