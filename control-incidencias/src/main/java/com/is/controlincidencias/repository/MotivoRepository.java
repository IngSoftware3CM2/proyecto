package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("motivoRepository")
public interface MotivoRepository extends JpaRepository<Motivo,Serializable> {
    Motivo findByIdMotivo(int idMotivo);
}
