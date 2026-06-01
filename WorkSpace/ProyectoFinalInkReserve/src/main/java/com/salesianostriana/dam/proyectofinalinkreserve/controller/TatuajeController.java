package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.TarifaInvalidaException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TatuajeController {
	
	private final TatuajeService tatuajeService;
	private final ArtistaService artistaService;
	private final ClienteService clienteService;
	
	/**
	 * Método auxiliar para cargar los datos comunes del dashboard de tatuajes, incluyendo la lista de tatuajes filtrada y paginada, así como las listas completas de artistas y clientes para los formularios.
	 * @param model
	 * @param filtro
	 * @param search
	 * @param page
	 * @param size
	 */
	private void cargarDatosDashboard(Model model, String filtro, String search, int page, int size) {
        model.addAllAttributes(tatuajeService.getDatosDashboard(filtro, search, page, size));
        model.addAttribute("listaArtistas", artistaService.findAll());
        model.addAttribute("listaClientes", clienteService.findAll());
    }
	
	
	@GetMapping("/Dashboard/Tatuajes")
	public String PintarDashboardTatuajes(@RequestParam(name = "idEditar", required = false) Long idEditar,
			@RequestParam(name = "verTatuId", required = false) Long verTatuId,
			@RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "8") int size,
			@RequestParam(value = "filtro", required = false) String filtro,
			Model model) {
	    
		if (!model.containsAttribute("formularioTatuaje")) {
			if (idEditar != null && tatuajeService.findById(idEditar).isPresent()) {
				model.addAttribute("formularioTatuaje", tatuajeService.findById(idEditar).get());
			} else {
				model.addAttribute("formularioTatuaje", new Tatuaje());
		    }
		}
		
		cargarDatosDashboard(model,filtro, search,page, size);
		
		if (verTatuId != null && tatuajeService.findById(verTatuId).isPresent()) {
	       
	        model.addAttribute("fichaTatuaje", tatuajeService.findById(verTatuId).get());
	    } else {
	        model.addAttribute("fichaTatuaje", null);
	    }
		
		return "DashboardTatuajes";
	}
	
	
	@PostMapping("/nuevoTatuajeCompleto")
	public String submit(@Valid @ModelAttribute("formularioTatuaje") Tatuaje tatuaje,
			BindingResult bindingResult,
			Model model){
		if (bindingResult.hasErrors()) {
			model.addAttribute("abrirModalTatuaje", true);
			model.addAttribute("listaArtistas", artistaService.findAll());
	        model.addAttribute("listaClientes", clienteService.findAll());
	        cargarDatosDashboard(model, null, null, 0, 8); 
	        return "DashboardTatuajes";
		}
		try {
			tatuajeService.save(tatuaje);
			return "redirect:/Dashboard/Tatuajes";
	} catch (TarifaInvalidaException ex) {
		model.addAttribute("errorTarifa", ex.getMessage());
        model.addAttribute("formularioTatuaje", tatuaje);
        model.addAttribute("abrirModal", true);
        model.addAttribute("listaArtistas", artistaService.findAll());
        model.addAttribute("listaClientes", clienteService.findAll());
        cargarDatosDashboard(model, null,null, 0, 8);
        return "DashboardTatuajes";
        }
	}
	
	
	@PostMapping("/Dashboard/Tatuajes/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioTatuaje") Tatuaje tatuaje, 
			Model model) {
		try {
			tatuajeService.editarTatuaje(tatuaje);
			return "redirect:/Dashboard/Tatuajes";
			} catch (TarifaInvalidaException ex) {
				model.addAttribute("errorTarifa", ex.getMessage());
		        model.addAttribute("formularioTatuaje", tatuaje);
		        model.addAttribute("abrirModalTatuaje", true);
		        model.addAttribute("listaArtistas", artistaService.findAll());
		        model.addAttribute("listaClientes", clienteService.findAll());
		        cargarDatosDashboard(model, null, null, 0, 5);
	            return "DashboardTatuajes";
			}
	}
	

	@GetMapping("/Dashboard/Tatuajes/Eliminar/{id}")
    public String eliminarTatuaje(@PathVariable("id") Long id) {
        tatuajeService.eliminarTatuaje(id);
        return "redirect:/Dashboard/Tatuajes";
    }
	
	
	
	
}
