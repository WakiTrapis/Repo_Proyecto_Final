package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TatuajeController {
	
	private final TatuajeService tatuajeService;
	
	@GetMapping("/Dashboard/Tatuajes")
	public String PintarDashboardTatuajes(Model model) {
		List<Tatuaje> lista = tatuajeService.findAll();
		model.addAttribute("listaTatuajes", lista);
		model.addAttribute("formularioTatuaje", new Tatuaje());
		
		return "DashboardTatuajes";
	}
	
	@PostMapping("/nuevoTatuajeCompleto")
	public String submit(@ModelAttribute("formularioTatuaje") Tatuaje tatuaje, Model model){
		tatuajeService.save(tatuaje);
		return "redirect:/Dashboard/Tatuajes";
	}
	
	
}
