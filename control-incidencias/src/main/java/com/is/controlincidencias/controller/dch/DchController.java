package com.is.controlincidencias.controller.dch;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dch")
public class DchController {
    private static final String INICIO = "dch/inicio";
    private static final String PERFIL = "dch/perfil";

    @GetMapping({"", "/"})
    public String inicio(Model model,@RequestParam(name = "error", required = false) Integer error,@RequestParam(name = "succes", required = false) Integer succes) {
        model.addAttribute("error",error);
        model.addAttribute("succes",succes);
        return INICIO;
    }

    @GetMapping("/perfil")
    public String perfil() {
        return PERFIL;
    }
}
