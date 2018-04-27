package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository("justificanteRepository")
public interface JustificanteRepository extends JpaRepository <Justificante, Serializable> {

<<<<<<< HEAD
    public abstract Justificante findByIdJustificante(int id);


=======
    Justificante findByIdJustificante (int id);
    List<Justificante> findAllByPersonal (Personal personal);
>>>>>>> f6b5f8322aa0b43614846410c8d135a4ef08ce17
}
