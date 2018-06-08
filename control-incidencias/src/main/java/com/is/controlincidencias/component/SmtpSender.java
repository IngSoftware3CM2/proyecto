package com.is.controlincidencias.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SmtpSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String to, String subject, String body){
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try{
            helper = new MimeMessageHelper(message, true);
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(body, true); // true indicates html
            // Aqui se puede agreagar algunas funcionalidades al helper
        } catch(MessagingException me){

        }
        javaMailSender.send(message);
    }

}
