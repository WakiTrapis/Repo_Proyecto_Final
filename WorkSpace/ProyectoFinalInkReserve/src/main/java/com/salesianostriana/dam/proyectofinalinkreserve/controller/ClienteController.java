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
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;
	private final CitaService citaService;
	private final TatuajeService tatuajeService;
	
	@GetMapping("/Dashboard/Clientes")
	public String PintarDashboardClientes(@RequestParam(name = "idEditar", required = false) Long idEditar,@RequestParam(name = "verPerfilId", required = false) Long verPerfilId, Model model) {
		List<Cliente> lista = clienteService.findAll();
		
		model.addAttribute("listaClientes", lista);
		model.addAttribute("formularioCliente", new Cliente());
		
		if (idEditar != null) {
			model.addAttribute("formularioCliente", clienteService.findById(idEditar).get());
		} else {
			model.addAttribute("formularioCliente", new Cliente());
		}
		if (verPerfilId != null && clienteService.findById(verPerfilId).isPresent()) {
	        Cliente cliente = clienteService.findById(verPerfilId).get();
	        model.addAttribute("perfilCliente", cliente);
	    } else {
	        model.addAttribute("perfilCliente", null);
	    }
		
		return "DashboardClientes";
	}
	
	@PostMapping("/nuevoClienteCompleto")
	public String submit(@ModelAttribute("formularioCliente") Cliente cliente, Model model){
		clienteService.save(cliente);
		return "redirect:/Dashboard/Clientes";
	}
	
	@PostMapping("/Dashboard/Clientes/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioCliente") Cliente cliente ) {
		clienteService.edit(cliente);
		return "redirect:/Dashboard/Clientes";
	}
	
	@GetMapping("/Dashboard/Clientes/Eliminar/{id}")
	public String eliminarCliente(@PathVariable("id") Long id) {
	    Optional<Cliente> clienteOpt = clienteService.findById(id);
	    if (clienteOpt.isPresent()) {
	        Cliente cliente = clienteOpt.get();
	        LocalDateTime ahora = LocalDateTime.now();       
	        if (cliente.getTatuajes() != null && !cliente.getTatuajes().isEmpty()) {
	            List<Tatuaje> tatuajesCliente = new ArrayList<>(cliente.getTatuajes());
	            for (Tatuaje tatuaje : tatuajesCliente) {              
	                if (tatuaje.getCitas() != null && !tatuaje.getCitas().isEmpty()) {
	                    List<Cita> citasTatuaje = new ArrayList<>(tatuaje.getCitas());
	                    
	                    for (Cita cita : citasTatuaje) {
	                        if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                            cita.setTatuaje(null);
	                            citaService.save(cita);
	                        } else {
	                            citaService.delete(cita);
	                        }
	                    }
	                }
	                tatuaje.setArtista(null);
	                tatuajeService.delete(tatuaje);
	            }
	        }   
	        if (cliente.getCitas() != null && !cliente.getCitas().isEmpty()) {
	            List<Cita> citasDirectas = new ArrayList<>(cliente.getCitas());
	            for (Cita cita : citasDirectas) {
	                if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                    cita.setCliente(null);
	                    citaService.save(cita);
	                } else {
	                    citaService.delete(cita);
	                }
	            }
	        }     
	        clienteService.delete(cliente);
	    }
	    
	    return "redirect:/Dashboard/Clientes";
	}
	
}
