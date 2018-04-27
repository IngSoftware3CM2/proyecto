package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.LicPaternidadModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LicPaternidadService {
     void subirArchivo(List<MultipartFile> file) throws IOException;
    Incidencia consultarIncidencia(int idIncidencia);
    void guardarLicPaternidad(LicPaternidadModel licPaternidadModel, Justificante justificante);
}
