package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.UnidadMedica;
import com.is.controlincidencias.entity.Zona;
import com.is.controlincidencias.repository.UnidadMedicaRepository;
import com.is.controlincidencias.service.UnidadMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("unidadMedicaServiceImpl")
public class UnidadMedicaServiceImpl implements UnidadMedicaService {
    @Autowired
    @Qualifier("unidadMedicaRepository")
    private UnidadMedicaRepository unidadMedicaRepository;

    @Override
    public List<UnidadMedica> getunidadesMedicasByZona(Zona zona) {
        return unidadMedicaRepository.findByZona(zona);
    }

    @Override
    public UnidadMedica getUnidadMedicaByNombre(String nombre) {
        return unidadMedicaRepository.findByNombre(nombre);
    }
}
