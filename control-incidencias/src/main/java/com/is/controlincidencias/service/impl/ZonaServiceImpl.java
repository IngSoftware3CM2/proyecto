package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Zona;
import com.is.controlincidencias.repository.ZonaRepository;
import com.is.controlincidencias.service.ZonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("zonaServiceImpl")
public class ZonaServiceImpl implements ZonaService {
    @Autowired
    @Qualifier("zonaRepository")
    private ZonaRepository zonaRepository;

    @Override
    public Zona getZonabyNombre(String nombre) {
        return zonaRepository.findByNombre(nombre);
    }
}
