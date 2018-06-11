package com.is.controlincidencias.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class LoginController {

    private static final String VISTA_LOGIN = "iniciar-sesion";

    @GetMapping("/login")
    public String login(@RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout, Model model) {
        log.info("login() -- PARAMS: error: " + error + ", logout: " + logout);

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

        log.info("loginSuccess() principal = " + principal.getName() + " ROLE = " + auths.get(0));
        log.info("loginSuccess() es ROLE_DOC? " + request.isUserInRole("DOC"));
        log.info("loginSuccess() es ROLE_DCH? " + request.isUserInRole("DCH"));
        log.info("loginSuccess() es ROLE_PAAE? " + request.isUserInRole("PAAE"));

        if (request.isUserInRole("DOC") || request.isUserInRole("PAAE"))
            redirect = "redirect:/personal";

        return redirect;
    }
}
