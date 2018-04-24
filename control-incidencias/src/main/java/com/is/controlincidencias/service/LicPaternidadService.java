package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.IncidenciaModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LicPaternidadService {
    public void subirArchivo(List<MultipartFile> file) throws IOException;
    public abstract Justificante consultarJustificante(int idIncidencia);
}
