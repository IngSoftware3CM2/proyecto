package com.is.controlincidencias.controller;


import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.LicPaternidadModel;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.LicPaternidadService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/licenciapaternidad")

public class LicenciaPaternidadController {

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    @GetMapping("/form")
    private String RedirectSolicitudLicenciaPaternidadForm(Model model) {//@RequestParam(name="id", required = false) int idJustificante,
        LicPaternidadModel licPaternidadModel = new LicPaternidadModel();
        /*LicPaternidad licPaternidad = new LicPaternidad();
        if (idJustificante!=0){
            licPaternidad = licPaternidadService.buscarLicPaternidad(idJustificante);

        }*/
        model.addAttribute("licPaternidadModel", licPaternidadModel);
        return Constants.JUSTIFICANTE_P;
    }

    @PostMapping("/add-lic-paternidad")
    private String GuardarLicPaternidad(@ModelAttribute("licPaternidadModel") LicPaternidadModel licPaternidadModel,@RequestParam("file") List<MultipartFile> files) {
        LOG.info("Datos que me llegan "+licPaternidadModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        Justificante justificante = new Justificante();
        for(int x=0;x<6;x++){
            if (!files.get(x).getOriginalFilename().equals(".jpg")){

            }
        }

        licPaternidadModel.setRegistrolicencia(files.get(0).getOriginalFilename());
        licPaternidadModel.setActanacimiento(files.get(1).getOriginalFilename());
        licPaternidadModel.setActamatrimonio(files.get(2).getOriginalFilename());
        licPaternidadModel.setConstanciacurso(files.get(3).getOriginalFilename());
        licPaternidadModel.setCopiaidentificacion(files.get(4).getOriginalFilename());
        licPaternidadModel.setComprobanteingresos(files.get(5).getOriginalFilename());
        try {
            licPaternidadService.subirArchivo(files);
            licPaternidadService.guardarLicPaternidad(licPaternidadModel,justificante);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ver-justificantes";
    }

}
