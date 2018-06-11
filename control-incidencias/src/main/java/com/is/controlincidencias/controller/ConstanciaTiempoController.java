package com.is.controlincidencias.controller;


import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.ConstanciaTiempoModel;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.JustificanteService;
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
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("personal/justificantes/constanciatiempo")

public class ConstanciaTiempoController {

    int idIncidencia;
    int idEmpleado;
    int idJustificanteModificar;
    LocalDate fecha;

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteService justificanteService;

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
        model.addAttribute("constanciaTiempoModel", constanciaTiempoModel);
        idEmpleado = personal.getIdEmpleado();
        model.addAttribute("noTajerta", personal.getNoTarjeta().toString());
        fecha = incidencia.getFechaRegistro();
        model.addAttribute("fecha", fecha);

        return "justificanteConstanciaTiempo/form-constancia-tiempo";
    }

    @PostMapping("/add-constancia-tiempo")
    private String guardarConstanciaTiempo(@ModelAttribute("constanciaTiempoModel") ConstanciaTiempoModel constanciaTiempoModel, @RequestParam("file") List<MultipartFile> files) {
        constanciaTiempoModel.setConstanciaArchivo(files.get(0).getOriginalFilename());
        if (constanciaTiempoModel.getSegfecha()==null){
            constanciaTiempoModel.setSegfecha(fecha);
        }
        LOG.info("---------------------------------Datos que me llegan "+constanciaTiempoModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        try {

            //idjustificante = licPaternidadService.guardarLicPaternidad(licPaternidadModel, idIncidencia, idEmpleado);
            licPaternidadService.subirArchivo(files, 1);
        } catch (IOException e) {
            LOG.error("ERROR:", e);
            justificanteService.removeJustificanteByIdJustificante(1);
        }

        return "redirect:/personal/justificantes?add=1";
    }

}