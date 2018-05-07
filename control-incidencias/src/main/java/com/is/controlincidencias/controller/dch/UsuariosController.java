package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.service.AsistenciaService;
import com.is.controlincidencias.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dch/usuarios")
public class UsuariosController {
    private static final String REGISTRAR_DOCENTE = "dch/usuarios-registrar-docente";
    private static final String REGISTRAR_PAAE = "dch/usuarios-registrar-paae";
    private static final String REGISTRAR_ADMON = "dch/usuarios-registrar-admon";
    private static final String REGISTRAR_JEFE= "dch/usuarios-registrar-jefe";

    private static final String MODIFICAR_USUARIO = "dch/usuarios-modificar";

    @Autowired
    @Qualifier("usuariosServiceImpl")
    private UsuariosService usuariosService;

    @GetMapping({"", "/"})
    public String inicio() {
        return "redirect:/dch";
    }

    @GetMapping("/registrar")
    public String registrarRedirect() {
        return "redirect:/dch/usuarios/registrar/docente";
    }

    @GetMapping("/registrar/docente")
    public String registrarDocente() {
        return REGISTRAR_DOCENTE;
    }

    @GetMapping("/registrar/paae")
    public String registrarPAAE() {
        // Recupearar los departamentos con el service
        // Crear un modelo para el jefe

        // Hacer cosas, solo tiene un dia

        // Generar numero de tarjeta
        // Generar contra

        return REGISTRAR_PAAE;
    }

    @GetMapping("/registrar/admon")
    public String registrarAdmon() {
        return REGISTRAR_ADMON;
    }

    @GetMapping("/registrar/jefe")
    public String registrarJefe() {
        // Recupearar los departamentos con el service
        // Crear un modelo para el jefe

        // Hacer cosas, no lleva horario ni horas ni turno

        // Generar numero de tarjeta
        // Generar contra

        return REGISTRAR_JEFE;
    }

    @GetMapping("/modificar")
    public String modificar() {
        return MODIFICAR_USUARIO;
    }
}
