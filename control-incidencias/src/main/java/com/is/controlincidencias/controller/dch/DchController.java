package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.service.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dch")
public class DchController {
    private static final String INICIO = "dch/inicio";
    private static final String PERFIL = "dch/perfil";

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    private int error=0;
    private int succes=0;
    @GetMapping({"", "/"})
    public String inicio(Model model) {
        model.addAttribute("error",error);
        model.addAttribute("succes",succes);
        error=0;
        succes=0;
        //incidenciaService.registrarIncidencia();
        return INICIO;
    }

    @GetMapping("/perfil")
    public String perfil() {
        return PERFIL;
    }
}
