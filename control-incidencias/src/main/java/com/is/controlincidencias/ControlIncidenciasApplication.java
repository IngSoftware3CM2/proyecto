package com.is.controlincidencias;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
public class ControlIncidenciasApplication {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        log.info("hola = " +  encoder.encode("hola"));
        SpringApplication.run(ControlIncidenciasApplication.class, args);
    }

}