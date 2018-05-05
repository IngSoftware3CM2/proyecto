package com.is.controlincidencias.controller.dch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dch")
public class DchController {
    private static final String INICIO = "dch/inicio";
    private static final String PERFIL = "dch/perfil";

    @GetMapping({"", "/"})
    public String inicio() {
        return INICIO;
    }

    @GetMapping("/perfil")
    public String perfil() {
        return PERFIL;
    }
}
