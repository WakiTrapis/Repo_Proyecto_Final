package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
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
	
	@GetMapping("/Dashboard/Tatuajes")
	public String PintarDashboardTatuajes(@RequestParam(name = "idEditar", required = false) Long idEditar,Model model) {
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
		
		return "DashboardTatuajes";
	}
	
	@PostMapping("/nuevoTatuajeCompleto")
	public String submit(@ModelAttribute("formularioTatuaje") Tatuaje tatuaje, Model model,@RequestParam("archivoImagen") MultipartFile archivo){
		if (!archivo.isEmpty()) {
	        String nombreFoto = fotosService.store(archivo);
	        tatuaje.setImagenTatuaje(nombreFoto);
	    }
		tatuajeService.save(tatuaje);
		return "redirect:/Dashboard/Tatuajes";
	}
	
	@PostMapping("/Dashboard/Tatuajes/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioTatuaje") Tatuaje tatuaje ) {
		tatuajeService.edit(tatuaje);
		return "redirect:/Dashboard/Tatuajes";
	}
	
	
	
}
