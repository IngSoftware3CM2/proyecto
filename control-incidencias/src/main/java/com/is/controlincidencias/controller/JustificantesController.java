package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.impl.JustificanteServiceImpl;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/*
* Para no tener un desmadre con las redirecciones la vizualizacion y la validacion se
* trabajaran en este controlador al cual redireccionaran los respectivas vistas de ver
* justificantes */

@Controller
@RequestMapping("/justificantes")
public class JustificantesController {

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteServiceImpl justificanteService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

    @GetMapping("/paternidad")
    public String verPaternidad() {
        return "inicio";
    }

    @GetMapping("/tipoa")
    public String verTipoA() {
        return "inicio";
    }

    @GetMapping("/omision")
    public String verOmision() {
        return "inicio";
    }

    @GetMapping("/retardo")
    public String verRetardo() {
        return "inicio";
    }

    @GetMapping("/cambiohorario")
    public String verCambioHorario() {
        return "inicio";
    }

    @GetMapping("/economico")
    public String verEconomico() {
        return "inicio";
    }

    @GetMapping("/suplementario")
    public String verSuplementario() {
        return "inicio";
    }

    @GetMapping("/comision")
    public String verComisionOficial() {
        return "inicio";
    }

    @GetMapping("/validar")
    public ModelAndView validarJustificantes (Principal principal) {
        ModelAndView mav = new ModelAndView("validar-justificantes");
        String email = "abhera@yandex.com";
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        List<Justificante> allJustificantes = new ArrayList<>();
        List<Justificante> showJustificantes = new ArrayList<>();
        allJustificantes = justificanteService.getAllJustificante();
        Personal personal = personalService.getPersonalByEmail(email);

        for (Justificante j : allJustificantes) {
            System.out.println("FECHAJUST: "+j.getFechaAsString());
        }

        mav.addObject("justificantes", allJustificantes);

        /* Para mostrar la barra vertical correcta
         * Mandar 2 para capital humano 1 para cualquier otro superior
         * obviamente depende de quien haya iniciado sesion (su ROL)
         */
        mav.addObject("tipo_usuario", 1);

        return mav;
    }


}
