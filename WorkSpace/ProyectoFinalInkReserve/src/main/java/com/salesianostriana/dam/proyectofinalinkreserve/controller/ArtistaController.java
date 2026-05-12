package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArtistaController {

	
	private final ArtistaService artistaService;
	
	
	@GetMapping("/Dashboard/Artistas")
	public String PintarDashboardArtistas(Model model) {
		List<Artista> lista = artistaService.findAll();
		model.addAttribute("listaArtistas", lista);
		model.addAttribute("formularioArtista", new Artista());
		
		return "DashboardArtistas";
	}
	
	@PostMapping("/nuevoArtistaCompleto")
	public String submit(@ModelAttribute("formularioArtista") Artista artista, Model model){
		artistaService.save(artista);
		return "redirect:/Dashboard/Artistas";
	}
}

