package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String PintarDashboardCitas(@RequestParam(name = "fecha", required = false) String fechaStr,@RequestParam(name = "idEditar", required = false) Long idEditar,Model model) {
		
		AgendaCitas agenda = citaService.getAgendaCitasDia(fechaStr);
	    
	    model.addAttribute("listaCitas", agenda.getListaCitas());
	    model.addAttribute("fechaActual", agenda.getFechaActual());
	    model.addAttribute("diaAnteriorStr", agenda.getDiaAnteriorStr());
	    model.addAttribute("diaSiguienteStr", agenda.getDiaSiguienteStr());
	    
		model.addAttribute("formularioCita", new Cita());
		model.addAttribute("listaTatuajes", tatuajeService.findAll());
        model.addAttribute("listaClientes", clienteService.findAll());
        model.addAttribute("listaArtistas", artistaService.findAll());
        
        if (idEditar != null) {
			model.addAttribute("formularioCita", citaService.findById(idEditar).get());
		} else {
			model.addAttribute("formulariocita", new Cita());
		}
        return "DashboardCitas";
	}
	
	@PostMapping("/nuevaCitaCompleta")
    public String submit(@ModelAttribute("formularioCita") Cita cita) {
		citaService.save(cita);
		return "redirect:/Dashboard/Citas";
	}
	
	
	@PostMapping("/Dashboard/Citas/Editar/submit")
	public String submitEditarCita(@ModelAttribute("formularioCita") Cita cita) {
		citaService.edit(cita);
			
		return "redirect:/Dashboard/Citas";
	}
}
