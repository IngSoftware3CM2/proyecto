package com.is.controlincidencias.controller;

import com.is.controlincidencias.component.ReglasNegocio;
import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.PersonalQuincena;
import com.is.controlincidencias.entity.TiempoSuplGenerado;
import com.is.controlincidencias.model.JustificateTiempoSuplModel;
import com.is.controlincidencias.service.*;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

    @Autowired
    @Qualifier("quincenaServiceImpl")
    private QuincenaServiceImpl quincenaService;

    @Autowired
    @Qualifier("personalQuincenaServiceImpl")
    private PersonalQuincenaService personalQuincenaService;
    private Incidencia incidencia;
    private int errorRN29=0;
    private int errorRN31=0;
    private int errorCampos=0;

    private static final String REDIRECT = "redirect:/personal/justificantes/tiemposuplementario?id=";
    @GetMapping("/tiemposuplementario")
    public ModelAndView verJustificante(Model model, @RequestParam(name =
            "id") Integer idincidencia, Principal principal){
        int sinTiempoError;
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        Personal personal = personalService.getPersonalByEmail(email);
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
        model.addAttribute("errorCampos",errorCampos);
        model.addAttribute("errorJustificantes",errorRN29);
        model.addAttribute("errorHorasCubrir",errorRN31);
        model.addAttribute("justTiempoSuplementario",new JustificateTiempoSuplModel());
        errorRN29=0;
        errorRN31=0;
        errorCampos=0;
        mav.addObject("tiempoSuplGenerado",tiemposSuplementarios);
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("noTarjeta",personal.getNoTarjeta());
        mav.addObject("incidencia",incidencia.getFechaRegistro());
        mav.addObject("horasCubrir",incidencia.getHorasFaltantes());
        return mav;
    }

    @GetMapping("/tiemposuplementario/cancelar")
    public String cancelarTipoA(){
        return "redirect:/personal/incidencias?cancelar=1";
    }


    @PostMapping("/tiemposuplementario/agregar")
    private String agregarJustificante (@ModelAttribute("justTiempoSuplementario")JustificateTiempoSuplModel justificateTiempoSuplModel){
        LOG.info("*******************************************");
        LOG.info(justificateTiempoSuplModel.getTiempocubrir());
        if(justificateTiempoSuplModel.getIdSeleccionados().isEmpty()){
            errorCampos = 1;
            return REDIRECT + incidencia.getIdIncidencia();
        }
        Date input = new Date();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int horasTotales=0;
        int horasFaltantes = incidencia.getHorasFaltantes();
        LOG.info(horasTotales);
        //Validacion de otra regla de negocio
            int idQuincena = quincenaService.idquincenaConFechaDeIncidencia(date);
            Integer idPersonalQuincena = personalQuincenaService.selectMaxIdPersonalQuincena();
            int idEmpleado = incidencia.getPersonal().getIdEmpleado();
            //Validacion de la regla de negocio 31
            if(idPersonalQuincena == null){
                personalQuincenaService.insertRegistroSupl(1,1,idEmpleado,idQuincena);
            }
            else{
                PersonalQuincena personalQuincena = personalQuincenaService.findAllByPersonalQuincena(idEmpleado,idQuincena);
                if(personalQuincena==null){
                    personalQuincenaService.insertRegistroSupl(idPersonalQuincena+1,1,idEmpleado,idQuincena);
                }else{
                    Integer diasUsados = personalQuincena.getJustiftiemposuplementario();
                    if (diasUsados==null){
                        diasUsados=0;
                    }
                    if(reglasNegocio.rn29(diasUsados)){
                        personalQuincenaService.updateSuplementarioQuincena(diasUsados+1,idEmpleado,idQuincena);
                    }
                    else{
                        errorRN29 =1;
                        LOG.info("ERROR REGLA DE NEGOCIO 29");
                        return REDIRECT + incidencia.getIdIncidencia();
                    }
                }
            }
        for (Integer id:justificateTiempoSuplModel.getIdSeleccionados()
                ) {
            LOG.info(id);
            TiempoSuplGenerado tiempo = tiempoSuplGeneradoService.findById(id);
            horasFaltantes = horasFaltantes - tiempo.getHoras().getHour();
            if(horasFaltantes<0){
                tiempoSuplGeneradoService.updatetiempoUsados(tiempo.getId(),horasFaltantes*(-1),-1);
                justificateTiempoSuplModel.setTiempocubrir(incidencia.getHorasFaltantes());
                break;
            }else if(horasFaltantes==0){
                tiempoSuplGeneradoService.updatetiempoUsados(tiempo.getId(),tiempo.getHoras().getHour(),1);
                justificateTiempoSuplModel.setTiempocubrir(incidencia.getHorasFaltantes());
                break;
            }
            tiempoSuplGeneradoService.updatetiempoUsados(tiempo.getId(),tiempo.getHoras().getHour(),1);
        }
        if(horasFaltantes>0){
            justificateTiempoSuplModel.setTiempocubrir(incidencia.getHorasFaltantes()-horasFaltantes);
        }
        tiempoSuplementarioService.saveJustificanteTS(justificateTiempoSuplModel,incidencia);
        return "redirect:/personal/justificantes?add=1";
    }
}
