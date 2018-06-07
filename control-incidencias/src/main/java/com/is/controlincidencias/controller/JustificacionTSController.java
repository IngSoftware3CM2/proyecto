package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/personal/justificantes")
public class JustificacionTSController {

    @GetMapping("/tiemposuplementario")
    public ModelAndView verJustificante(){
        ModelAndView mav = new ModelAndView(Constants.JUSTIFICANTE_TS);
        return mav;
    }


}
