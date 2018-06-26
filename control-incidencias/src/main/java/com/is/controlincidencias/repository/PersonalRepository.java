package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository("personalRepository")
public interface PersonalRepository extends JpaRepository<Personal, Serializable> {
    boolean existsPersonalByNoTarjeta(String noTarjeta);
    Personal findFirstByNoTarjeta(String noTarjeta);
    Personal findByIdEmpleado(int idEmpleado);
    Personal findByLogin_Correo(String email);
    Personal findByLogin_CorreoAndLogin_Passwordhash(String email, String pwd);
    Personal findByNoTarjeta(String tarjeta);
    List<Personal> findByTipoOrderByNoTarjeta(String tipo);
    List<Personal> findByDepartamento_IdDepartamentoOrderByNoTarjeta(Integer id);

    @Query(value = "select sexo from personal where idempleado= :id", nativeQuery = true)
    @Transactional
    String sexoDePersonal(@Param("id") int id);

    List<Personal> findAllByTipo(String tipo);


    @Query(value="select * from personal where tipo =:tipo1 or tipo=:tipo2", nativeQuery = true)
    @Transactional
    List<Personal> findAllByAmbosTipos(@Param("tipo1") String tipo1,@Param("tipo2") String tipo2);

    Personal findFirstByJustificantes_IdJustificante(Integer id);
}
