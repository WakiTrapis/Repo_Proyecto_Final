package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;


@Controller
public class DashboardController {

	@GetMapping("/Dashboard")
	public String PintarDashboardPrincipal(Model model) {
		model.addAttribute("formularioArtista", new Artista());
		model.addAttribute("formularioCliente", new Cliente());
		return "DashboardPrincipal";
	}
	
	
	
}
