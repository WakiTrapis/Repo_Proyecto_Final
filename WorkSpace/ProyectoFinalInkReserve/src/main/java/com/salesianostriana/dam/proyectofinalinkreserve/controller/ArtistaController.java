package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArtistaController {

	
	private final ArtistaService artistaService;
	private final TatuajeService tatuajeService;
	private final CitaService citaService;
	
	
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
	

	
	@GetMapping("/Dashboard/Artistas/Editar/{id}")
	public String submitEditar(@ModelAttribute("formularioArtista") Artista artista ) {
		artistaService.edit(artista);
		return "redirect:/Dashboard/Artistas";
	}
	
	
	@GetMapping("/Dashboard/Artistas/Eliminar/{id}")
	public String submitEliminar(@PathVariable("id") Long id, Model model) {
	    Optional<Artista> artistaEncontrado = artistaService.findById(id);
	    
	    if (artistaEncontrado.isPresent()) {
	        Artista artista = artistaEncontrado.get();
	        LocalDateTime ahora = LocalDateTime.now();
	        
	        
	        if (artista.getTatuajes() != null && !artista.getTatuajes().isEmpty()) {
	            List<Tatuaje> listaTatuajesSegura = new ArrayList<>(artista.getTatuajes());
	            for (Tatuaje tatuaje : listaTatuajesSegura) {
	                tatuaje.setArtista(null);
	                tatuajeService.save(tatuaje);
	            }
	        }
	        
	        
	        if (artista.getCitas() != null && !artista.getCitas().isEmpty()) {
	            List<Cita> listaCitasSegura = new ArrayList<>(artista.getCitas());
	            for (Cita cita : listaCitasSegura) {
	                if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                    cita.setArtista(null);
	                    citaService.save(cita);
	                } else {
	                    citaService.delete(cita);
	                }
	            }
	        }
	        artistaService.delete(artista);
	    }
	    
	    return "redirect:/Dashboard/Artistas";
	}
}

