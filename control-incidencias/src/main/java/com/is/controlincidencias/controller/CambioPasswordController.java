package com.is.controlincidencias.controller;

import com.is.controlincidencias.model.CambioPasswordModel;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/perfil")
public class CambioPasswordController {

    private static final Log LOGGER = LogFactory.getLog(CambioPasswordController.class);
    static final String VISTA_CAMBIAR_PASSWORD = "cambiar-password";

    @GetMapping("/cambiar-password")
    public String getVistaCambiarPassword(Model model)
    {
        LOGGER.info("Accedí al metodo acceder del controlador");
        model.addAttribute("cambioPassword", new CambioPasswordModel());
        return VISTA_CAMBIAR_PASSWORD;
    }

    @PostMapping("/cambiar-password/confirmar")
    public ModelAndView confirmar(@ModelAttribute("cambioPassword") CambioPasswordModel cambioPassword, BindingResult bindingResult )
    {
        LOGGER.info(cambioPassword);
        ModelAndView mav = new ModelAndView();
        if(bindingResult.hasErrors()){
            LOGGER.info("Hubo errores");
            mav.setViewName(VISTA_CAMBIAR_PASSWORD);
        }else{
            LOGGER.info(cambioPassword);
            mav.setViewName(VISTA_CAMBIAR_PASSWORD);
            mav.addObject("cambioPassword", cambioPassword);
        }
        return mav;
    }

}
