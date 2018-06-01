package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.converter.StringToLocalDate;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.TipoA;
import com.is.controlincidencias.model.JustificanteTAModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.JustificanteTARepository;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.JustificanteTAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service("taServiceImpl")
public class JustificanteTAServiceImpl implements JustificanteTAService{

    @Autowired
    @Qualifier("justificanteTARepository")
    private JustificanteTARepository justificanteTARepository;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Override
    public String findNoTarjetaByIdEmpleado(int idEmpleado) {
        return Integer.toString(justificanteTARepository.findNotarjetaByIdempleado(idEmpleado));
    }

    @Override
    public int saveJustificanteTA(JustificanteTAModel justificanteTAModel, Justificante justificante,int idIncidencia) {
        Date fecha = new Date();
        //Aqui cambia dependiendo el No empleado.
        int idEmpleado=justificante.getPersonal().getIdEmpleado();
        justificanteRepository.altaJustificante("Espera",fecha,1,idEmpleado);
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        LocalDate fechaFin = justificanteTAModel.getFin();
        LocalDate fechaInicio = justificanteTAModel.getInicio();
        int idJustificante =ids.get(ids.size()-1);
        justificanteTARepository.saveJustificanteTA(fechaFin,justificanteTAModel.getFolio(),fechaInicio,idJustificante+"_"+justificanteTAModel.getLicenciaArchivo(),justificanteTAModel.getTipo(),ids.get(ids.size()-1),justificanteTAModel.getIdunidadmedica());
        incidenciaService.updateIdJustificante(ids.get(ids.size()-1),idIncidencia);
        return idJustificante;
    }
    @Override
    public List<String> findZonas() {
        return justificanteTARepository.findZonas();
    }

    @Override
    public TipoA findByJustificante(Justificante justificante) {
        return justificanteTARepository.findByJustificante(justificante);
    }

    @Override
    public boolean existsByIdjustificante(int id) {
        return justificanteTARepository.existsByJustificante_IdJustificante(id);
    }
}
