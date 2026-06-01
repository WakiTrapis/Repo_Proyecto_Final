package com.salesianostriana.dam.proyectofinalinkreserve.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.proyectofinalinkreserve.exception.CampoDuplicadoException;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.DniInvalidoException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;
	private final CitaService citaService;
	
	/**
	 * Método auxiliar para cargar los datos comunes del dashboard de clientes, incluyendo el top 3 de clientes frecuentes y los datos paginados según la búsqueda.
	 * @param model
	 * @param search
	 * @param page
	 * @param size
	 */
	private void cargarDatosDashboard(Model model, String search, int page, int size) {
        model.addAttribute("topClientes", citaService.obtenerTop3ClientesFrecuentes());
        model.addAllAttributes(clienteService.getDatosDashboard(search, page, size));
    }
	
	@GetMapping("/Dashboard/Clientes")
	public String PintarDashboardClientes(@RequestParam(name = "idEditar", required = false) Long idEditar,
			@RequestParam(name = "verPerfilId", required = false) Long verPerfilId,
			@RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {
		
        if (!model.containsAttribute("formularioCliente")) {
            if (idEditar != null && clienteService.findById(idEditar).isPresent()) {
                model.addAttribute("formularioCliente", clienteService.findById(idEditar).get());
            } else {
                model.addAttribute("formularioCliente", new Cliente());
            }
        }
        cargarDatosDashboard(model, search, page, size);
        
        if (verPerfilId != null && clienteService.findById(verPerfilId).isPresent()) {
            model.addAttribute("perfilCliente", clienteService.findById(verPerfilId).get());
        } else {
            model.addAttribute("perfilCliente", null);
        }
        
        return "DashboardClientes";
    }
	
	@PostMapping("/nuevoClienteCompleto")
	public String submit(@Valid @ModelAttribute("formularioCliente") Cliente cliente, 
			BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
	        model.addAttribute("abrirModalCliente", true);
	        cargarDatosDashboard(model, null, 0, 5); 
	        return "DashboardClientes"; 
	    }
		try {
			clienteService.saveArmor(cliente, cliente.getId() != null ? cliente.getId() : -1L);
	        return "redirect:/Dashboard/Clientes";	
		} catch (DniInvalidoException | CampoDuplicadoException ex) {
			model.addAttribute("errorDni", ex.getMessage());
	        model.addAttribute("formularioCliente", cliente);
	        model.addAttribute("abrirModalCliente", true);
	        cargarDatosDashboard(model, null, 0, 5);
	        return "DashboardClientes";
	        }
	}
	
	@PostMapping("/Dashboard/Clientes/Editar/submit")
	public String submitEditar(@Valid @ModelAttribute("formularioCliente") Cliente cliente,
			 Model model ) {
		try {
			clienteService.editarCliente(cliente, cliente.getId());
			return "redirect:/Dashboard/Clientes";
			} catch (DniInvalidoException | CampoDuplicadoException ex) {
				model.addAttribute("errorDni", ex.getMessage());
		        model.addAttribute("formularioCliente", cliente);
		        model.addAttribute("abrirModalCliente", true);
		        cargarDatosDashboard(model, null, 0, 5);
	            return "DashboardClientes";
			}
	    }
	
	@GetMapping("/Dashboard/Clientes/Eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/Dashboard/Clientes";
    }
	
}
