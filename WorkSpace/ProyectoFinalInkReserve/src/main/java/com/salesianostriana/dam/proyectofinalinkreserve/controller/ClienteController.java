package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.DniInvalidoException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService clienteService;
	private final CitaService citaService;
	private final TatuajeService tatuajeService;
	
	private void cargarDatosDashboard(Model model, String search, int page, int size) {
        List<Cliente> topClientes = citaService.obtenerTop3ClientesFrecuentes();
        Pageable pageable = PageRequest.of(page, size);
        Page<Cliente> clientePage;
        
        model.addAttribute("topClientes", topClientes);

        if (search != null && !search.trim().isEmpty()) {
            clientePage = clienteService.buscarPorNombreClientePaginado(search.trim(), pageable);
            model.addAttribute("search", search);
        } else {
            clientePage = clienteService.findAllPaginado(pageable);
        }
        
        model.addAttribute("listaClientes", clientePage.getContent());
        model.addAttribute("currentPage", clientePage.getNumber());
        model.addAttribute("totalPages", clientePage.getTotalPages());
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
	        clienteService.save(cliente);
	        return "redirect:/Dashboard/Clientes";	
		} catch (DniInvalidoException ex) {
			model.addAttribute("errorDni", ex.getMessage());
	        model.addAttribute("formularioArtista", cliente);
	        model.addAttribute("abrirModal", true);
	        cargarDatosDashboard(model, null, 0, 5);
	        return "DashboardClientes";
	        }
	}
	
	@PostMapping("/Dashboard/Clientes/Editar/submit")
	public String submitEditar(@Valid @ModelAttribute("formularioCliente") Cliente cliente,
			 Model model ) {
		try {
			clienteService.editarCliente(cliente);
			return "redirect:/Dashboard/Clientes";
			} catch (DniInvalidoException ex) {
				model.addAttribute("errorDni", ex.getMessage());
		        model.addAttribute("formularioCliente", cliente);
		        model.addAttribute("abrirModal", true);
		        cargarDatosDashboard(model, null, 0, 5);
	            return "DashboardClientes";
			}
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
