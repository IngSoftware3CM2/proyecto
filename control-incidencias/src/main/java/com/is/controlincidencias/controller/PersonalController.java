package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/personal")
public class PersonalController {
    private static final Log LOG = LogFactory.getLog(PersonalController.class);
    private static final String INICIO = "inicio";
    private static final String PERFIL = "perfil";
    private static final String CAMBIAR_CONTRA = "cambiar-contra";

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

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
        return INICIO;
    }

    @GetMapping("/perfil")
    public String perfil(Model model, Principal principal) {
        LOG.info("perfil()");
        Personal personal = personalService.getPersonalByEmail(principal.getName());
        model.addAttribute("datos", personal);
        return PERFIL;
    }

    @GetMapping("/perfil/cambiar")
    public String cambiarContra() {
        return CAMBIAR_CONTRA;
    }
}
