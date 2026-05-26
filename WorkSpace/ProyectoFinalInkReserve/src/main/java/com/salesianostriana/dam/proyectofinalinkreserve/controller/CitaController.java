package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.proyectofinalinkreserve.exception.CitaSolapadaException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.AgendaCitas;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CitaController {

	private final CitaService citaService;
	private final TatuajeService tatuajeService;
    private final ClienteService clienteService;
    private final ArtistaService artistaService;
	
	@GetMapping("/Dashboard/Citas")
	public String PintarDashboardCitas(@RequestParam(name = "fecha", required = false) String fechaStr,
			@RequestParam(name = "idEditar", required = false) Long idEditar,
			@RequestParam(name = "verPerfilId", required = false) Long verPerfilId,
			@RequestParam(value = "filtro", required = false) String filtro,
			@RequestParam(name = "error", required = false) String error,Model model) {
		
		List<Cita> citasFiltradas;
		AgendaCitas agenda = citaService.getAgendaCitasDia(fechaStr);
		
		if ("sinArtista".equals(filtro)) {
	        citasFiltradas = citaService.obtenerCitasSinArtista();
	        model.addAttribute("filtroActual", "sinArtista");
	    } else {
	    citasFiltradas = agenda.getListaCitas();
	    model.addAttribute("fechaActual", agenda.getFechaActual());
	    model.addAttribute("diaAnteriorStr", agenda.getDiaAnteriorStr());
	    model.addAttribute("diaSiguienteStr", agenda.getDiaSiguienteStr());
	    model.addAttribute("filtroActual", "fecha");
	    }
		model.addAttribute("listaCitas", citasFiltradas);
		model.addAttribute("formularioCita", new Cita());
		model.addAttribute("listaTatuajes", tatuajeService.findAll());
        model.addAttribute("listaClientes", clienteService.findAll());
        model.addAttribute("listaArtistas", artistaService.findAll());
        
        if (!model.containsAttribute("formularioCita")) {
            if (idEditar != null && citaService.findById(idEditar).isPresent()) {
                model.addAttribute("formularioCita", citaService.findById(idEditar).get());
            } else {
                model.addAttribute("formularioCita", new Cita());
            }
        }
        
        
        if (idEditar != null) {
			model.addAttribute("formularioCita", citaService.findById(idEditar).get());
		} else {
			model.addAttribute("formularioCita", new Cita());
		}
        if (verPerfilId != null && citaService.findById(verPerfilId).isPresent()) {
	        Cita cita = citaService.findById(verPerfilId).get();
	        model.addAttribute("fichaCita", cita);
	    } else {
	        model.addAttribute("fichaCita", null);
	    }
        return "DashboardCitas";
	}
	
	@PostMapping("/nuevaCitaCompleta")
    public String guardarCitaConCalculo(@ModelAttribute("formularioCita") Cita cita, RedirectAttributes redirectAttributes, Model model) {
		try {
			citaService.save(cita);
			return "redirect:/Dashboard/Citas";
		} catch (CitaSolapadaException ex) {
			redirectAttributes.addFlashAttribute("errorSolapamiento", ex.getMessage());
			redirectAttributes.addFlashAttribute("formularioCita", cita);
			redirectAttributes.addFlashAttribute("abrirModal", true);
			return "redirect:/Dashboard/Citas";
		}
	}
	
	
	@PostMapping("/Dashboard/Citas/Editar/submit")
	public String submitEditarCita(@ModelAttribute("formularioCita") Cita cita, RedirectAttributes redirectAttributes, Model model) {
		try {
			citaService.edit(cita);
			return "redirect:/Dashboard/Citas";
		} catch (CitaSolapadaException ex) {
			redirectAttributes.addFlashAttribute("errorSolapamiento", ex.getMessage());
			redirectAttributes.addFlashAttribute("formularioCita", cita); 
			redirectAttributes.addFlashAttribute("abrirModal", true);
			return "redirect:/Dashboard/Citas";
		}	
	}
	
	@GetMapping("/Dashboard/Citas/Eliminar/{id}")
	public String eliminarCita(@PathVariable("id") Long id) {
		if (citaService.findById(id).isPresent()) {
			citaService.deleteById(id);
		}
		return "redirect:/Dashboard/Citas";
	}
}
