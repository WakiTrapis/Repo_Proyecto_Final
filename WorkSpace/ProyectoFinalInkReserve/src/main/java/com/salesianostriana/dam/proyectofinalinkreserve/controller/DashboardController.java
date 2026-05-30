package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;


import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.EstadoTatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DashboardController {
	
	private final TatuajeService tatuajeService;
	private final ArtistaService artistaService;
	private final ClienteService clienteService;
	private final CitaService citaService;

	@GetMapping("/Dashboard")
	public String PintarDashboardPrincipal(Model model) {
		model.addAttribute("listaTatuajes", tatuajeService.findAll());
        model.addAttribute("listaClientes", clienteService.findAll());
        model.addAttribute("listaArtistas", artistaService.findAll());
		model.addAttribute("formularioArtista", new Artista());
		model.addAttribute("formularioCliente", new Cliente());
		model.addAttribute("formularioTatuaje", new Tatuaje());
		model.addAttribute("formularioCita", new Cita());
		List<Cliente> topClientes = citaService.obtenerTop3ClientesFrecuentes();
	    List<Artista> topArtistas = citaService.obtenerTop3ArtistasMasDemandados();
	    model.addAttribute("topClientes", topClientes);
	    model.addAttribute("topArtistas", topArtistas);
	    model.addAttribute("proximasCitas", citaService.obtenerProximasCitas());
	    model.addAttribute("tatuajesDiseno", tatuajeService.contarPorEstado(EstadoTatuaje.DISENO));
	    model.addAttribute("tatuajesEnProceso", tatuajeService.contarPorEstado(EstadoTatuaje.EN_PROCESO));
	    model.addAttribute("tatuajesTatuados", tatuajeService.contarPorEstado(EstadoTatuaje.TATUADO));
		return "DashboardPrincipal";
	}
	
	
	
}
