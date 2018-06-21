package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.PersonalService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class RegPeriodoVavacionalController {

    private static final Log LOG = LogFactory.getLog(RegPeriodoVavacionalController.class);
    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;


    @GetMapping("/periodovacacional")
    public ModelAndView registrarDiaNoLaborable(Model model, Principal principal){
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        Personal personal = personalService.getPersonalByEmail(email);
        ModelAndView mav = new ModelAndView(Constants.PERIODO_VACACIONAL);
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        return mav;
    }

    @GetMapping("/periodovacacional/exito")
    public String exito(){
        return "redirect:/dch?succes=1";
    }

    @GetMapping("/periodovacacional/cancelar")
    public String cancelar(){
        return "redirect:/dch?error=1";
    }
}
