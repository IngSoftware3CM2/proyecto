package com.is.controlincidencias.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IniciarSesionController {

    private static final Log LOGGER = LogFactory.getLog(IniciarSesionController.class);
    private static final String VISTA_INICIO = "inicio";
    private static final String VISTA_LOGIN = "iniciar-sesion";

    @GetMapping("/login")
    public String asistencia() {
        return VISTA_LOGIN;
    }

    @PostMapping("/acceder")
    public String acceder() {
        LOGGER.info("Accedí al metodo acceder del controlador.");
        return VISTA_INICIO;
    }
}
