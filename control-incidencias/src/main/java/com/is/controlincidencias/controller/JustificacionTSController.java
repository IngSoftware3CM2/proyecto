package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.TiempoSuplGenerado;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.PersonalService;
import com.is.controlincidencias.service.TiempoSuplGeneradoService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/personal/justificantes")
public class JustificacionTSController {

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("tiempoSuplGeneradoServiceImpl")
    private TiempoSuplGeneradoService tiempoSuplGeneradoService;

    private int idIncidencia;
    private Personal personal;
    private int sinTiempoError;
    private static final String REDIRECT = "redirect:redirect:/personal/justificantes/tiemposuplementario?id=";
    @GetMapping("/tiemposuplementario")
    public ModelAndView verJustificante(Model model, @RequestParam(name =
            "id") Integer idincidencia, Principal principal){
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        idIncidencia=idincidencia;
        personal = personalService.getPersonalByEmail(email);
        Incidencia incidencia = incidenciaService.consultarIncidencia(idIncidencia);
        Date fecha = new Date();
        LocalDate fechaActual = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate limTiempoSupl = fechaActual.minus(2,ChronoUnit.MONTHS);
        List<TiempoSuplGenerado> tiemposSuplementarios= tiempoSuplGeneradoService.findByPersonal(personal.getIdEmpleado(),limTiempoSupl);
        sinTiempoError=0;
        if(tiemposSuplementarios.isEmpty()){
            sinTiempoError=1;
        }
        ModelAndView mav = new ModelAndView(Constants.JUSTIFICANTE_TS);
        model.addAttribute("sinTiempo",sinTiempoError);
        mav.addObject("tiempoSuplGenerado",tiemposSuplementarios);
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("noTarjeta",personal.getNoTarjeta());
        mav.addObject("incidencia",incidencia.getFechaRegistro());
        return mav;
    }

    @GetMapping("/tiemposuplementario/cancelar")
    public String cancelarTipoA(){
        return "redirect:/personal/incidencias?cancelar=1";
    }

}
