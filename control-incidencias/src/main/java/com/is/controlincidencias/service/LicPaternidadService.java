package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.LicPaternidad;
import com.is.controlincidencias.model.IncidenciaModel;
import com.is.controlincidencias.model.LicPaternidadModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LicPaternidadService {
    public void subirArchivo(List<MultipartFile> file) throws IOException;
    public abstract Justificante consultarJustificante(int idIncidencia);
    public void guardarLicPaternidad(LicPaternidadModel licPaternidadModel,Justificante justificante);
}
