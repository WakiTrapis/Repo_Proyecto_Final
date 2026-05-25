package com.salesianostriana.dam.proyectofinalinkreserve.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public Page<Cliente> findAllPaginado(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }
	
	public Page<Cliente> buscarPorNombreClientePaginado(String criterio, Pageable pageable) {
        return clienteRepository.findByNombreClienteContainingIgnoreCase(criterio, pageable);
    }
	
	
	

}

