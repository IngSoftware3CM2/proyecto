package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.converter.StringToLocalDate;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.JustificanteTAModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.JustificanteTARepository;
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

    @Override
    public String findNoTarjetaByNoEmpleado(int noEmpleado) {
        return Integer.toString(justificanteTARepository.findNotarjetaByNoempleado(noEmpleado));
    }

    @Override
    public void saveJustificanteTA(JustificanteTAModel justificanteTAModel, Justificante justificante) {
        Date fecha = new Date();
        String tipoModel = justificanteTAModel.getTipo();
        String tipo;
        if(tipoModel.equals("1")){
            tipo="LM";
        }else if(tipoModel.equals("2")){
            tipo="CM";
        }else{
            tipo="CF";
        }
        //Aqui cambia dependiendo el No empleado.
        int noEmpleado=justificante.getPersonal().getNoEmpleado();
        justificanteRepository.altaJustificante("Espera",fecha,noEmpleado);
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        LocalDate fechaFin = StringToLocalDate.tryParseDate(justificanteTAModel.getFin());
        LocalDate fechaInicio = StringToLocalDate.tryParseDate(justificanteTAModel.getInicio());
        justificanteTARepository.saveJustificanteTA(fechaFin,justificanteTAModel.getFolio(),fechaInicio,justificanteTAModel.getLicenciaArchivo(),tipo,ids.get(ids.size()-1),justificanteTAModel.getIdunidadmedica());
    }
    @Override
    public List<String> findZonas() {
        return justificanteTARepository.findZonas();
    }

    @Override
    public boolean existsByIdjustificante(int id) {
        return justificanteTARepository.existsByJustificante_IdJustificante(id);
    }
}
