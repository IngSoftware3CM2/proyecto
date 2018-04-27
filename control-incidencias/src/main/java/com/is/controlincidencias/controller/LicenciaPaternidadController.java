package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.model.LicPaternidadModel;
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

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    @GetMapping("/form")
    private String RedirectSolicitudLicenciaPaternidadForm(Model model) {
        model.addAttribute("licPaternidadModel", new LicPaternidadModel());
        return Constants.JUSTIFICANTE_P;
    }

    @PostMapping("/add-lic-paternidad")
    private String GuardarLicPaternidad(@ModelAttribute("licPaternidadModel") LicPaternidadModel licPaternidadModel, @RequestParam("file") List<MultipartFile> files) throws IOException {
        LOG.info("Datos que me llegan " + licPaternidadModel.toString());
        //licPaternidadService.consultarIncidencia(1).toString();
        for (MultipartFile file : files) {
            LOG.info("Info de archivo " + file.getOriginalFilename());
        }
        if (files.size() == 0) {
            //debe de decirle que pues no metió archivos
        }
        try {
            licPaternidadService.subirArchivo(files);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "control-de-justificantes";
    }

}
