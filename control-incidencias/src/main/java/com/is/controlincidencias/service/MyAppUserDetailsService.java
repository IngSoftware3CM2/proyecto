package com.is.controlincidencias.service;

import com.is.controlincidencias.entity.Login;
import com.is.controlincidencias.repository.LoginRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service("userService")
public class MyAppUserDetailsService implements UserDetailsService {

    @Autowired
    @Qualifier("loginRepository")
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("loadUserByUsername() email = " + s);
        Login login = loginRepository.findByCorreo(s);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if (login != null)
            grantedAuthorities.add(new SimpleGrantedAuthority(login.getPersonal().getTipo()));

        return new User(login.getCorreo(), login.getPasswordhash(), grantedAuthorities);
    }
}
