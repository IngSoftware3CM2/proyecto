package com.is.controlincidencias.controller;

import com.is.controlincidencias.component.ReglasNegocio;
import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.TiempoSuplGenerado;
import com.is.controlincidencias.model.JustificateTiempoSuplModel;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.PersonalService;
import com.is.controlincidencias.service.TiempoSuplGeneradoService;
import com.is.controlincidencias.service.TiempoSuplementarioService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/personal/justificantes")
public class JustificacionTSController {

    private static final Log LOG = LogFactory.getLog(JustificacionTSController.class);

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("tiempoSuplGeneradoServiceImpl")
    private TiempoSuplGeneradoService tiempoSuplGeneradoService;

    @Autowired
    @Qualifier("tiempoSuplementarioServiceImpl")
    private TiempoSuplementarioService tiempoSuplementarioService;

    @Autowired
    @Qualifier("reglasNegocioComponent")
    private ReglasNegocio reglasNegocio;

    private Incidencia incidencia;
    private Personal personal;
    private int errorRN29=0;

    private static final String REDIRECT = "redirect:/personal/justificantes/tiemposuplementario?id=";
    @GetMapping("/tiemposuplementario")
    public ModelAndView verJustificante(Model model, @RequestParam(name =
            "id") Integer idincidencia, Principal principal){
        int sinTiempoError;
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        personal = personalService.getPersonalByEmail(email);
        incidencia = incidenciaService.consultarIncidencia(idincidencia);
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
        model.addAttribute("errorHorasCubrir",errorRN29);
        model.addAttribute("justTiempoSuplementario",new JustificateTiempoSuplModel());
        model.addAttribute("selectedValue",new ArrayList<Integer>());
        errorRN29=0;
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


    @PostMapping("/tiemposuplementario/agregar")
    private String agregarJustificante (@ModelAttribute("justTiempoSuplementario")JustificateTiempoSuplModel justificateTiempoSuplModel){
        LOG.info(justificateTiempoSuplModel.getTiempocubrir());
        List<TiempoSuplGenerado> tiemposGenerados = new ArrayList<>();
        LocalTime horasTotales=LocalTime.parse("00:00:00");
        for (Integer id:justificateTiempoSuplModel.getIdSeleccionados()
             ) {
            LOG.info(id);
            TiempoSuplGenerado tiempo = tiempoSuplGeneradoService.findById(id);
            horasTotales=horasTotales.plusHours(tiempo.getHoras().getHour());
            tiemposGenerados.add(tiempo);
        }
        LOG.info(horasTotales);
        //Validacion de otra regla de negocio

        //Validacion de la regla de negocio 29
        if(!reglasNegocio.rn29(horasTotales,justificateTiempoSuplModel.getTiempocubrir())){
            errorRN29=1;
            LOG.info("ERROR REGLA DE NEGOCIO 29");
            return REDIRECT + incidencia.getIdIncidencia();
        }
        // Actualizamos los tiempos suplementarios generados guardados
        for (TiempoSuplGenerado tiemposSupl:tiemposGenerados
             ) {
            tiempoSuplGeneradoService.updatetiempoUsados(tiemposSupl.getId());
        }
        //Actualizamos la tabla personalquincena


        //Guardamos el registro
            tiempoSuplementarioService.saveJustificanteTS(justificateTiempoSuplModel,incidencia);
        return "redirect:/personal/justificantes?add=1";
    }
}
