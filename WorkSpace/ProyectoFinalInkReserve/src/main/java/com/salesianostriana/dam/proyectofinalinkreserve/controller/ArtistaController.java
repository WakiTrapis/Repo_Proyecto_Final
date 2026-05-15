package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArtistaController {

	
	private final ArtistaService artistaService;
	
	
	@GetMapping("/Dashboard/Artistas")
	public String PintarDashboardArtistas(@RequestParam(name = "idEditar", required = false) Long idEditar, Model model) {
		List<Artista> lista = artistaService.findAll();
		
		model.addAttribute("listaArtistas", lista);
		model.addAttribute("formularioArtista", new Artista());
		
		if (idEditar != null) {
			model.addAttribute("formularioArtista", artistaService.findById(idEditar).get());
		} else {
			model.addAttribute("formularioArtista", new Artista());
	    }
		return "DashboardArtistas";
		
	}
	
	@PostMapping("/nuevoArtistaCompleto")
	public String submit(@ModelAttribute("formularioArtista") Artista artista, Model model){
		artistaService.save(artista);
		return "redirect:/Dashboard/Artistas";
	}
	

	
	@PostMapping("/Dashboard/Artistas/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioArtista") Artista artista ) {
		artistaService.edit(artista);
		return "redirect:/Dashboard/Artistas";
	}
}

