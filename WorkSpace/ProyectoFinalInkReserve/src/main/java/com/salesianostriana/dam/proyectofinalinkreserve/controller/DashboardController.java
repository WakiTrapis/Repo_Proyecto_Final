package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;


@Controller
public class DashboardController {

	@GetMapping("/Dashboard")
	public String PintarDashboardPrincipal(Model model) {
		model.addAttribute("formularioArtista", new Artista());
		return "DashboardPrincipal";
	}
	
	
	
}
