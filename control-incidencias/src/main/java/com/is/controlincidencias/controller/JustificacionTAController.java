package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.converter.StringToLocalDate;
import com.is.controlincidencias.converter.TipoAConverter;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.TipoA;
import com.is.controlincidencias.model.JustificanteTAModel;
import com.is.controlincidencias.service.JustificanteService;
import com.is.controlincidencias.service.JustificanteTAService;
import com.is.controlincidencias.service.LicPaternidadService;
import com.is.controlincidencias.service.PersonalService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
        String email = "";
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
    private String guardarJustificanteTA(@ModelAttribute("justificanteTAModel") JustificanteTAModel justificanteTAModel, @RequestParam("file") List<MultipartFile> files) {
        LOG.info("Datos que me llegan "+justificanteTAModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        Justificante justificante = new Justificante();

        justificanteTAModel.setLicenciaArchivo(files.get(0).getOriginalFilename());
        justificanteTAModel.setIdunidadmedica(justificanteTAModel.getIdunidadmedica());
        justificante.setPersonal(personal);
        LocalDate inicio = StringToLocalDate.tryParseDate(justificanteTAModel.getInicio());
        LocalDate fin = StringToLocalDate.tryParseDate(justificanteTAModel.getFin());
        if(justificanteTAModel.getFolio().length() != 12){
            errorFolio=1;
            return REDIRECTURL+idIncidencia;
        }
        //Obtengo el tipo de justificante tipoA
        if(inicio.isAfter(fin)){
            errorf=1;
            return REDIRECTURL+idIncidencia;
        }
        if (files.isEmpty()) {
            error = 1;
            return REDIRECTURL+idIncidencia;
        }
        try {
         //Aqui trato de subir el archivo
            idJustificante = justificanteTAService.saveJustificanteTA(justificanteTAModel, justificante,idIncidencia);
            licPaternidadService.subirArchivo(files, idJustificante);
            LOG.info("Aqui trato de subir el archivo");
        } catch (IOException e) {
            LOG.error(e);
        }
        return "redirect:/personal/justificantes";
    }

    @GetMapping("/tipoa/cancelar")
    public String cancelarTipoA(){
        return "redirect:/personal/incidencias";
    }


}
