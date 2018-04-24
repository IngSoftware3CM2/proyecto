package com.is.controlincidencias.controller;

import com.is.controlincidencias.service.impl.IncidenciaServiceImpl;
import com.is.controlincidencias.service.impl.JustificanteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/docente")
public class DocenteController {
    /*Falta ver incidencias, ver perfil, cerrar sesion */

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteServiceImpl justificanteService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaServiceImpl incidenciaService;

    @GetMapping("/justificantes")
    public ModelAndView showJustificantes() {
        ModelAndView mav = new ModelAndView("ver-justificantes");
        mav.addObject("justificantes", justificanteService.listAllJustificante() );
        return mav;
    }

    @GetMapping("/incidencias")
    public ModelAndView showIncidencias(){
        ModelAndView mav = new ModelAndView("ver-incidencias");
        mav.addObject("incidencias", incidenciaService.listAllIncidencia());
        return mav;
    }

    @GetMapping("/removejustificante")
    public ModelAndView removeJustificante(@RequestParam(name = "id", required = true) int id){
        justificanteService.removeJustificante(id);
        return showIncidencias();
    }
}