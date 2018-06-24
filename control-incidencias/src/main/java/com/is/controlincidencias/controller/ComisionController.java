package com.is.controlincidencias.controller;


import com.is.controlincidencias.entity.Incidencia;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.ComisionModel;
import com.is.controlincidencias.service.CambioHorarioService;
import com.is.controlincidencias.service.ComisionService;
import com.is.controlincidencias.service.LicPaternidadService;
import com.is.controlincidencias.service.NotificacionService;
import com.is.controlincidencias.service.impl.IncidenciaServiceImpl;
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

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;



@Controller
@RequestMapping("/personal/justificantes/comisionOficial")
public class ComisionController {
    static final String VISTA_COMISION = "justificanteComision/comisionoficial";
    static final String VISTA_MOD_COMISION = "justificanteCambioHorario/";
    private static final Log LOGGER = LogFactory.getLog(ComisionController.class);

    int idEmpleado;
    int idIncidencia;
    int modCambHorarioJust;
    public static final String HORA_QUINCE = "15:00";

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioService cambioService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaServiceImpl incidenciaService;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadService licPaternidadService;

    @Autowired
    @Qualifier("comisionServiceImpl")
    private ComisionService comisionService;

    @Autowired
    @Qualifier("notificacionServiceImpl")
    private NotificacionService notificacionService;


    @GetMapping("/agregar")
    public ModelAndView registrar(Model model, @RequestParam(name="id")Integer idincidencia)
    {
        Incidencia incidencia = incidenciaService.consultarIncidencia(idincidencia);
        ModelAndView mav = new ModelAndView(VISTA_COMISION);
        LOGGER.info("Accedí al metodo acceder del controlador");
        idIncidencia = idincidencia;
        LOGGER.info("La variable global vale " + idIncidencia);
        idEmpleado = cambioService.getIdEmpleadoByIdIncidencia(idincidencia); //obtengo el numero de empelado
        LOGGER.info("El id de la incidencia es " + idincidencia + " el ID EMPLEADO es " + idEmpleado);
        model.addAttribute("comisionModel", new ComisionModel());
        Personal personal = personalService.getPersonalByIdEmpleado(idEmpleado);
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        model.addAttribute("tarjeta", personal.getNoTarjeta());
        return mav;
    }

    @PostMapping("/addComision")
    public String addComision(@Valid @ModelAttribute("comisionModel") ComisionModel Cmodelo, @RequestParam("file") List<MultipartFile> files)
    {
        ComisionModel com = new ComisionModel();
        com.setIdComision(idEmpleado); //aqui meto el idEmpleado para enviarselo an repository
        Personal personal = personalService.getPersonalByIdEmpleado(idEmpleado);
        com.setInicio(Cmodelo.getInicio());
        com.setFin(Cmodelo.getFin());
        com.setInvitacionArchivo(files.get(0).getOriginalFilename());
        LOGGER.info(com);
        try
            {
                int idjustificante = comisionService.getLastJustificante();
                licPaternidadService.subirArchivo(files, idjustificante);
                comisionService.addComision(com, idIncidencia);
            }
        catch (IOException e)
            {
                LOGGER.error("ERROR:", e);
            }
        notificacionService.removeByPersonalAndMotivo(personal.getIdEmpleado(),3);
        return "redirect:/personal/justificantes?add=1";
    }


}
