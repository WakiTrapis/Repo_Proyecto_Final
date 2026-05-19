package com.salesianostriana.dam.proyectofinalinkreserve.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.TatuajeRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class TatuajeService extends BaseServiceImpl <Tatuaje, Long, TatuajeRepository> {

	private final TatuajeRepository tatuajeRepository;
	private final FotosService fotosService;

	public TatuajeService(TatuajeRepository repository, TatuajeRepository tatuajeRepository) {
		super(repository);
		this.tatuajeRepository = tatuajeRepository;
		this.fotosService = new FotosService();
	}
	
	public void editarTatuaje(Tatuaje tatuajeEditado, MultipartFile archivo) {
	    Tatuaje tatuajeOriginal = findById(tatuajeEditado.getId()).get();
	    if (archivo != null && !archivo.isEmpty()) {
	        String nombreFoto = fotosService.store(archivo);
	        tatuajeEditado.setImagenTatuaje(nombreFoto);
	    } else {
	        tatuajeEditado.setImagenTatuaje(tatuajeOriginal.getImagenTatuaje());
	    }

	    this.edit(tatuajeEditado);
	}

}
