package com.is.controlincidencias.converter;

import com.is.controlincidencias.entity.TipoA;
import com.is.controlincidencias.model.JustificanteTAModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("tipoAConverter")
public class TipoAConverter {
    public JustificanteTAModel entityToModel(TipoA tipoa){
        JustificanteTAModel justificanteTAModel = new JustificanteTAModel();
        justificanteTAModel.setFolio(tipoa.getFolio());
        justificanteTAModel.setTipo(tipoa.getTipo());
        justificanteTAModel.setLicenciaArchivo(tipoa.getlicenciaArchivo());
        justificanteTAModel.setInicio(tipoa.getInicio().toString());
        justificanteTAModel.setFin(tipoa.getFin().toString());
        justificanteTAModel.setIdjustificante(tipoa.getJustificante().getIdJustificante());
        justificanteTAModel.setIdunidadmedica(tipoa.getUnidadMedica().getIdUnidad());
        return justificanteTAModel;
    }

    public TipoA modelToEntity(JustificanteTAModel justificanteTAModel){
        TipoA tipoA = new TipoA();
        tipoA.setTipo(justificanteTAModel.getTipo());
        tipoA.setlicenciaArchivo(justificanteTAModel.getLicenciaArchivo());
        tipoA.setInicio(StringToLocalDate.tryParseDate(justificanteTAModel.getInicio()));
        tipoA.setFin(StringToLocalDate.tryParseDate(justificanteTAModel.getFin()));
        return tipoA;
    }

}
