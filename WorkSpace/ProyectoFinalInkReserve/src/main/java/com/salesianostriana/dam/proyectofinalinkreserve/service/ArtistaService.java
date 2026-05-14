package com.salesianostriana.dam.proyectofinalinkreserve.service;

import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;


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
	
	
	public Artista devolverArtista( Long id) {
		Optional<Artista> artista = findById(id);
		if (artista.isPresent()) {
			return artista.get();
		} else {
			return null;
		}
	}
	
	
	
	

}
