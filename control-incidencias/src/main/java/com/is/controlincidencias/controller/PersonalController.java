package com.is.controlincidencias.controller;

import com.is.controlincidencias.entity.Justificante;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.model.CambioPasswordModel;
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
    @Qualifier("taServiceImpl")
    private JustificanteTAServiceImpl justificanteTAService;

    @Autowired
    @Qualifier("cambioHorarioServiceImpl")
    private CambioHorarioServiceImpl cambioHorarioService;

    @Autowired
    @Qualifier("permisoEconomicoServiceImpl")
    private PermisoEconomicoServiceImpl permisoEconomicoService;



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
        model.addAttribute("contraModel", new CambioPasswordModel());
        model.addAttribute("estado", "nada");
        return CAMBIAR_CONTRA;
    }

    @PostMapping("/perfil/cambiar/confirmar")
    public ModelAndView confirmar(@ModelAttribute("contraModel") CambioPasswordModel cambioPassword, Principal principal) {
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
        } else if (!cambioPassword.getNewPassword().equals(cambioPassword.getVerifyPassword())) {
            LOG.info("confirmar() Las nuevas contraseñas no cuadran");
            estado = "diferente";
        } else {
            LOG.info("confirmar() Chido");
            p.getLogin().setPasswordhash(encoder.encode(cambioPassword.getNewPassword()));
            personalService.actualizarContra(p);
        }
        mav.setViewName(CAMBIAR_CONTRA);
        mav.addObject("estado", estado);
        mav.addObject("contraModel", new CambioPasswordModel());
        return mav;
    }

    @GetMapping("/justificantes")
    public ModelAndView showJustificantes() {
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-justificantes");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
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
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("justificantes", justificantes);
        return mav;
    }

    @GetMapping("/verjustificante")
    public ModelAndView verJustificante() {
        return new ModelAndView("ver-justificante-docente");
    }

    @GetMapping("/incidencias")
    public ModelAndView showIncidencias() {
        int noEmpleado = 22;
        ModelAndView mav = new ModelAndView("ver-incidencias");
        Personal personal = personalService.getPersonalByNoEmpleado(noEmpleado);
        mav.addObject("TipoAndNombre", personal.nombreAndTipoToString());
        mav.addObject("incidencias", incidenciaService.getIncidenciasByPersonal(personal));
        return mav;
    }

    @GetMapping("/removejustificante")
    public ModelAndView removeJustificante(@RequestParam(name = "id", required = true) int idJustificante) {
        justificanteService.removeJustificanteByIdJustificante(idJustificante);
        return showIncidencias();
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
            redirectURL = "redirect:/personal/justificantes/horario/agregar";
        else if (tipo == 4)
            redirectURL = "redirect:/personal/justificantes/economico/agregar";
        return redirectURL;
    }

    @GetMapping("/justificantes/modificar")
    public String redirectJustificanteToModificar(@RequestParam(name = "id") Integer id,
                                                  @RequestParam(name = "tipo") String tipo,
                                                  RedirectAttributes attributes) {
        LOG.info("redirectJustificanteToModificar() id = " + id + " tipoJustificante = " + tipo);
        attributes.addAttribute("id", id);
        String redirectURL = "redirect:/personal";

        if (tipo.equals("Tipo A"))
            redirectURL = "redirect:/personal/justificantes/tipoa/modificar";
        else if (tipo.equals("Licencia Paternidad"))
            redirectURL = "redirect:/personal/justificantes/paternidad/modificar";

        return redirectURL;
    }
}
