package com.is.controlincidencias.controller;

import com.is.controlincidencias.service.impl.JustificanteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/docente")
public class DocenteController {
    /*Falta ver incidencias, ver perfil, cerrar sesion */

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteServiceImpl justificanteService;

    @GetMapping("/verjustificantes")
    public ModelAndView showJustificantes() {
        ModelAndView mav = new ModelAndView("ver-justificantes");
        mav.addObject("justificantes", justificanteService.listAllJustificante() );
        return mav;
    }
}