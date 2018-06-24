package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* Para no tener un desmadre con las redirecciones la vizualizacion y la validacion se
* trabajaran en este controlador al cual redireccionaran los respectivas vistas de ver
* justificantes */

@Controller
@RequestMapping("/justificantes")
public class JustificantesController {
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
}
