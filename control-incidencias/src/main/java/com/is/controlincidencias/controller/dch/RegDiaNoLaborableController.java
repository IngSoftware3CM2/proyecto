package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.ConsultaPersonal;
import com.is.controlincidencias.model.DiaNoLaborableModel;
import com.is.controlincidencias.service.PersonalService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dch/asistencia")
public class RegDiaNoLaborableController {

    private static final Log LOG = LogFactory.getLog(RegDiaNoLaborableController.class);
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
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        model.addAttribute("personal", new ConsultaPersonal());
        model.addAttribute("diaNoLaborable", new DiaNoLaborableModel());
        return mav;
    }

/*
    @PostMapping("/dianolaborable/agregar")
    private String guardarJustificanteTA(@Valid @ModelAttribute("diaNoLaborable") DiaNoLaborableModel diaNoLaborableModel,
                                          BindingResult bindingResult) {
        LOG.info("----------------------------------------------------------");
        LOG.info("Datos que me llegan "+diaNoLaborableModel.getFecha().toString());
        LOG.info("Datos: "+ diaNoLaborableModel.getJustificacion());
        LOG.info(diaNoLaborableModel.getIdPersonal());
        if(bindingResult.hasErrors())
            LOG.info("Errorcito");
        return "redirect:/dch/asistencia/dianolaborable";
    }
    */
}
