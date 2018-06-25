package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/superior")
public class SuperiorController {
    /*Este metodo debe de tener la logica para ver justificantes para cualquier tipo de superior
    * pero considerando todas las posibilidades de acuerdo a los estados */
    @GetMapping("/justificantes")
    public String verJustificantes() {
        return "inicio";
    }
}
