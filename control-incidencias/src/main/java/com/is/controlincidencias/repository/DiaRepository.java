package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("diaRepository")
public interface DiaRepository extends JpaRepository<Dia, Serializable> {
    List<Dia> findDiaByHorarioActualIdHorario(Integer id);

    Dia findFirstByHorarioActual_IdHorarioAndNombre(Integer id, String nombre);
}
