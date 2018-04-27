package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("incidenciaRepository")
public interface IncidenciaRepository extends JpaRepository<Incidencia, Serializable> {
<<<<<<< HEAD

    public abstract Incidencia findByIdIncidencia(int id);

=======
    List <Incidencia> findAllByPersonal (Personal personal);
>>>>>>> f6b5f8322aa0b43614846410c8d135a4ef08ce17
}
