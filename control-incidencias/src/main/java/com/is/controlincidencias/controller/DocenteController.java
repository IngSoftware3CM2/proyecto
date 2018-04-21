package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/docente")
public class DocenteController {
/*Falta ver incidencias, ver perfil, cerrar sesion */

    @GetMapping("/verjustificantes")
    public String verJustificantes() {
        return "ver-justificantes";
    }

}