package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/licenciapaternidad")

public class LicenciaPaternidadController {

    @GetMapping("/form")
    private String RedirectSolicitudLicenciaPaternidadForm(){

        return "solicitud-licencia-paternidad-chafa";
    }

}
