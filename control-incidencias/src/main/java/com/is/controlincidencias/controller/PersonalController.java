package com.is.controlincidencias.controller;

import com.is.controlincidencias.component.ReglasNegocio;
import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Motivo;
import com.is.controlincidencias.entity.Notificacion;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.LoginModel;
import com.is.controlincidencias.service.impl.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/personal")
public class PersonalController {
    private static final Log LOG = LogFactory.getLog(PersonalController.class);
    private static final String INICIO = "inicio";
    private static final String PERFIL = "perfil";
    private static final String CAMBIAR_CONTRA = "cambiar-contra";

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

    @Autowired
    @Qualifier("justificanteServiceImpl")
    private JustificanteServiceImpl justificanteService;

    @Autowired
    @Qualifier("incidenciaServiceImpl")
    private IncidenciaServiceImpl incidenciaService;

    @Autowired
    @Qualifier("licPaternidadServiceImpl")
    private LicPaternidadServiceImpl licPaternidadService;

    @Autowired
    @Qualifier ("notificacionServiceImpl")
    private NotificacionServiceImpl notificacionService;

    @Autowired
    @Qualifier("taServiceImpl")
    private JustificanteTAServiceImpl justificanteTAService;

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioServiceImpl cambioHorarioService;

    @Autowired
    @Qualifier("permisoEconomicoServiceImpl")
    private PermisoEconomicoServiceImpl permisoEconomicoService;

    @Autowired
    @Qualifier("reglasNegocioComponent")
    private ReglasNegocio reglasNegocio;

    @GetMapping({"", "/"})
    public String inicio(Model model, Principal principal) {
        if (principal != null)
            LOG.info("inicio() " + principal.getName());
        return INICIO;
    }

    /*
     * Para recuperar el correo deben de agregar el parametro de Pincipal
     * El correo se recupera con getName()
     * Realizar una consulta a la base de datos para recuperar el ID del usuario
     * con base en el email
     * OJO, si se trabaja con el login desactivado al llamar a principal.getName()
     * produce un error
     * */
    @GetMapping("/perfil")
    public String perfil(Model model, Principal principal) {
        LOG.info("perfil()");
        String email = "ariel@gmail.com"; // aqui poner un email por default para que no de error
        if (principal != null && principal.getName() != null)
            email = principal.getName();

        Personal personal = personalService.getPersonalByEmail(email); // Obtenemos el personal (noEmpleado)
        model.addAttribute("datos", personal);
        return PERFIL;
    }

    @GetMapping("/perfil/cambiar")
    public String cambiarContra(Model model) {
        LOG.info("cambiarContra()");
        model.addAttribute("contraModel", new LoginModel());
        model.addAttribute("estado", "nada");
        return CAMBIAR_CONTRA;
    }

    @PostMapping("/perfil/cambiar/confirmar")
    public ModelAndView confirmar(@ModelAttribute("contraModel") LoginModel cambioPassword, Principal principal) {
        LOG.info("confirmar()");
        String estado = "exito";
        ModelAndView mav = new ModelAndView();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String email = "ariel@gmail.com"; // aqui poner un email por default
        if (principal != null && principal.getName() != null)
            email = principal.getName();

        Personal p = personalService.getPersonalByEmail(email);
        if (!encoder.matches(cambioPassword.getOldPassword(), p.getLogin().getPasswordhash())) {
            LOG.info("confirmar() La contraseña vieja no cuadra");
            estado = "error";
        } else if (!reglasNegocio.rn1(cambioPassword.getNewPassword())
                || !reglasNegocio.rn1(cambioPassword.getOldPassword())
                || !reglasNegocio.rn1(cambioPassword.getVerifyPassword())) {
            LOG.info("confirmar() ERROR REGLA DE NEGOCIO 1");
            estado = "error1";
        } else if ( reglasNegocio.rn21(cambioPassword.getNewPassword()) ) {
            LOG.info("confirmar() ERROR REGLA DE NEGOCIO 21");
            estado = "error2";
        }
        else if (!cambioPassword.getNewPassword().equals(cambioPassword.getVerifyPassword())) {
            LOG.info("confirmar() Las nuevas contraseñas no cuadran");
            estado = "diferente";
        } else {
            LOG.info("confirmar() Chido");
            p.getLogin().setPasswordhash(encoder.encode(cambioPassword.getNewPassword()));
            personalService.actualizarContra(p);
        }
        mav.setViewName(CAMBIAR_CONTRA);
        mav.addObject("estado", estado);
        mav.addObject("contraModel", new LoginModel());
        return mav;
    }



    @GetMapping("/justificantes")
    public ModelAndView showJustificantes(Model model, @RequestParam(name = "add", required = false) Integer add,@RequestParam(name = "modificar", required = false) Integer modificar, Principal principal) {
        String email = "a@gmail.com"; // aqui poner un email por default para que no de error
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        ModelAndView mav = new ModelAndView("ver-justificantes");
        Personal personal = personalService.getPersonalByEmail(email);
        List<Justificante> justificantes = justificanteService.getJustificantesByPersonal(personal);
        for (Justificante justificante : justificantes) {
            if (justificanteTAService.existsByIdjustificante(justificante.getIdJustificante())) {
                justificante.setTipo(1);
            } else if (licPaternidadService.existsByIdjustificante(justificante.getIdJustificante())) {
                justificante.setTipo(2);
            } else if (cambioHorarioService.existsByIdjustificante(justificante.getIdJustificante())){
                justificante.setTipo(3);
            } else if (permisoEconomicoService.existsByIdjustificante(justificante.getIdJustificante())) {
                justificante.setTipo(4);
            } else {
                justificante.setTipo(666);
            }
        }
        model.addAttribute("add", add);
        model.addAttribute("modificar", modificar);
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("justificantes", justificantes);
        return mav;
    }

    @GetMapping("/verjustificante")
    public ModelAndView verJustificante() {
        return new ModelAndView("ver-justificante-docente");
    }

    @GetMapping("/incidencias")
    public ModelAndView showIncidencias(Model model,@RequestParam(name = "ano", required = false) Integer ano,@RequestParam(name = "quincena", required = false) Integer quincena,@RequestParam(name = "cancelar", required = false) Integer cancelar, Principal principal) {
        String email = "a@gmail.com"; // aqui poner un email por default para que no de error
        if (principal != null && principal.getName() != null)
            email = principal.getName();
        ModelAndView mav = new ModelAndView("ver-incidencias");
        Personal personal = personalService.getPersonalByEmail(email);
        LOG.info("*****************************************"+cancelar);
        model.addAttribute("cancelar", cancelar);
        model.addAttribute("ano", ano);
        model.addAttribute("quincena", quincena);
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("incidencias", incidenciaService.getIncidenciasByPersonal(personal));
        Integer motivo = new Integer (-1);
        if (notificacionService.existsByPersonal(personal)){
            Notificacion notificacion = notificacionService.findByPersonal(personal);
            motivo = new Integer(notificacion.getMotivo().getIdMotivo());
        }
        mav.addObject("motivo", motivo);
        return mav;
    }


    @GetMapping("/removejustificante")
    public String removeJustificante(@RequestParam(name = "id", required = true) int idJustificante) {
        justificanteService.removeJustificanteByIdJustificante(idJustificante);
        return "redirect:/personal/incidencias?cancelar=0";
    }

    @GetMapping("/justificantes/agregar")
    public String redirectIncidenciaToJustificante(@RequestParam(name = "id") Integer id,
                                                  @RequestParam(name = "tipoJustificante") Integer tipo,
                                                   RedirectAttributes attributes) {
        LOG.info("redirectAgregarJustificante() id = " + id + " tipoJustificante = " + tipo);
        attributes.addAttribute("id", id);
        String redirectURL = "redirect:/personal";

        if (tipo == 1)
            redirectURL = "redirect:/personal/justificantes/tipoa";
        else if (tipo == 2)
            redirectURL = "redirect:/personal/justificantes/paternidad/agregar";
        else if (tipo == 3)
            redirectURL = "redirect:/personal/justificantes/cambiohorario/agregar";
        else if (tipo == 4)
            redirectURL = "redirect:/personal/justificantes/economico/agregar";
        else if(tipo==5)
            redirectURL = "redirect:/personal/justificantes/tiemposuplementario";
        else if(tipo==6)
            redirectURL = "redirect:/personal/justificantes/constanciatiempo/agregar";
        return redirectURL;
    }

    @GetMapping("/justificantes/modificar")
    public String redirectJustificanteToModificar(@RequestParam(name = "id") Integer id,
                                                  @RequestParam(name = "tipo") Integer tipo,
                                                  RedirectAttributes attributes) {
        LOG.info("redirectJustificanteToModificar() id = " + id + " tipoJustificante = " + tipo);
        attributes.addAttribute("id", id);
        String redirectURL = "redirect:/personal";

        if (tipo == 1)
            redirectURL = "redirect:/personal/justificantes/tipoa/modificar";
        else if (tipo == 2)
            redirectURL = "redirect:/personal/justificantes/paternidad/modificar";
        else if (tipo == 3)
            redirectURL = "redirect:/personal/justificantes/cambiohorario/modificar";
        else if (tipo == 4)
            redirectURL = "redirect:/personal/justificantes/economico/modificar";

        return redirectURL;
    }
}
