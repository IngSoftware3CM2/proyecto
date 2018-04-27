package com.is.controlincidencias.controller.asistencias;

import com.is.controlincidencias.model.AsistenciaJSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/modificar")
    public String modificar(Model model) {
        AsistenciaJSON asistencia = new AsistenciaJSON();
        model.addAttribute("asistenciaModel", asistencia);
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
