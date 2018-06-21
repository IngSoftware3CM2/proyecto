package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.LicPaternidad;
import com.is.controlincidencias.model.LicPaternidadModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface LicPaternidadService {
    LicPaternidad buscarLicPaternidad(int idJustificante);

    void subirArchivo(List<MultipartFile> file, int noEmpleado) throws IOException;
    void subirArchivo(List<MultipartFile> file, int noEmpleado,String ruta) throws IOException;
    int guardarLicPaternidad(LicPaternidadModel licPaternidadModel, int idIncidencia, int noEmpleado);
    LicPaternidad buscarLicPaternidadPorIdjustificante(int idJustificante);
    boolean existsByIdjustificante (int id);
    void borrarArchivo(String archivo);
    void updateLicPaternidad(LicPaternidad licPaternidad, int idjustificante);
}