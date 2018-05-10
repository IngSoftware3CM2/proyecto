package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.converter.PermisoEconomicoConverter;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.PermisoEconomicoModel;
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
@RequestMapping("/personal/justificantes")
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

    @GetMapping("/permiso-economico")
    public ModelAndView verPermisoEconomico(Model model, Principal principal){
        personal = getPersonal(principal);
        ModelAndView modelAndView = new ModelAndView(Constants.JUSTIFICANTE_DE);
        model.addAttribute("permisoEconomicoModel",new PermisoEconomicoModel());
        modelAndView.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        modelAndView.addObject("noTarjeta",personal.getNoTarjeta());
        return modelAndView;
    }
    @GetMapping("/permiso-economico/modificar")
    public ModelAndView modificarPermisoEconomicoe(@RequestParam("id")int idJustificante, Model model, Principal principal){
        personal = getPersonal(principal);
        ModelAndView modelAndView = new ModelAndView(Constants.JUSTIFICANTE_DE);
        PermisoEconomicoModel permisoEconomicoModel = new PermisoEconomicoModel();
        if(idJustificante!=0){
            Justificante justificante = justificanteService.findJustificanteById(idJustificante);
            PermisoEconomico permisoEconomico = permisoEconomicoService.findByJustificante(justificante);
            permisoEconomicoModel = permisoEconomicoConverter.converterPermisoEconomicoToPermisoEconomicoModel(permisoEconomico);
        }
        modelAndView.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        modelAndView.addObject("noTarjeta",personal.getNoTarjeta());
        model.addAttribute("permisoEconomicoModel",permisoEconomicoModel);
        return modelAndView;
    }

    @PostMapping("/permiso-economico/agregar")
    private String guardarPermisoEconomico(@ModelAttribute("permisoEconomicoModel") PermisoEconomicoModel permisoEconomicoModel) {
        Justificante justificante = new Justificante();
        justificante.setPersonal(personal);

        permisoEconomicoService.addPermisoEconomico(permisoEconomicoModel, justificante);
        return "redirect:/personal/justificantes";
    }

    @GetMapping("/permiso-economico/cancelar")
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
