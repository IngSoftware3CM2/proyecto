package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {

    @GetMapping("/registrar")
    public String registrar() {
        return "registro-usuarios";
    }

    @GetMapping("/modificar")
    public String modificar() {
        return "registro-usuarios";
    }
}
