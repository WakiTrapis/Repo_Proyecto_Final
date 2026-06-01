package com.salesianostriana.dam.proyectofinalinkreserve;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.salesianostriana.dam.proyectofinalinkreserve.model.User;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.UserRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.usuario.UserRol;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UserDataSeed {

	private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    @PostConstruct
    public void init() {

        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .userRol(UserRol.ADMIN)
                    .build();
            usuarioRepository.save(admin);
        }

        if (usuarioRepository.findByUsername("user").isEmpty()) {
            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user"))
                    .userRol(UserRol.USER)
                    .build();
            usuarioRepository.save(user);
        }
    }
}
