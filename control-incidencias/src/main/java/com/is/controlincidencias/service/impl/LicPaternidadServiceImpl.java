package com.is.controlincidencias.service.impl;

import com.is.controlincidencias.controller.LicenciaPaternidadController;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.LicPaternidad;
import com.is.controlincidencias.model.LicPaternidadModel;
import com.is.controlincidencias.repository.JustificanteRepository;
import com.is.controlincidencias.repository.LicPaternidadRepository;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.LicPaternidadService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service("licPaternidadServiceImpl")

public class LicPaternidadServiceImpl implements LicPaternidadService{

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    @Autowired
    @Qualifier("licPaternidadRepository")
    private LicPaternidadRepository licPaternidadRepository;

    @Autowired
    @Qualifier("justificanteRepository")
    private JustificanteRepository justificanteRepository;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    private String ruta_archivos = ".//src//main//resources//files//";


    public Justificante consultarJustificante(int id) {
        return justificanteRepository.findByIdJustificante(id);
    }

    @Override
    public void guardarLicPaternidad(LicPaternidadModel licPaternidadModel,int idIncidencia) {
        //necesito hacer la conversioon y guardar el justificante
        Date fecha = new Date();
        //Esta cosa deberia de cambiar dependiendo el empleado que esta en el sistema
        int noEmpleado=22;
        justificanteRepository.altaJustificante("Espera",fecha,noEmpleado);
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        licPaternidadRepository.altaLicPaternidad(ids.get(ids.size()-1), licPaternidadModel.getActamatrimonio(), licPaternidadModel.getActanacimiento(), licPaternidadModel.getComprobanteingresos(), licPaternidadModel.getConstanciacurso(), licPaternidadModel.getCopiaidentificacion(), licPaternidadModel.getJustificacion(), licPaternidadModel.getRegistrolicencia());
        incidenciaService.updateIdJustificante(ids.get(ids.size()-1),idIncidencia);
    }

    @Override
    public LicPaternidad buscarLicPaternidad(int idJustificante) {
        return licPaternidadRepository.findById(idJustificante);
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

    @Override
    public boolean existsByIdjustificante(int id) {
        return licPaternidadRepository.existsByJustificante_IdJustificante(id);
    }
}
