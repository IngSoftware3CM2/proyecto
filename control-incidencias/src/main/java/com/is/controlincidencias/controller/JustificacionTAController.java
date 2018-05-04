package com.is.controlincidencias.controller;

import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.JustificanteTAModel;
import com.is.controlincidencias.service.JustificanteTAService;
import com.is.controlincidencias.service.LicPaternidadService;
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
import java.util.List;

@Controller
@RequestMapping("/justificacionTA")
public class JustificacionTAController {

    private static final Log LOG = LogFactory.getLog(JustificacionTAController.class);

    @Autowired
    @Qualifier("taServiceImpl")
    private JustificanteTAService justificanteTAService;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    private int error=0;
    private int noEmpleado = 22 ;
    @GetMapping("/justificanteForm")
        public ModelAndView verJustificante(Model model){
        ModelAndView mav = new ModelAndView(Constants.JUSTIFICANTE_A);
        model.addAttribute("error",error);
        error=0;
        model.addAttribute("justificanteTAModel",new JustificanteTAModel());
        mav.addObject("estados",justificanteTAService.findZonas());
        return mav;
    }


    @PostMapping("/add-justificante-tipoA")
    private String guardarJustificanteTA(@ModelAttribute("justificanteTAModel") JustificanteTAModel justificanteTAModel, @RequestParam("file") List<MultipartFile> files) throws IOException {
        LOG.info("Datos que me llegan "+justificanteTAModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        Justificante justificante = new Justificante();
        justificanteTAModel.setLicenciaArchivo(files.get(0).getOriginalFilename());
        justificanteTAModel.setIdunidadmedica("AS-001");
        justificanteTAModel.setTipo("LM");
        if (files.size() == 0) {
            error=1;
            return Constants.JUSTIFICANTE_A;
        }
        try {
            //Aqui trato de subir el archivo
            licPaternidadService.subirArchivo(files);
            justificanteTAService.saveJustificanteTA(justificanteTAModel,justificante);
            LOG.info("Aqui trato de subir el archivo");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/docente/justificantes";
    }


}
