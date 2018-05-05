package com.is.controlincidencias.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/personal")
public class PersonalController {
    public static final Log LOG =LogFactory.getLog(PersonalController.class);

    /*
    * Para recuperar el correo deben de agregar el parametro de Pincipal
    * El correo se recupera con getName()
    * Realizar una consulta a la base de datos para recuperar el ID del usuario
    * con base en el email
    * OJO, si se trabaja con el login desactivado al llamar a principal.getName()
    * produce un error
    * */
    @GetMapping({"", "/"})
    public String inicio(Model model, Principal principal) {
        if (principal != null)
            LOG.info("inicio() " + principal.getName());
        return "inicio";
    }
}
