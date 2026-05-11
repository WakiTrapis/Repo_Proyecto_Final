package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;

@Controller
public class ArtistaController {

	
	@GetMapping("/nuevoArtista")
	public String ShowFormNuevoArtista(Model model) {
		Artista artista = new Artista();
		model.addAttribute("formularioArtista",artista);
		return "FormularioArtistas";
	}

	
	@PostMapping("/nuevoArtistaCompleto")
	public String submit(@ModelAttribute("formularioCompletoArtista") Artista artista, Model model){
		model.addAttribute("artista", artista);
		return "DashboardArtistas";
	}
}

