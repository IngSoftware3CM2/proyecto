package com.is.controlincidencias.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class LoginController {

    private static final Log LOG = LogFactory.getLog(LoginController.class);
    private static final String VISTA_INICIO = "inicio";
    private static final String VISTA_LOGIN = "iniciar-sesion";

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error,
                        @RequestParam(name = "logout", required = false) String logout) {
        LOG.info("login() -- PARAMS: error: " + error + ", logout: " + logout);

        model.addAttribute("error", error);
        model.addAttribute("logout", logout);

        return VISTA_LOGIN;
    }

    @GetMapping("/loginsuccess")
    public String loginSuccess(HttpServletRequest request) {
        String redirect = "redirect:/dch";
        Principal principal = request.getUserPrincipal();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<GrantedAuthority> auths = (List<GrantedAuthority>) auth.getAuthorities();

        LOG.info("loginSuccess() principal = " + principal.getName() + " ROLE = " + auths.get(0));
        LOG.info("loginSuccess() es ROLE_DOC? " + request.isUserInRole("DOC"));
        LOG.info("loginSuccess() es ROLE_DCH? " + request.isUserInRole("DCH"));
        LOG.info("loginSuccess() es ROLE_PAEE? " + request.isUserInRole("PAEE"));

        if (request.isUserInRole("DO") || request.isUserInRole("PAEE"))
            redirect = "redirect:/personal";

        return redirect;
    }

    @PostMapping("/acceder")
    public String acceder() {
        LOG.info("Accedí al metodo acceder del controlador.");
        return VISTA_INICIO;
    }
}
