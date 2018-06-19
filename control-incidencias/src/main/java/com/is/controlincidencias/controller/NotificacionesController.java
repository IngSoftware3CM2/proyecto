package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.NotificacionModel;
import com.is.controlincidencias.service.PersonalService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("personal/notificaciones")

public class NotificacionesController {

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    int idEmpleado;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @GetMapping("/crear")
    private String redirectCrearNotificacion(Model model, Principal principal) {
        String email = "correo@gmail.com";
        if (principal != null && principal.getName() != null) {
            email = principal.getName();
        }
        Personal personal = personalService.getPersonalByEmail(email);
        NotificacionModel notificacionModel = new NotificacionModel();
        idEmpleado = personal.getIdEmpleado();
        model.addAttribute("noTajerta", personal.getNoTarjeta().toString());
        LocalDate fecha = LocalDate.now();
        //model.addAttribute("fecha", fecha);
        model.addAttribute("notificacionModel", notificacionModel);
        return "notificaciones/crear-notificacion";
    }

    @PostMapping("/agregar")
    private String guardarConstanciaTiempo(@ModelAttribute("constanciaTiempoModel") NotificacionModel notificacionModel, @RequestParam("file") List<MultipartFile> files) {
        LOG.info("---------------------------------Datos que me llegan "+notificacionModel.toString());
        return null;
    }
}
