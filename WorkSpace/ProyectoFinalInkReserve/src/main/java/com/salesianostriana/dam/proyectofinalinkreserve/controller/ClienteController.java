package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;
	
	@GetMapping("/Dashboard/Clientes")
	public String PintarDashboardClientes(Model model) {
		List<Cliente> lista = clienteService.findAll();
		model.addAttribute("listaClientes", lista);
		model.addAttribute("formularioCliente", new Cliente());
		
		return "DashboardClientes";
	}
	
	@PostMapping("/nuevoClienteCompleto")
	public String submit(@ModelAttribute("formularioCliente") Cliente cliente, Model model){
		clienteService.save(cliente);
		return "redirect:/Dashboard/Clientes";
	}
	
}
