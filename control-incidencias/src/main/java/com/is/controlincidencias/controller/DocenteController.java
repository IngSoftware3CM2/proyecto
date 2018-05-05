package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadServiceImpl licPaternidadService;

    @Autowired
    @Qualifier("taServiceImpl")
    private JustificanteTAServiceImpl justificanteTAService;

    @GetMapping("/justificantes")
    public ModelAndView showJustificantes() {
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-justificantes");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        List <Justificante> justificantes = justificanteService.getJustificantesByPersonal(personal);
        for (Justificante justificante : justificantes) {
            if (justificanteTAService.existsByIdjustificante(justificante.getIdJustificante()))
            {
                justificante.setTipo("Tipo A");
            }
            else if (licPaternidadService.existsByIdjustificante(justificante.getIdJustificante()))
            {
                justificante.setTipo("Licencia Paternidad");
            }
            else
            {
                justificante.setTipo("Otro Tipo");
            }
        }
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("justificantes", justificantes);
        return mav;
    }

    @GetMapping("/verjustificante")
    public ModelAndView verJustificante(){
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-justificante-docente");
        return mav;
    }

    @GetMapping("/incidencias")
    public ModelAndView showIncidencias(){
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-incidencias");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("incidencias", incidenciaService.getIncidenciasByPersonal(personal));
        return mav;
    }

    @GetMapping("/removejustificante")
    public ModelAndView removeJustificante(@RequestParam(name = "id", required = true) int idJustificante){
        int noEmpleado = 22;
        justificanteService.removeJustificanteByIdJustificante(idJustificante);
        return showIncidencias();
    }
}