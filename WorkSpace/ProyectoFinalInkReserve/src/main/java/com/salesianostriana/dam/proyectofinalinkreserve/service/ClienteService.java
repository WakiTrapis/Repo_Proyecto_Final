package com.salesianostriana.dam.proyectofinalinkreserve.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.DniInvalidoException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ClienteRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.TatuajeRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class ClienteService extends BaseServiceImpl <Cliente, Long, ClienteRepository> {

	private final ClienteRepository clienteRepository;
	private final CitaRepository citaRepository;
	private final TatuajeRepository tatuajeRepository;

	public ClienteService(ClienteRepository repository, ClienteRepository clienteRepository,
            CitaRepository citaRepository, TatuajeRepository tatuajeRepository) {
		super(repository);
		this.clienteRepository = clienteRepository;
		this.citaRepository = citaRepository;
		this.tatuajeRepository = tatuajeRepository;
		}

	public Page<Cliente> findAllPaginado(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }
	
	public Page<Cliente> buscarPorNombreClientePaginado(String criterio, Pageable pageable) {
        return clienteRepository.findByNombreClienteContainingIgnoreCase(criterio, pageable);
    }
	
	public Cliente save(Cliente cliente) {
        if (cliente.getDniCliente() == null || !cliente.getDniCliente().matches("^[0-9]{8}[A-Za-z]$")) {
            throw new DniInvalidoException("El DNI introducido no es válido. Debe constar de 8 números y una letra (Ej: 12345678X).");
        }
        
        return super.save(cliente);
    }
	
	public void editarCliente(Cliente clienteEditado) {
	    if (clienteEditado.getDniCliente() == null || !clienteEditado.getDniCliente().matches("^[0-9]{8}[A-Za-z]$")) {
            throw new DniInvalidoException("El DNI introducido no es válido. Debe constar de 8 números y una letra (Ej: 12345678X).");
        }
	    this.edit(clienteEditado);
	}
	
	public void eliminarCliente(Long id) {
	    findById(id).ifPresent(cliente -> {
	        LocalDateTime ahora = LocalDateTime.now();

	        if (cliente.getTatuajes() != null && !cliente.getTatuajes().isEmpty()) {
	            List<Tatuaje> tatuajesCliente = new ArrayList<>(cliente.getTatuajes());
	            for (Tatuaje tatuaje : tatuajesCliente) {
	                if (tatuaje.getCitas() != null && !tatuaje.getCitas().isEmpty()) {
	                    List<Cita> citasTatuaje = new ArrayList<>(tatuaje.getCitas());
	                    for (Cita cita : citasTatuaje) {
	                        if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                            cita.setTatuaje(null);
	                            citaRepository.save(cita);
	                        } else {
	                            citaRepository.delete(cita);
	                        }
	                    }
	                }
	                tatuaje.setArtista(null);
	                tatuajeRepository.delete(tatuaje);
	            }
	        }

	        if (cliente.getCitas() != null && !cliente.getCitas().isEmpty()) {
	            List<Cita> citasDirectas = new ArrayList<>(cliente.getCitas());
	            for (Cita cita : citasDirectas) {
	                if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                    cita.setCliente(null);
	                    citaRepository.save(cita);
	                } else {
	                    citaRepository.delete(cita);
	                }
	            }
	        }

	        delete(cliente);
	    });
	}
	
	/**
	 * Obtiene los datos necesarios para mostrar en el dashboard de clientes, incluyendo la lista de clientes paginada y el número total de páginas.
	 * @param search
	 * @param page
	 * @param size
	 * @return Un mapa con la lista de clientes, la página actual y el total de páginas, además del criterio de búsqueda si se ha proporcionado.
	 */
	public Map<String, Object> getDatosDashboard(String search, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Cliente> clientePage;
	    Map<String, Object> datos = new HashMap<>();

	    if (search != null && !search.trim().isEmpty()) {
	        clientePage = buscarPorNombreClientePaginado(search.trim(), pageable);
	        datos.put("search", search.trim());
	    } else {
	        clientePage = findAllPaginado(pageable);
	    }

	    datos.put("listaClientes", clientePage.getContent());
	    datos.put("currentPage", clientePage.getNumber());
	    datos.put("totalPages", clientePage.getTotalPages());
	    return datos;
	}   
	

}

