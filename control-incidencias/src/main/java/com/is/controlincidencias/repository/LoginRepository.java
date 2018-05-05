package com.is.controlincidencias.repository;

import com.is.controlincidencias.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<Login, Serializable> {
    Login findByCorreo(String correo);
}
