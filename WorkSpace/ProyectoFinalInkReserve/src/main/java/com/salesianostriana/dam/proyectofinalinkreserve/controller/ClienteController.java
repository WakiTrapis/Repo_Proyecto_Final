package com.salesianostriana.dam.proyectofinalinkreserve.controller;

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
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;
	
	@GetMapping("/Dashboard/Clientes")
	public String PintarDashboardClientes(@RequestParam(name = "idEditar", required = false) Long idEditar, Model model) {
		List<Cliente> lista = clienteService.findAll();
		
		model.addAttribute("listaClientes", lista);
		model.addAttribute("formularioCliente", new Cliente());
		
		if (idEditar != null) {
			model.addAttribute("formularioCliente", clienteService.findById(idEditar).get());
		} else {
			model.addAttribute("formularioCliente", new Cliente());
		}
		
		return "DashboardClientes";
	}
	
	@PostMapping("/nuevoClienteCompleto")
	public String submit(@ModelAttribute("formularioCliente") Cliente cliente, Model model){
		clienteService.save(cliente);
		return "redirect:/Dashboard/Clientes";
	}
	
	@GetMapping("/Dashboard/Clientes/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioCliente") Cliente cliente ) {
		clienteService.edit(cliente);
		return "redirect:/Dashboard/Clientes";
	}
	
	@GetMapping("/Dashboard/Clientes/Eliminar/{id}")
	public String submitEliminar(@PathVariable("id") Long id, Model model) {
	    Optional<Cliente> cliente = clienteService.findById(id);
	    if (cliente.isPresent()) {
	        clienteService.delete(cliente.get());
	    }
	    return "redirect:/Dashboard/Clientes";
	}
	
}
