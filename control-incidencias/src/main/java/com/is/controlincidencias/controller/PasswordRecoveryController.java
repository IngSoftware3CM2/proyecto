package com.is.controlincidencias.controller;

import com.is.controlincidencias.component.SmtpSender;
import com.is.controlincidencias.entity.Personal;
import com.is.controlincidencias.service.impl.PersonalServiceImpl;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;

@Controller
public class PasswordRecoveryController {

    private static final String VISTA_PASSWORD_RECOVERY = "password-recovery";
    private static final Log LOG = LogFactory.getLog(JustificacionTAController.class);

    @Autowired
    private SmtpSender smtpSender;

    @Autowired
    @Qualifier("personalServiceImpl")
    private PersonalServiceImpl personalService;

    @GetMapping("/password-recovery")
    public String getVistaPasswordRecovery(Model model) {
        model.addAttribute("estado", "none");
        return VISTA_PASSWORD_RECOVERY;
    }

    @ResponseBody
    @PostMapping("/password-recovery/confirm")
    public ModelAndView confirmar(@RequestParam(value="email") String userEmail) {
        LOG.info("confirmar() PARAMS --user.email= "+userEmail);
        String estado = "exito";
        ModelAndView mav = new ModelAndView();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Personal p = personalService.getPersonalByEmail(userEmail);
        try {
            LOG.info("confirmar() OK: User email exists");
            // Generar nueva contraseña
            String newPassword = generatePassword(30);
            LOG.info("confirmar() GENERATED: "+newPassword);
            // Actualizar la contraseña
            p.getLogin().setPasswordhash(encoder.encode(newPassword));
            personalService.actualizarContra(p);
            // Enviar la nueva contraseña por correo
            String msgBody = generteMessageBody(p, newPassword);
            smtpSender.send(userEmail, "Hemos recuperado tu password", msgBody);
        } catch(NullPointerException npe){
            LOG.error("confirmar() No existe un usuario con este email");
            estado = "error";
        }
        mav.setViewName(VISTA_PASSWORD_RECOVERY);
        mav.addObject("estado", estado);
        return mav;
    }

    public static String generatePassword(int length){
        SecureRandom random = new SecureRandom();
        char[] chars = new char[length];
        for(int i=0;i<chars.length;i++){
            int v = random.nextInt(10 + 26 + 26);
            char c;
            if (v < 10){
                c = (char)('0' + v);
            }
            else if (v < 36) {
                c = (char)('a' - 10 + v);
            }
            else {
                c = (char)('A' - 36 + v);
            }
            chars[i] = c;
        }
        return new String(chars);
    }

    public static String generteMessageBody(Personal personal, String newPassword){
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>Contrase&ntilde;a recuperada.</h1><hr/>");
        sb.append("<p>Hola ").append(personal.getNombre());
        sb.append(", hemos atendido tu solicitud de recuperaci&oacute;n de contrase&ntilde;a, ");
        sb.append("tu nueva contrase&ntilde;a es: <p style=\\'color:blue;\\'>");
        sb.append(newPassword).append("</p>");
        return sb.toString();
    }
}
