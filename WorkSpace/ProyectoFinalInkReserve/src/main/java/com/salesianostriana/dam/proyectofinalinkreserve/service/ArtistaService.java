package com.salesianostriana.dam.proyectofinalinkreserve.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ArtistaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class ArtistaService extends BaseServiceImpl <Artista, Long, ArtistaRepository> {

	private final ArtistaRepository artistaRepository;
	private final FotosService fotosService;

	public ArtistaService(ArtistaRepository repository, ArtistaRepository artistaRepository,FotosService fotosService) {
		super(repository);
		this.artistaRepository = artistaRepository;
		this.fotosService = fotosService;
	}
	
	public void editarArtista(Artista artistaEditado, MultipartFile archivo) {
	    Artista artistaOriginal = findById(artistaEditado.getId()).get();
	    if (archivo != null && !archivo.isEmpty()) {
	        String nombreFoto = fotosService.store(archivo);
	        artistaEditado.setFotoArtista(nombreFoto);
	    } else {
	        artistaEditado.setFotoArtista(artistaOriginal.getFotoArtista());
	    }

	    this.edit(artistaEditado);
	}
	
	public Page<Artista> findAllPaginado(Pageable pageable) {
        return artistaRepository.findAll(pageable);
    }
	
	public Page<Artista> buscarPorNombreArtistaPaginado(String criterio, Pageable pageable) {
        return artistaRepository.findByNombreArtistaContainingIgnoreCase(criterio, pageable);
    }
	
	

}
