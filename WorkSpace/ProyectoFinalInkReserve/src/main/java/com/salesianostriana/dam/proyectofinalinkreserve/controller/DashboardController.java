package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

	@GetMapping("/Dashboard")
	public String PintarDashboardPrincipal(Model model) {
		model.addAttribute("usuario", new Object() {
	        public String username = "Salesianos";
	    });
		return "DashboardPrincipal";
	}
	
	@GetMapping("/Dashboard/Artistas")
	public String PintarDashboardArtistas(Model model) {
		model.addAttribute("usuario", new Object() {
	        public String username = "Salesianos";
	    });
		return "DashboardArtistas";
	}
	
	
}
