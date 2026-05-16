package com.salesianostriana.dam.proyectofinalinkreserve.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class CitaService extends BaseServiceImpl <Cita, Long, CitaRepository> {

	private final CitaRepository citaRepository;

	public CitaService(CitaRepository repository, CitaRepository citaRepository) {
		super(repository);
		this.citaRepository = citaRepository;
	}
	
	
}
