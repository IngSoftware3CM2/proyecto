package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.converter.PermisoEconomicoConverter;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.PermisoEconomico;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.PermisoEconomicoModel;
import com.is.controlincidencias.repository.QuincenaRepository;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.JustificanteService;
import com.is.controlincidencias.service.PermisoEconomicoService;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
import com.is.controlincidencias.service.impl.QuincenaServiceImpl;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/personal/justificantes/economico")
public class PermisoEconomicoController {

    int idIncidencia;
    int idEmpleado;
    Personal personal_chido;

    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    private final PermisoEconomicoService permisoEconomicoService;

    private final PersonalServiceImpl personalService;

    private final JustificanteService justificanteService;

    private final PermisoEconomicoConverter permisoEconomicoConverter;



    @Autowired
    public PermisoEconomicoController(@Qualifier("permisoEconomicoServiceImpl") PermisoEconomicoService permisoEconomicoService, @Qualifier("personalServiceImpl") PersonalServiceImpl personalService, @Qualifier("justificanteServiceImpl") JustificanteService justificanteService, @Qualifier("permisoEconomicoConverter") PermisoEconomicoConverter permisoEconomicoConverter) {
        this.permisoEconomicoService = permisoEconomicoService;
        this.personalService = personalService;
        this.justificanteService = justificanteService;
        this.permisoEconomicoConverter = permisoEconomicoConverter;
    }

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("quincenaServiceImpl")
    private QuincenaServiceImpl quincenaService;

    @GetMapping("/agregar")
    private String redirectFormPermisoEconomico(Model model, @RequestParam(name =
            "id") Integer idincidencia, Principal principal) {
        String email = "";
        if (principal != null && principal.getName() != null) {
            email = principal.getName();
        }
        idIncidencia = idincidencia;
        Personal personal = personalService.getPersonalByEmail(email);
        personal_chido = personal;
        idEmpleado = personal.getIdEmpleado();
        Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
        model.addAttribute("noTajerta", personal.getNoTarjeta().toString());
        model.addAttribute("fecha", incidencia.getFechaRegistro().toString());
        return Constants.JUSTIFICANTE_DE;
    }

    @PostMapping("/add-permisoE")
    private String guardarPermisoEconomico() {
        String tipo = personal_chido.getTipo(); //ROLE_DOC
        Incidencia incidencia =  incidenciaService.consultarIncidencia(idIncidencia);
        int idquincena = quincenaService.idquincenaConFechaDeIncidencia(incidencia.getFechaRegistro());
        LOG.info(idquincena);
        //me falta obtener idquincena
        if(permisoEconomicoService.preguntarAnoQuincena(idEmpleado,idquincena,tipo)==1){
            LOG.error("Si hay registro******************");
        }
        else{
            LOG.error("No hay registro******************");
        }
        return "redirect:/personal/justificantes?add=1";
    }


    @GetMapping("/cancelar")
    public String cancelarPermisoEconomico(){
        return "redirect:/personal/incidencias";
    }


}
