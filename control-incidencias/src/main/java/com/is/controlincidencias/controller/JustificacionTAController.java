package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.converter.StringToLocalDate;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.UnidadMedica;
import com.is.controlincidencias.model.JustificanteTAModel;
import com.is.controlincidencias.service.JustificanteTAService;
import com.is.controlincidencias.service.LicPaternidadService;
import com.is.controlincidencias.service.UnidadMedicaService;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
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
    private PersonalServiceImpl personalService;

    @Autowired
    @Qualifier("unidadMedicaServiceImpl")
    private UnidadMedicaService unidadMedicaService;

    private int error=0;
    private int errorf=0;
    private int noEmpleado = 22 ;
    private Personal personal;
    @GetMapping("/tipoa")
        public ModelAndView verJustificante(Model model,Principal principal){
        personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        ModelAndView mav = new ModelAndView(Constants.JUSTIFICANTE_A);
        model.addAttribute("error",error);
        model.addAttribute("errorf",errorf);
        error=0;
        errorf=0;
        model.addAttribute("justificanteTAModel",new JustificanteTAModel());
        mav.addObject("estados",justificanteTAService.findZonas());
        mav.addObject("tipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("noTarjeta",personal.getNoTarjeta());
        return mav;
    }


    @PostMapping("/tipoa/agregar")
    private String guardarJustificanteTA(@ModelAttribute("justificanteTAModel") JustificanteTAModel justificanteTAModel, @RequestParam("file") List<MultipartFile> files) {
        LOG.info("Datos que me llegan "+justificanteTAModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        Justificante justificante = new Justificante();
        justificanteTAModel.setLicenciaArchivo(files.get(0).getOriginalFilename());
        UnidadMedica unidad = unidadMedicaService.getUnidadMedicaByNombre(justificanteTAModel.getIdunidadmedica());
        justificanteTAModel.setIdunidadmedica(unidad.getIdUnidad());
        justificante.setPersonal(personal);
        LocalDate inicio = StringToLocalDate.tryParseDate(justificanteTAModel.getInicio());
        LocalDate fin = StringToLocalDate.tryParseDate(justificanteTAModel.getFin());
        //Obtengo el tipo de justificante tipoA
        if(inicio.isAfter(fin)){
            errorf=1;
            return "redirect:/personal/justificantes/tipoa";
        }else {
            if (files.isEmpty()) {
                error = 1;
                return "redirect:/personal/justificantes/tipoa";
            }
            try {
                //Aqui trato de subir el archivo
                licPaternidadService.subirArchivo(files);
                justificanteTAService.saveJustificanteTA(justificanteTAModel, justificante);
                LOG.info("Aqui trato de subir el archivo");
            } catch (IOException e) {
                LOG.error(e);
            }
        }
        return "redirect:/personal/justificantes";
    }

    @GetMapping("/tipoa/cancelar")
    public String cancelarTipoA(){
        return "redirect:/personal/incidencias";
    }


}
