package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Page<Cliente>findByNombreClienteContainingIgnoreCase(String nombreCliente,Pageable pageable);
	
	boolean existsByDniClienteAndIdNot(String dniCliente, Long id);
	
	boolean existsByTelefonoClienteAndIdNot(String telefonoCliente, Long id);
	
	boolean existsByEmailAndIdNot(String email, Long id);
}
