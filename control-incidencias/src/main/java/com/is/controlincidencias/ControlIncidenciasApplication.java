package com.is.controlincidencias;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
@Slf4j
@SpringBootApplication
public class ControlIncidenciasApplication {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("Password de prueba: hola = " +  encoder.encode("hola"));
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }
}
*/

/* ESTA CLASE ES PARA EL DEPLOY, NO LA BORREN PORQUE SE ME OLVIDA */
@Slf4j
@SpringBootApplication
public class ControlIncidenciasApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ControlIncidenciasApplication.class);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        SpringApplication.run(ControlIncidenciasApplication.class, args);
        log.info("Password de prueba: hola = " +  encoder.encode("hola"));
    }
}