package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.impl.IncidenciaServiceImpl;
import com.is.controlincidencias.service.impl.JustificanteServiceImpl;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
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

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

    @GetMapping("/justificantes")
    public ModelAndView showJustificantes() {
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-justificantes");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        mav.addObject("justificantes", justificanteService.getJustificantesByPersonal(personal));
        return mav;
    }

    @GetMapping("/incidencias")
    public ModelAndView showIncidencias(){
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-incidencias");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        mav.addObject("incidencias", incidenciaService.getIncidenciasByPersonal(personal));
        return mav;
    }

    @GetMapping("/removejustificante")
    public ModelAndView removeJustificante(@RequestParam(name = "id", required = true) int id){
        justificanteService.removeJustificante(id);
        return showIncidencias();
    }
}