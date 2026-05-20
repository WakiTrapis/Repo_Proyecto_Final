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
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.FotosService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TatuajeController {
	
	private final TatuajeService tatuajeService;
	private final FotosService fotosService;
	private final ArtistaService artistaService;
	private final ClienteService clienteService;
	private final CitaService citaService;
	
	@GetMapping("/Dashboard/Tatuajes")
	public String PintarDashboardTatuajes(@RequestParam(name = "idEditar", required = false) Long idEditar,@RequestParam(name = "verTatuId", required = false) Long verTatuId,Model model) {
		List<Tatuaje> lista = tatuajeService.findAll();
		model.addAttribute("listaTatuajes", lista);
		model.addAttribute("formularioTatuaje", new Tatuaje());
		model.addAttribute("listaArtistas", artistaService.findAll());
		model.addAttribute("listaClientes", clienteService.findAll());
		
		if (idEditar != null) {
			model.addAttribute("formularioTatuaje", tatuajeService.findById(idEditar).get());
		} else {
			model.addAttribute("formularioTatuaje", new Tatuaje());
	    }
		if (verTatuId != null && tatuajeService.findById(verTatuId).isPresent()) {
	        Tatuaje tatuaje = tatuajeService.findById(verTatuId).get();
	        model.addAttribute("fichaTatuaje", tatuaje);
	    } else {
	        model.addAttribute("fichaTatuaje", null);
	    }
		return "DashboardTatuajes";
	}
	
	@PostMapping("/nuevoTatuajeCompleto")
	public String submit(@ModelAttribute("formularioTatuaje") Tatuaje tatuaje,@RequestParam("archivoImagen") MultipartFile archivo){
		if (!archivo.isEmpty()) {
	        String nombreFoto = fotosService.store(archivo);
	        tatuaje.setImagenTatuaje(nombreFoto);
	    }
		tatuajeService.save(tatuaje);
		return "redirect:/Dashboard/Tatuajes";
	}
	
	@PostMapping("/Dashboard/Tatuajes/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioTatuaje") Tatuaje tatuaje, @RequestParam("archivoImagen") MultipartFile archivo) {
		tatuajeService.editarTatuaje(tatuaje, archivo);
		return "redirect:/Dashboard/Tatuajes";
	}
	

	@GetMapping("/Dashboard/Tatuajes/Eliminar/{id}")
	public String eliminarTatuaje(@PathVariable("id") Long id) {
	    Optional<Tatuaje> tatuajeOpt = tatuajeService.findById(id);
	    if (tatuajeOpt.isPresent()) {
	        Tatuaje tatuaje = tatuajeOpt.get();
	        LocalDateTime ahora = LocalDateTime.now();
	        if (tatuaje.getCitas() != null) {
	            List<Cita> citas = new ArrayList<>(tatuaje.getCitas());
	            for (Cita cita : citas) {
	                if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                    cita.setTatuaje(null);
	                    citaService.save(cita);
	                } else {

	                    citaService.delete(cita);
	                }
	            }
	        }
	        tatuajeService.delete(tatuaje);
	    }
	    
	    return "redirect:/Dashboard/Tatuajes";
	}
	
	
	
	
}
