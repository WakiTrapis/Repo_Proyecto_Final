package com.salesianostriana.dam.proyectofinalinkreserve.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.DniInvalidoException;
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
	
	public void editarCliente(Cliente clienteEditado) {
	    Cliente clienteOriginal = findById(clienteEditado.getId()).get();
	    

	    if (clienteEditado.getDniCliente() == null || !clienteEditado.getDniCliente().matches("^[0-9]{8}[A-Za-z]$")) {
            throw new DniInvalidoException("El DNI introducido no es válido. Debe constar de 8 números y una letra (Ej: 12345678X).");
        }
	    this.edit(clienteEditado);
	}
	

}

