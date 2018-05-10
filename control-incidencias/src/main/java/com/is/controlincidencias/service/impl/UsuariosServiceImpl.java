package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Departamento;
import com.is.controlincidencias.model.DepartamentoForm;
import com.is.controlincidencias.repository.DepartamentoRepository;
import com.is.controlincidencias.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("usuariosServiceImpl")
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    @Qualifier("departamentoRepository")
    private DepartamentoRepository departamentoRepository;

    @Override
    public List<DepartamentoForm> recuperarDepartamentos() {
        List<Departamento> departamentos = departamentoRepository.findAll();
        List<DepartamentoForm> opciones = new ArrayList<>();

        for (Departamento d : departamentos)
            opciones.add(new DepartamentoForm(d.getIdDepartamento(), d.getNombre()));

        return opciones;
    }
}
