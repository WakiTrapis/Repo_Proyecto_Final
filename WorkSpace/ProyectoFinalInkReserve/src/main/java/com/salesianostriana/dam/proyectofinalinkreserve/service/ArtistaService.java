package com.salesianostriana.dam.proyectofinalinkreserve.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ArtistaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class ArtistaService extends BaseServiceImpl <Artista, Long, ArtistaRepository> {

	private final ArtistaRepository artistaRepository;

	public ArtistaService(ArtistaRepository repository, ArtistaRepository artistaRepository) {
		super(repository);
		this.artistaRepository = artistaRepository;
	}
	

}
