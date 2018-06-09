package com.is.controlincidencias.controller;


import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.ConstanciaTiempoModel;
import com.is.controlincidencias.service.IncidenciaService;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("personal/justificantes/constanciatiempo")

public class ConstanciaTiempoController {

    int idIncidencia;
    int idEmpleado;
    int idJustificanteModificar;

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @GetMapping("/agregar")
    private String redirectSolicitudLicenciaPaternidadForm(Model model, @RequestParam(name =
            "id") Integer idincidencia, Principal principal) {

        String email = "correo@gmail.com";
        if (principal != null && principal.getName() != null) {
            email = principal.getName();
        }
        Personal personal = personalService.getPersonalByEmail(email);
        ConstanciaTiempoModel constanciaTiempoModel = new ConstanciaTiempoModel();
        idIncidencia = idincidencia;
        Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
        List<String> instituciones = new ArrayList<String>();
        instituciones.add("CENDI");
        instituciones.add("ISSSTE");
        instituciones.add("CLIDDA");

        model.addAttribute("instituciones", instituciones );
        model.addAttribute("constanciaTiempoModel", constanciaTiempoModel);
        idEmpleado = personal.getIdEmpleado();
        model.addAttribute("noTajerta", personal.getNoTarjeta().toString());
        model.addAttribute("fecha", incidencia.getFechaRegistro().toString());

        return "justificanteConstanciaTiempo/form-constancia-tiempo";
    }

    @PostMapping("/add-constancia-tiempo")
    private String guardarConstanciaTiempo(@ModelAttribute("constanciaTiempoModel") ConstanciaTiempoModel constanciaTiempoModel, @RequestParam("file") List<MultipartFile> files) {
        constanciaTiempoModel.setConstanciaArchivo(files.get(0).getOriginalFilename());

        LOG.info("---------------------------------Datos que me llegan "+constanciaTiempoModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo

        return "redirect:/personal/justificantes?add=1";
    }

}