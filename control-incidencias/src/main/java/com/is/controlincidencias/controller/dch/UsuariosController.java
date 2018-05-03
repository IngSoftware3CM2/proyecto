package com.is.controlincidencias.controller.dch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dch/usuarios")
public class UsuariosController {
    private static final String REGISTRAR_USUARIOS = "dch/usuarios-registrar";
    private static final String MODIFICAR_USUARIO = "dch/usuarios-modificar";

    @GetMapping({"", "/"})
    public String inicio() {
        return "redirect:/dch";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return REGISTRAR_USUARIOS;
    }

    @GetMapping("/modificar")
    public String modificar() {
        return MODIFICAR_USUARIO;
    }
}
