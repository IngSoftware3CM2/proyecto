package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/justificacionTA")
public class JustificacionTAController {

    @GetMapping("justificanteForm")
    public ModelAndView verJustificante(){
        return new ModelAndView(Constants.JUSTIFICANTE_A);
    }
}
