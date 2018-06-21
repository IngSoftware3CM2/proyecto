package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.model.ConstanciaTiempoModel;
import com.is.controlincidencias.repository.ConstanciaTiempoRepository;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.service.ConstanciaTiempoService;
import com.is.controlincidencias.service.IncidenciaService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("constanciaTiempoServiceImpl")

public class ConstanciaTiempoServiceImpl implements ConstanciaTiempoService{
    private static final Log LOG = LogFactory.getLog(ConstanciaTiempoServiceImpl.class);

    @Autowired
    @Qualifier("constanciaTiempoRepository")
    private ConstanciaTiempoRepository constanciaTiempoRepository;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Override
    public void guardarConstanciaTiempo(ConstanciaTiempoModel constanciaTiempoModel,int idIncidencia, int noEmpleado) {
        Date fecha = new Date();
        //Esta cosa deberia de cambiar dependiendo el empleado que esta en el sistema
        justificanteRepository.altaJustificante("Espera",fecha,9,noEmpleado);
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        Integer id = constanciaTiempoRepository.selectMaxIdConstanciaTiempo();
        if (id==null){
            id=1;
        }
        else{
            id += 1;
        }
        constanciaTiempoRepository.altaConstanciaTiempo(id,constanciaTiempoModel.getConstanciaArchivo(),constanciaTiempoModel.getSegfecha(),constanciaTiempoModel.getTipo(),ids.get(ids.size()-1));
        incidenciaService.updateIdJustificante(ids.get(ids.size()-1),idIncidencia);
    }

    @Override
    public boolean existByidjustificante(int id) {
        return constanciaTiempoRepository.existsByJustificante_IdJustificante(id);
    }
}
