package com.is.controlincidencias.controller.dch;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.ConsultaPersonal;
import com.is.controlincidencias.model.DiaNoLaborableModel;
import com.is.controlincidencias.service.LicPaternidadService;
import com.is.controlincidencias.service.PersonalService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dch/asistencia")
public class RegDiaNoLaborableController {

    private static final Log LOG = LogFactory.getLog(RegDiaNoLaborableController.class);
    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    private Personal personal;
    private int idPeriodoInhabil;
    @GetMapping("/dianolaborable")
    public ModelAndView registrarDiaNoLaborable(Model model,Principal principal){
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        personal = personalService.getPersonalByEmail(email);
        ModelAndView mav = new ModelAndView(Constants.DIA_NO_LABORABLE);
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        model.addAttribute("personal", new ConsultaPersonal());
        model.addAttribute("diaNoLaborable", new DiaNoLaborableModel());
        return mav;
    }

    @GetMapping("/dianolaborable/archivo")
    public ModelAndView registrarDiaNoLaborableArchivo(Model model,@RequestParam(name = "id", required = false) Integer idPeriodo){
        idPeriodoInhabil=idPeriodo;
        ModelAndView mav = new ModelAndView(Constants.DIA_NO_LABORABLE_ARCHIVO);
        mav.addObject("tipoAndNombre",personal.nombreAndTipoToString());
        return mav;
    }

    @PostMapping("dianolaborable/archivo/subir")
    private String guardarJustificanteTA(@RequestParam("file") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return "redirect:/dch/asistencia/dianolaborable/archivo?id="+idPeriodoInhabil;
        }
        try {
            //Aqui trato de subir el archivo
            //Actualizo la tabla de periodo inhabil con el nombre del archivo
            licPaternidadService.subirArchivo(files, idPeriodoInhabil);
            LOG.info("Aqui trato de subir el archivo");
        } catch (IOException e) {
            LOG.error(e);
        }
        return "redirect:/dch?succes=1";
    }


    @GetMapping("/dianolaborable/cancelar")
    public String cancelar(){
        return "redirect:/dch?error=1";
    }
}
