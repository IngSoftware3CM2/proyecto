package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cambio-horario")
public class CambioHorario {
    static final String VISTA_CAMBIO_HORARIO = "solicitud-cambio-horario";
    static final String COMFIRMAR_CAMBIO_HORARIO = "ver-cambio-horario";

    @GetMapping("/registrar")
    public String registrar() { return VISTA_CAMBIO_HORARIO;    }

    @GetMapping("/comfirmar")
    public String comfirmar() { return COMFIRMAR_CAMBIO_HORARIO; }
}
