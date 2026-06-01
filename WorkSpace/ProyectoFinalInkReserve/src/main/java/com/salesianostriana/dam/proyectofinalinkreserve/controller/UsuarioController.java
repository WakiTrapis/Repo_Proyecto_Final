package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectofinalinkreserve.model.User;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/cambiar-contrasena")
    public String mostrarFormulario(Model model) {
        return "cambiar-contrasena";
    }

    @PostMapping("/cambiar-contrasena")
    public String cambiarContrasena(
            @RequestParam String nuevaPassword,
            @RequestParam String confirmarPassword,
            Authentication authentication,
            Model model) {

        if (!nuevaPassword.equals(confirmarPassword)) {
            model.addAttribute("error", "Las contraseñas no coinciden.");
            return "cambiar-contrasena";
        }

        if (nuevaPassword.length() < 4) {
            model.addAttribute("error", "La contraseña debe tener al menos 4 caracteres.");
            return "cambiar-contrasena";
        }

        User user = (User) authentication.getPrincipal();
        user.setPassword(passwordEncoder.encode(nuevaPassword));
        userRepository.save(user);

        return "redirect:/cambiar-contrasena?exito";
    }
}
