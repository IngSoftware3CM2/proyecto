package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.converter.PermisoEconomicoConverter;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.PermisoEconomicoModel;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.JustificanteService;
import com.is.controlincidencias.service.PermisoEconomicoService;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/personal/justificantes/economico")
public class PermisoEconomicoController {

    private final PermisoEconomicoService permisoEconomicoService;

    private final PersonalServiceImpl personalService;

    private final JustificanteService justificanteService;

    private final PermisoEconomicoConverter permisoEconomicoConverter;

    private Personal personal;

    @Autowired
    public PermisoEconomicoController(@Qualifier("permisoEconomicoServiceImpl") PermisoEconomicoService permisoEconomicoService, @Qualifier("personalServiceImpl") PersonalServiceImpl personalService, @Qualifier("justificanteServiceImpl") JustificanteService justificanteService, @Qualifier("permisoEconomicoConverter") PermisoEconomicoConverter permisoEconomicoConverter) {
        this.permisoEconomicoService = permisoEconomicoService;
        this.personalService = personalService;
        this.justificanteService = justificanteService;
        this.permisoEconomicoConverter = permisoEconomicoConverter;
    }

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @GetMapping("/permiso-economico")
    public ModelAndView verPermisoEconomico(Model model, Principal principal){
        personal = getPersonal(principal);
        ModelAndView modelAndView = new ModelAndView(Constants.JUSTIFICANTE_DE);
        model.addAttribute("permisoEconomicoModel",new PermisoEconomicoModel());
        modelAndView.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        modelAndView.addObject("noTarjeta",personal.getNoTarjeta());
        return modelAndView;
    }


    @GetMapping("/agregar")
    private String redirectFormPermisoEconomico(Model model, @RequestParam(name =
            "id") Integer idincidencia, Principal principal) {
        String email = "";
        if (principal != null && principal.getName() != null) {
            email = principal.getName();
        }
        Personal personal = personalService.getPersonalByEmail(email);
        Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
        model.addAttribute("noTajerta", personal.getNoTarjeta().toString());
        model.addAttribute("fecha", incidencia.getFechaRegistro().toString());
        return Constants.JUSTIFICANTE_DE;
    }

    @PostMapping("/add-permisoE")
    private String guardarPermisoEconomico() {
        return "redirect:/personal/justificantes?add=1";
    }


    @GetMapping("/cancelar")
    public String cancelarPermisoEconomico(){
        return "redirect:/personal/incidencias";
    }

    public Personal getPersonal(Principal principal){
        String email = "";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        return personalService.getPersonalByEmail(email);
    }
}
