package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.NotificacionModel;
import com.is.controlincidencias.repository.NotificacionRepository;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("personal/notificaciones")

public class NotificacionesController {

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    int idEmpleado;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    @Autowired
    @Qualifier("notificacionRepository")
    private NotificacionRepository notificacionRepository;

    @GetMapping("/cancel")
    private String cancel() {
        return "redirect:/personal/vernotificaciones?cancelar=1";
    }

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
        Date fecha = new Date();
        model.addAttribute("fecha", fecha);
        model.addAttribute("notificacionModel", notificacionModel);
        return "notificaciones/crear-notificacion";
    }

    @PostMapping("/agregar")
    private String guardarConstanciaTiempo(@ModelAttribute("constanciaTiempoModel") NotificacionModel notificacionModel, @RequestParam("file") List<MultipartFile> files) {
        LOG.info("---------------------------------Datos que me llegan " + notificacionModel.toString() + "------------------" + files.get(0).getOriginalFilename());
        try {
            licPaternidadService.subirArchivo(files, idEmpleado);
        } catch (IOException e) {
            LOG.error("ERROR:", e);
            return "redirect:/personal/vernotificaciones?add=0";
        }
        int idMotivo = 0;
        if (notificacionModel.getMotivo().equals("LP")) {
            idMotivo = 1;
        } else if (notificacionModel.getMotivo().equals("LM")) {
            idMotivo = 2;
        } else {//CO
            idMotivo = 3;
        }
        Integer id = notificacionRepository.selectMaxIdPersonalQuincena();
        Date fecha = new Date();
        if (id == null) {
            notificacionRepository.insertNotificacion(1, files.get(0).getOriginalFilename(), fecha, idMotivo, idEmpleado);
        } else {
            notificacionRepository.insertNotificacion(id + 1, files.get(0).getOriginalFilename(), fecha, idMotivo, idEmpleado);
        }

        return "redirect:/personal/vernotificaciones?add=1";
    }
}
