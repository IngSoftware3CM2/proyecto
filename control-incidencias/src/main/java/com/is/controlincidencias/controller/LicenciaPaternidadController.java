package com.is.controlincidencias.controller;


import com.is.controlincidencias.constants.Constants;
import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.LicPaternidad;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.model.LicPaternidadModel;
import com.is.controlincidencias.service.IncidenciaService;
import com.is.controlincidencias.service.JustificanteService;
import com.is.controlincidencias.service.LicPaternidadService;
import com.is.controlincidencias.service.PersonalService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/personal/justificantes/paternidad")

public class LicenciaPaternidadController {

    int idIncidencia, noEmpleado;
    LicPaternidad licPaternidadModificar = new LicPaternidad();

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaService incidenciaService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalService personalService;

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteService justificanteService;


    private static final Log LOG = LogFactory.getLog(LicenciaPaternidadController.class);

    @GetMapping("/cancel")
    private String cancel(){
        //return "redirect:/contacts/showcontacts";
        return "ver-incidencias";
    }

    @GetMapping("/agregar")
    private String RedirectSolicitudLicenciaPaternidadForm(Model model,@RequestParam(name="id")Integer idincidencia,@RequestParam(name="noEmpleado")Integer noempleado) {
        noEmpleado = noempleado;
        LicPaternidadModel licPaternidadModel = new LicPaternidadModel();
        idIncidencia=idincidencia;
        Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
        model.addAttribute("licPaternidadModel", licPaternidadModel);
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        model.addAttribute("noTajerta",personal.getNoTarjeta().toString());
        model.addAttribute("fecha",incidencia.getFechaRegistro().toString());
        return Constants.JUSTIFICANTE_P;
    }

    @GetMapping("/modificar")
    private String RedirectSolicitudLicenciaPaternidadFormModificar(Model model,@RequestParam(name="idjustificante")Integer idjustificante) {
        if (idjustificante!=0){
            LicPaternidad licPaternidad = licPaternidadService.buscarLicPaternidadPorIdjustificante(idjustificante);
            LicPaternidadModel licPaternidadModel = new LicPaternidadModel();
            licPaternidadModel.setJustificacion(licPaternidad.getJustificacion());
            licPaternidadModificar = licPaternidad;
            model.addAttribute("actamatrimonio",licPaternidad.getActamatrimonio());
            model.addAttribute("actanacimiento",licPaternidad.getActanacimiento());
            model.addAttribute("comprobanteingresos",licPaternidad.getComprobanteingresos());
            model.addAttribute("copiaidentificacion",licPaternidad.getCopiaidentificacion());
            model.addAttribute("registrolicencia",licPaternidad.getRegistrolicencia());
            model.addAttribute("constanciacurso",licPaternidad.getConstanciacurso());

            model.addAttribute("licPaternidadModel",licPaternidadModel);

        }

        return "justificantePaternidad/form-justificante-paternidad-modificar";
    }


        @PostMapping("/add-lic-paternidad")
    private String GuardarLicPaternidad(@ModelAttribute("licPaternidadModel") LicPaternidadModel licPaternidadModel,@RequestParam("file") List<MultipartFile> files) {
        LOG.info("Datos que me llegan "+licPaternidadModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        licPaternidadModel.setRegistrolicencia(files.get(0).getOriginalFilename());
        licPaternidadModel.setActanacimiento(files.get(1).getOriginalFilename());
        licPaternidadModel.setActamatrimonio(files.get(2).getOriginalFilename());
        licPaternidadModel.setConstanciacurso(files.get(3).getOriginalFilename());
        licPaternidadModel.setCopiaidentificacion(files.get(4).getOriginalFilename());
        licPaternidadModel.setComprobanteingresos(files.get(5).getOriginalFilename());
        try {

            int idjustificante = licPaternidadService.guardarLicPaternidad(licPaternidadModel, idIncidencia, noEmpleado);
            licPaternidadService.subirArchivo(files,idjustificante);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/personal/justificantes";
    }

    @PostMapping("/update-lic-paternidad")
    private String updateLicPaternidad(@ModelAttribute("licPaternidadModel") LicPaternidadModel licPaternidadModel,@RequestParam("file") List<MultipartFile> files) {
        LOG.info("Datos que me llegan "+licPaternidadModel.toString());
        //Necesito crear un justificante, darlo de alte en la base y despues utilizarlo
        if (true){

        }
        licPaternidadModel.setRegistrolicencia(files.get(0).getOriginalFilename());
        licPaternidadModel.setActanacimiento(files.get(1).getOriginalFilename());
        licPaternidadModel.setActamatrimonio(files.get(2).getOriginalFilename());
        licPaternidadModel.setConstanciacurso(files.get(3).getOriginalFilename());
        licPaternidadModel.setCopiaidentificacion(files.get(4).getOriginalFilename());
        licPaternidadModel.setComprobanteingresos(files.get(5).getOriginalFilename());
        try {
            int idjustificante = licPaternidadService.guardarLicPaternidad(licPaternidadModel, idIncidencia, noEmpleado);
            licPaternidadService.subirArchivo(files,idjustificante);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/personal/justificantes";
    }

}
