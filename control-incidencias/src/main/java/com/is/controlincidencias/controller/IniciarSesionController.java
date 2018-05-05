package com.is.controlincidencias.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IniciarSesionController {

    private static final Log LOG = LogFactory.getLog(IniciarSesionController.class);
    private static final String VISTA_INICIO = "inicio";
    private static final String VISTA_LOGIN = "iniciar-sesion";

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout) {
        LOG.info("login() -- PARAMS: error: " + error + ", logout: " + logout);

        model.addAttribute("error", error);
        model.addAttribute("logout", logout);

        return VISTA_LOGIN;
    }

    @PostMapping("/acceder")
    public String acceder() {
        LOG.info("Accedí al metodo acceder del controlador.");
        return VISTA_INICIO;
    }
}
