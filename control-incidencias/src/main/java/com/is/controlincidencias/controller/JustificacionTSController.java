package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/personal/justificantes")
public class JustificacionTSController {

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    private int idIncidencia;
    private Personal personal;
    @GetMapping("/tiemposuplementario")
    public ModelAndView verJustificante(Model model, @RequestParam(name =
            "id") Integer idincidencia, Principal principal){
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        idIncidencia=idincidencia;
        personal = personalService.getPersonalByEmail(email);
        Incidencia incidencia = incidenciaService.consultarIncidencia(idIncidencia);
        ModelAndView mav = new ModelAndView(Constants.JUSTIFICANTE_TS);
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("noTarjeta",personal.getNoTarjeta());
        mav.addObject("incidencia",incidencia.getFechaRegistro());
        return mav;
    }

    @GetMapping("/tiemposuplementario/cancelar")
    public String cancelarTipoA(){
        return "redirect:/personal/incidencias?cancelar=1";
    }

}
