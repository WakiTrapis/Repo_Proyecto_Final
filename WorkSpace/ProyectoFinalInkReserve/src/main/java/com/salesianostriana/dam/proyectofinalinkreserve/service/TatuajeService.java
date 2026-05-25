package com.salesianostriana.dam.proyectofinalinkreserve.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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

	public Page<Tatuaje> findAllPaginado(Pageable pageable) {
	    return tatuajeRepository.findAll(pageable);
	}

	public Page<Tatuaje> buscarPorNombreTatuajePaginado(String nombre, Pageable pageable) {
	    return tatuajeRepository.findByNombreTatuajeContainingIgnoreCase(nombre, pageable);
	}

	public Page<Tatuaje> obtenerTatuajesSinArtistaPaginado(Pageable pageable) {
	    return tatuajeRepository.findByArtistaIsNull(pageable);
	}
}
