package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ArtistaRepository;

@ControllerAdvice
public class NavControllerAdvice {

    private final ArtistaRepository artistaRepository;

    public NavControllerAdvice(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    @ModelAttribute("fotoNavUsuario")
    public String fotoNavUsuario(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) return null;
        return artistaRepository.findByNombreArtista(authentication.getName())
                .map(Artista::getFotoArtista)
                .orElse(null);
    }
}
