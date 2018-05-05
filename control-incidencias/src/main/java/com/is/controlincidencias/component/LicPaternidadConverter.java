package com.is.controlincidencias.component;

import com.is.controlincidencias.entity.LicPaternidad;
import com.is.controlincidencias.model.LicPaternidadModel;
import org.springframework.stereotype.Component;

@Component("licPaternidadComponent")

public class LicPaternidadConverter {
    public LicPaternidadModel LicPaternidad2LicPaternidadModel(LicPaternidad licPaternidad){
        LicPaternidadModel licPaternidadModel = new LicPaternidadModel();
        //licPaternidadModel.setJustificante(licPaternidad.getJustificante());
        licPaternidadModel.setActamatrimonio(licPaternidad.getActamatrimonio());
        licPaternidadModel.setJustificacion(licPaternidad.getJustificacion());
        licPaternidadModel.setActanacimiento(licPaternidad.getActanacimiento());
        licPaternidadModel.setComprobanteingresos(licPaternidad.getComprobanteingresos());
        licPaternidadModel.setCopiaidentificacion(licPaternidad.getCopiaidentificacion());
        licPaternidadModel.setRegistrolicencia(licPaternidad.getRegistrolicencia());
        licPaternidadModel.setConstanciacurso(licPaternidad.getConstanciacurso());
        return licPaternidadModel;
    }

    public LicPaternidad LicPaternidadModel2LicPaternidad(LicPaternidadModel licPaternidadModel){
        LicPaternidad licPaternidad = new LicPaternidad();
        //licPaternidad.setJustificante(licPaternidadModel.getJustificante());
        licPaternidad.setActamatrimonio(licPaternidadModel.getActamatrimonio());
        licPaternidad.setJustificacion(licPaternidadModel.getJustificacion());
        licPaternidad.setActanacimiento(licPaternidadModel.getActanacimiento());
        licPaternidad.setComprobanteingresos(licPaternidadModel.getComprobanteingresos());
        licPaternidad.setCopiaidentificacion(licPaternidadModel.getCopiaidentificacion());
        licPaternidad.setRegistrolicencia(licPaternidadModel.getRegistrolicencia());
        licPaternidad.setConstanciacurso(licPaternidadModel.getConstanciacurso());
        return licPaternidad;
    }
}
