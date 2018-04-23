package com.is.controlincidencias.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asistencias")
public class AsistenciasController {
    private static final String INICIO_ASISTENCIAS = "inicio-asistencias";
    private static final String REGISTRAR_ASISTENCIA = "registrar-asistencias";
    private static final String MODIFICAR_ASISTENCIA = "modificar-asistencias";
    private static final String MOSTRAR_ASISTENCIA = "mostrar-asistencias";
    private static final String ELIMINAR_ASISTENCIA = "eliminar-asistencias";


    @GetMapping({"", "/"})
    public String inicio() {
        return INICIO_ASISTENCIAS;
    }

    @GetMapping("/registrar")
    public String registrar() {
        return REGISTRAR_ASISTENCIA;
    }

    @GetMapping("/modificar")
    public String modificar() {
        return MODIFICAR_ASISTENCIA;
    }

    @GetMapping("/mostrar")
    public String mostrar() {
        return MOSTRAR_ASISTENCIA;
    }

    @GetMapping("/eliminar")
    public String eliminar() {
        return ELIMINAR_ASISTENCIA;
    }
}
