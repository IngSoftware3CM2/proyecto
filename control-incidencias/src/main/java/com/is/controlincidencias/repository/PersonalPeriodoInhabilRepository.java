package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.PersonalPeriodoInhabil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface PersonalPeriodoInhabilRepository extends JpaRepository<PersonalPeriodoInhabil,Serializable> {
    @Query(value = "select max(idPersonalPeriodoInhabil) from personalperiodoinhabil", nativeQuery = true)
    @Transactional
    Integer selectMaxIdPersonalPeriodoInhabil();

}
