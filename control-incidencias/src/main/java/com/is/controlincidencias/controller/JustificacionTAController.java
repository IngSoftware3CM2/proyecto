package com.is.controlincidencias.controller;

import com.is.controlincidencias.component.ReglasNegocio;
import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.converter.TipoAConverter;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.TipoA;
import com.is.controlincidencias.model.JustificanteTAModel;
import com.is.controlincidencias.service.*;
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
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/personal/justificantes")
public class JustificacionTAController {

    private static final Log LOG = LogFactory.getLog(JustificacionTAController.class);

    @Autowired
    @Qualifier("taServiceImpl")
    private JustificanteTAService justificanteTAService;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteService justificanteService;

    @Autowired
    @Qualifier("tipoAConverter")
    private TipoAConverter tipoAConverter;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("notificacionServiceImpl")
    private NotificacionService notificacionService;

    @Autowired
    @Qualifier("reglasNegocioComponent")
    private ReglasNegocio reglasNegocio;

    private int error=0;
    private int errorf=0;
    private int errorFolio=0;
    private Personal personal;
    private int idIncidencia;
    private int idJustificante;
    public static final String REDIRECTURL = "redirect:/personal/justificantes/tipoa?id=";

    @GetMapping("/tipoa")
        public ModelAndView verJustificante(Model model,@RequestParam(name =
            "id") Integer idincidencia,Principal principal){
        //Aqui esta la parte del codigo del correo
        String email = "correo@gmail.com";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        idIncidencia=idincidencia;
        personal = personalService.getPersonalByEmail(email);
        //Aqui termina lo del correo
        ModelAndView mav = new ModelAndView(Constants.JUSTIFICANTE_A);
        model.addAttribute("error",error);
        model.addAttribute("errorf",errorf);
        model.addAttribute("errorFolio",errorFolio);
        error=0;
        errorf=0;
        errorFolio=0;
        model.addAttribute("justificanteTAModel",new JustificanteTAModel());
        mav.addObject("estados",justificanteTAService.findZonas());
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("noTarjeta",personal.getNoTarjeta());
        return mav;
    }

    @GetMapping("/tipoa/modificar")
    public ModelAndView modificarJustificante(@RequestParam("id")int idjustificante, Model model,Principal principal){
        //Aqui esta la parte del codigo del correo
        String email = "";
        if (principal!=null && principal.getName()!=null){
            email=principal.getName();
        }
        personal = personalService.getPersonalByEmail(email);
        //Aqui termina lo del correo
        idJustificante=idjustificante;
        ModelAndView mav = new ModelAndView(Constants.JUSTIFICANTE_A_MOD);
        JustificanteTAModel justificanteTAModel = new JustificanteTAModel();
        model.addAttribute("error",error);
        model.addAttribute("errorf",errorf);
        model.addAttribute("errorFolio",errorFolio);
        error=0;
        errorf=0;
        errorFolio=0;
        if(idJustificante!=0){
            Justificante justificante = justificanteService.findJustificanteById(idJustificante);
            TipoA tipoA = justificanteTAService.findByJustificante(justificante);
            justificanteTAModel = tipoAConverter.entityToModel(tipoA);
        }
        mav.addObject("estados",justificanteTAService.findZonas());
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("noTarjeta",personal.getNoTarjeta());
        model.addAttribute("justificanteTAModel",justificanteTAModel);
        return mav;
    }


    @PostMapping("/tipoa/agregar")
    private String guardarJustificanteTA(@Valid @ModelAttribute("justificanteTAModel") JustificanteTAModel justificanteTAModel,
        @RequestParam("file") List<MultipartFile> files, BindingResult bindingResult) {
        LOG.info("Datos que me llegan "+justificanteTAModel.toString());
        if(bindingResult.hasErrors())
            LOG.info("Errorsito");
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        Justificante justificante = new Justificante();
        Incidencia incidencia = incidenciaService.consultarIncidencia(idIncidencia);
        justificanteTAModel.setLicenciaArchivo(files.get(0).getOriginalFilename());
        justificanteTAModel.setIdunidadmedica(justificanteTAModel.getIdunidadmedica());
        justificante.setPersonal(personal);
        LocalDate fincidencia= incidencia.getFechaRegistro();
        LocalDate inicio = justificanteTAModel.getInicio();
        LocalDate fin = justificanteTAModel.getFin();

        //Validacion de la regla de negocio RN12
        if(reglasNegocio.rn12(inicio,fincidencia)){
            errorf=1;
            LOG.info("ERROR REGLA DE NEGOCIO 12");
            return REDIRECTURL+idIncidencia;
        }
        //Validacion de la regla de negocio RN14
        if(reglasNegocio.rn14(inicio,fin)){
            errorf=1;
            LOG.info("ERROR REGLA DE NEGOCIO 14");
            return REDIRECTURL+idIncidencia;
        }
        //Validacion de la regla de negocio RN15
        if(reglasNegocio.rn15(justificanteTAModel.getFolio())){
            errorFolio=1;
            LOG.info("ERROR REGLA DE NEGOCIO 15");
            return REDIRECTURL+idIncidencia;
        }
        //Validacion de la regla de negocio RN28
        if(reglasNegocio.rn28(inicio,fin)){
            errorf=1;
            LOG.info("ERROR REGLA DE NEGOCIO 28");
            return REDIRECTURL+idIncidencia;
        }
        if (files.isEmpty()) {
            error = 1;
            return REDIRECTURL+idIncidencia;
        }
        try {
         //Aqui trato de subir el archivo
            notificacionService.removeByPersonalAndMotivo(personal.getIdEmpleado(),2);
            idJustificante = justificanteTAService.saveJustificanteTA(justificanteTAModel, justificante,idIncidencia);
            licPaternidadService.subirArchivo(files, idJustificante);
            LOG.info("Aqui trato de subir el archivo");
        } catch (IOException e) {
            LOG.error(e);
        }
        return "redirect:/personal/justificantes?add=1";
    }

    @GetMapping("/tipoa/cancelar")
    public String cancelarTipoA(){
        return "redirect:/personal/incidencias?cancelar=1";
    }
}
