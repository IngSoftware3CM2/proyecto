package com.is.controlincidencias.controller;


import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.LicPaternidadModel;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.LicPaternidadService;
import com.is.controlincidencias.service.PersonalService;
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
@RequestMapping("/personal/justificantes/paternidad")

public class LicenciaPaternidadController {

    static int idIncidencia;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;



    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    @GetMapping("/cancel")
    private String cancel(){
        //return "redirect:/contacts/showcontacts";
        return "ver-incidencias";
    }

    @GetMapping("/agregar")
    private String redirectSolicitudLicenciaPaternidadForm(Model model,@RequestParam(name="id", required=true)int id) {//@RequestParam(name="id", required = false) int idJustificante,
        idIncidencia=id;
        Incidencia incidencia = incidenciaService.consultarIncidencia(id);
        LicPaternidadModel licPaternidadModel = new LicPaternidadModel();
        model.addAttribute("licPaternidadModel", licPaternidadModel);
        Personal personal = personalService.getPersonalByNoEmpleado(1);
        model.addAttribute("noTajerta",personal.getNoTarjeta().toString());
        model.addAttribute("fecha",incidencia.getFechaRegistro().toString());
        return Constants.JUSTIFICANTE_P;
    }

    @PostMapping("/add-lic-paternidad")
    private String guardarLicPaternidad(@ModelAttribute("licPaternidadModel") LicPaternidadModel licPaternidadModel,@RequestParam("file") List<MultipartFile> files) {
        LOG.info("Datos que me llegan "+licPaternidadModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        licPaternidadModel.setRegistrolicencia(files.get(0).getOriginalFilename());
        licPaternidadModel.setActanacimiento(files.get(1).getOriginalFilename());
        licPaternidadModel.setActamatrimonio(files.get(2).getOriginalFilename());
        licPaternidadModel.setConstanciacurso(files.get(3).getOriginalFilename());
        licPaternidadModel.setCopiaidentificacion(files.get(4).getOriginalFilename());
        licPaternidadModel.setComprobanteingresos(files.get(5).getOriginalFilename());
        try {
            licPaternidadService.subirArchivo(files);
            licPaternidadService.guardarLicPaternidad(licPaternidadModel, idIncidencia);
        } catch (IOException e) {
            LOG.error("ERROR:", e);
        }
        return "redirect:/docente/justificantes";
    }

}
