package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asistencias")
public class AsistenciaController {

    @GetMapping("")
    public String asistencia() {
        return "inicio";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro-asistencia";
    }
}
