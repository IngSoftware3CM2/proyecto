package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.component.LicPaternidadConverter;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.LicPaternidadModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.LicPaternidadRepository;
import com.is.controlincidencias.service.LicPaternidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service("licPaternidadServiceImpl")

public class LicPaternidadServiceImpl implements LicPaternidadService{

    @Autowired
    @Qualifier("licPaternidadRepository")
    private LicPaternidadRepository licPaternidadRepository;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Autowired
    @Qualifier("licPaternidadComponent")
    private LicPaternidadConverter licPaternidadConverter;

    private String ruta_archivos = ".//src//main//resources//files//";


    public Justificante consultarJustificante(int id) {
        return justificanteRepository.findByIdJustificante(id);
    }

    @Override
    public void guardarLicPaternidad(LicPaternidadModel licPaternidadModel, Justificante justificante) {
        //necesito hacer la conversion
        //LicPaternidad licPaternidad = licPaternidadConverter.LicPaternidadModel2LicPaternidad(licPaternidadModel);
        //licPaternidad.setJustificante(justificante);
        //licPaternidadRepository.savelicPaternidad);
        licPaternidadRepository.altaLicPaternidad(justificante.getIdJustificante(), licPaternidadModel.getActamatrimonio(), licPaternidadModel.getActanacimiento(), licPaternidadModel.getComprobanteingresos(), licPaternidadModel.getConstanciacurso(), licPaternidadModel.getCopiaidentificacion(), licPaternidadModel.getJustificacion(), licPaternidadModel.getRegistrolicencia());
    }

     @Override
    public Incidencia consultarIncidencia(int idIncidencia) {
        return licPaternidadRepository.findById(idIncidencia);
    }

    @Override
    public void subirArchivo(List<MultipartFile> files) throws IOException {
        for(MultipartFile file: files) {
            if(file.isEmpty()) continue;
            byte[] bytes = file.getBytes();
            Path path = Paths.get(ruta_archivos + file.getOriginalFilename());
            Files.write(path,bytes);
        }

    }

}
