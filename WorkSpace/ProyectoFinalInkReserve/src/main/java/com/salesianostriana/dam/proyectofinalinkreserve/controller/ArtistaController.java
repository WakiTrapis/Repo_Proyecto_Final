package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ArtistaRepository;

@Controller
public class ArtistaController {

	@Autowired
	private ArtistaRepository artistaRepository;
	
	@GetMapping("/Dashboard/Artistas")
	public String PintarDashboardArtistas(Model model) {
		List<Artista> lista = artistaRepository.findAll();
		model.addAttribute("listaArtistas", lista);
		model.addAttribute("formularioArtista", new Artista());
		
		return "DashboardArtistas";
	}
	
	@PostMapping("/nuevoArtistaCompleto")
	public String submit(@ModelAttribute("formularioArtista") Artista artista,@RequestParam("imagenArtista") MultipartFile imagenArtista, Model model){
		if (!imagenArtista.isEmpty()) {
	        String nombreArchivo=imagenArtista.getOriginalFilename();
	        artista.setFoto(nombreArchivo);
	    }
		artistaRepository.save(artista);
		return "redirect:/Dashboard/Artistas";
	}
}

