package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/dch/asistencia")
public class RegDiaNoLaborableController {

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;


    @GetMapping("/dianolaborable")
    public ModelAndView registrarDiaNoLaborable(Model model,Principal principal){
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        Personal personal = personalService.getPersonalByEmail(email);
        ModelAndView mav = new ModelAndView(Constants.DIA_NO_LABORABLE);
        return mav;
    }

}
