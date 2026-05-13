package com.salesianostriana.dam.proyectofinalinkreserve.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.TatuajeRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class TatuajeService extends BaseServiceImpl <Tatuaje, Long, TatuajeRepository> {

	private final TatuajeRepository tatuajeRepository;

	public TatuajeService(TatuajeRepository repository, TatuajeRepository tatuajeRepository) {
		super(repository);
		this.tatuajeRepository = tatuajeRepository;
	}

}
