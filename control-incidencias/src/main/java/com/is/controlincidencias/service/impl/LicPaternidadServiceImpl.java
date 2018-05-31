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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private String rutaArchivos = ".//src//main//resources//files//";


    public Justificante consultarJustificante(int id) {
        return justificanteRepository.findByIdJustificante(id);
    }

    @Override
    public int guardarLicPaternidad(LicPaternidadModel licPaternidadModel,int idIncidencia, int noEmpleado) {
        //necesito hacer la conversioon y guardar el justificante
        Date fecha = new Date();
        //Esta cosa deberia de cambiar dependiendo el empleado que esta en el sistema
        justificanteRepository.altaJustificante("Espera",fecha,2,noEmpleado);
        List<Integer> ids = justificanteRepository.ultimoJustificanteAnadido();
        licPaternidadRepository.altaLicPaternidad(ids.get(ids.size()-1), ids.get(ids.size()-1)+"_"+licPaternidadModel.getActamatrimonio(), ids.get(ids.size()-1)+"_"+licPaternidadModel.getActanacimiento(), ids.get(ids.size()-1)+"_"+licPaternidadModel.getComprobanteingresos(), ids.get(ids.size()-1)+"_"+licPaternidadModel.getConstanciacurso(), ids.get(ids.size()-1)+"_"+licPaternidadModel.getCopiaidentificacion(), licPaternidadModel.getJustificacion(), ids.get(ids.size()-1)+"_"+licPaternidadModel.getRegistrolicencia());
        incidenciaService.updateIdJustificante(ids.get(ids.size()-1),idIncidencia);
        LOG.info(ids);
        return ids.get(ids.size()-1);
    }

    @Override
    public LicPaternidad buscarLicPaternidadPorIdjustificante(int idJustificante) {
        return licPaternidadRepository.selectByIdjustificante(idJustificante);
    }

    @Override
    public LicPaternidad buscarLicPaternidad(int idJustificante) {
        return licPaternidadRepository.findById(idJustificante);
    }


    @Override
    public void subirArchivo(List<MultipartFile> files,int noJustificante) throws IOException {
        for(MultipartFile file: files) {
            if(file.isEmpty()) continue;
            //byte[] bytes = file.getBytes();
            //Path path = Paths.get(rutaArchivos+noJustificante+"_"+file.getOriginalFilename());
            //Files.write(path,bytes);
            File convFile = new File(rutaArchivos+noJustificante+"_"+file.getOriginalFilename());
            convFile.createNewFile();
            try (FileOutputStream fos = new FileOutputStream(convFile)) {
                fos.write(file.getBytes());
                fos.close();
            }
        }

    }

    @Override
    public boolean existsByIdjustificante(int id) {
        return licPaternidadRepository.existsByJustificante_IdJustificante(id);
    }

    @Override
    public void borrarArchivo(String archivo) {
            File fichero = new File(".//src//main//resources//files//"+archivo);
            if (fichero.delete())
                LOG.info("El fichero ha sido borrado satisfactoriamente");
            else
                LOG.info("El fichero no pudó ser borrado");
    }

    @Override
    public void updateLicPaternidad(LicPaternidad licPaternidad, int idjustificante) {
        licPaternidadRepository.updateLicPaternidad(idjustificante,licPaternidad.getJustificacion(),licPaternidad.getActamatrimonio(),licPaternidad.getActanacimiento(),licPaternidad.getComprobanteingresos(),licPaternidad.getConstanciacurso(),licPaternidad.getRegistrolicencia());
    }
}
