package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DashboardController {
	
	private final ArtistaService artistaService;
	private final ClienteService clienteService;

	@GetMapping("/Dashboard")
	public String PintarDashboardPrincipal(Model model) {
		model.addAttribute("formularioArtista", new Artista());
		model.addAttribute("formularioCliente", new Cliente());
		model.addAttribute("formularioTatuaje", new Tatuaje());
		model.addAttribute("formularioCita", new Cita());
		model.addAttribute("listaArtistas", artistaService.findAll());
		model.addAttribute("listaClientes", clienteService.findAll());
		return "DashboardPrincipal";
	}
	
	
	
}
