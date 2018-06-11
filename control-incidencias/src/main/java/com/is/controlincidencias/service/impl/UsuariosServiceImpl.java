package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.entity.Departamento;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.DepartamentoModel;
import com.is.controlincidencias.model.DocenteModel;
import com.is.controlincidencias.model.PaaeModel;
import com.is.controlincidencias.repository.DepartamentoRepository;
import com.is.controlincidencias.repository.PersonalRepository;
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
    private DepartamentoRepository deptoRepository;
    @Autowired
    @Qualifier("personalRepository")
    private PersonalRepository personalRepository;


    private static final int TIPO_PAEE = 1;

    @Override
    public List<DepartamentoModel> recuperarDepartamentos(int tipo) {
        List<Departamento> deptos;
        List<DepartamentoModel> opciones = new ArrayList<>();
        if (tipo == TIPO_PAEE)
            deptos = deptoRepository.findAllByPermisopaeeIsTrueOrPermisodocpaeeIsTrue();
        else
            deptos = deptoRepository.findAllByPermisodocenteIsTrueOrPermisodocpaeeIsTrue();

        deptos.forEach(item -> opciones
                .add(new DepartamentoModel(item.getIdDepartamento(), item.getNombre())));

        return opciones;
    }

    @Override
    public Personal registarPaee(PaaeModel modelo) {
        List<Personal> lista = personalRepository.findByTipoOrderByNoTarjeta("ROLE_PAEE");
        Personal ultimo = lista.get(lista.size()-1);
        int tarjeta = Integer.parseInt(ultimo.getNoTarjeta()) + 1;
        String notarjeta = "0" + tarjeta;
        modelo.setTarjeta(notarjeta); // generar
        modelo.setContra(generarContra(modelo.getNoEmpleado(), modelo.getApPaterno()));
        return null;
    }

    @Override
    public Personal registrarDocente(DocenteModel modelo) {
        List<Personal> lista = personalRepository
                .findByDepartamento_IdDepartamentoOrderByNoTarjeta(modelo.getDepartamento());
        Personal ultimo = lista.get(lista.size()-1);
        int tarjeta = Integer.parseInt(ultimo.getNoTarjeta()) + 1;
        String notarjeta = "0" + tarjeta;
        modelo.setTarjeta(notarjeta); // generar
        modelo.setContra(generarContra(modelo.getNoEmpleado(), modelo.getApPaterno()));

        return null;
    }

    private String generarContra(String numero, String apellido) {
        StringBuilder contra = new StringBuilder();
        contra.append(numero, 0, 3);
        if (apellido.length() < 4) {
            contra.append(apellido);
            while (contra.length() < 8)
                contra.append("0");
        } else contra.append(apellido, 0, 3);
        return contra.toString();
    }
}
