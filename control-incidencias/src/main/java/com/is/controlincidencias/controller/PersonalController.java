package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.impl.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

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

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteServiceImpl justificanteService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaServiceImpl incidenciaService;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadServiceImpl licPaternidadService;

    @Autowired
    @Qualifier("taServiceImpl")
    private JustificanteTAServiceImpl justificanteTAService;

    @GetMapping({"", "/"})
    public String inicio(Model model, Principal principal) {
        if (principal != null)
            LOG.info("inicio() " + principal.getName());
        return INICIO;
    }

    /*
     * Para recuperar el correo deben de agregar el parametro de Pincipal
     * El correo se recupera con getName()
     * Realizar una consulta a la base de datos para recuperar el ID del usuario
     * con base en el email
     * OJO, si se trabaja con el login desactivado al llamar a principal.getName()
     * produce un error
     * */
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

    @GetMapping("/justificantes")
    public ModelAndView showJustificantes() {
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-justificantes");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        List<Justificante> justificantes = justificanteService.getJustificantesByPersonal(personal);
        for (Justificante justificante : justificantes) {
            if (justificanteTAService.existsByIdjustificante(justificante.getIdJustificante()))
            {
                justificante.setTipo("Tipo A");
            }
            else if (licPaternidadService.existsByIdjustificante(justificante.getIdJustificante()))
            {
                justificante.setTipo("Licencia Paternidad");
            }
            else
            {
                justificante.setTipo("Otro Tipo");
            }
        }
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("justificantes", justificantes);
        return mav;
    }

    @GetMapping("/verjustificante")
    public ModelAndView verJustificante(){
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-justificante-docente");
        return mav;
    }

    @GetMapping("/incidencias")
    public ModelAndView showIncidencias(){
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-incidencias");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("incidencias", incidenciaService.getIncidenciasByPersonal(personal));
        return mav;
    }

    @GetMapping("/removejustificante")
    public ModelAndView removeJustificante(@RequestParam(name = "id", required = true) int idJustificante){
        int noEmpleado = 22;
        justificanteService.removeJustificanteByIdJustificante(idJustificante);
        return showIncidencias();
    }
}
