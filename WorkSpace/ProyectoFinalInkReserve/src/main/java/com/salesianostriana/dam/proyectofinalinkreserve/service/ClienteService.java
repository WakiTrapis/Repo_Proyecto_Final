package com.salesianostriana.dam.proyectofinalinkreserve.service;

import java.util.Optional;

import org.springframework.stereotype.Service;


import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ClienteRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class ClienteService extends BaseServiceImpl <Cliente, Long, ClienteRepository> {

	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository repository, ClienteRepository clienteRepository) {
		super(repository);
		this.clienteRepository = clienteRepository;
	}

	public Cliente devolverArtista( Long id) {
		Optional<Cliente> cliente = findById(id);
		if (cliente.isPresent()) {
			return cliente.get();
		} else {
			return null;
		}
	}
	
	
	

}

