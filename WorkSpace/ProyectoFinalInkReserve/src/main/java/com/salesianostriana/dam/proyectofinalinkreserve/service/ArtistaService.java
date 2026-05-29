package com.salesianostriana.dam.proyectofinalinkreserve.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.DniInvalidoException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ArtistaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class ArtistaService extends BaseServiceImpl <Artista, Long, ArtistaRepository> {

	private final ArtistaRepository artistaRepository;
	private final FotosService fotosService;
	private final TatuajeService tatuajeService;
	private final CitaRepository citaRepository;

	
	
	public ArtistaService(ArtistaRepository repository, ArtistaRepository artistaRepository, FotosService fotosService,
			TatuajeService tatuajeService, CitaRepository citaRepository) {
		super(repository);
		this.artistaRepository = artistaRepository;
		this.fotosService = fotosService;
		this.tatuajeService = tatuajeService;
		this.citaRepository = citaRepository;
	}

	public void editarArtista(Artista artistaEditado, MultipartFile archivo) {
	    Artista artistaOriginal = findById(artistaEditado.getId()).get();
	    if (archivo != null && !archivo.isEmpty()) {
	        String nombreFoto = fotosService.store(archivo);
	        artistaEditado.setFotoArtista(nombreFoto);
	    } else {
	        artistaEditado.setFotoArtista(artistaOriginal.getFotoArtista());
	    }

	    if (artistaEditado.getDniArtista() == null || !artistaEditado.getDniArtista().matches("^[0-9]{8}[A-Za-z]$")) {
            throw new DniInvalidoException("El DNI introducido no es válido. Debe constar de 8 números y una letra (Ej: 12345678X).");
        }
	    this.edit(artistaEditado);
	}
	
	public Page<Artista> findAllPaginado(Pageable pageable) {
        return artistaRepository.findAll(pageable);
    }
	
	public Page<Artista> buscarPorNombreArtistaPaginado(String criterio, Pageable pageable) {
        return artistaRepository.findByNombreArtistaContainingIgnoreCase(criterio, pageable);
    }
	
	@Override
    public Artista save(Artista artista) {
        if (artista.getDniArtista() == null || !artista.getDniArtista().matches("^[0-9]{8}[A-Za-z]$")) {
            throw new DniInvalidoException("El DNI introducido no es válido. Debe constar de 8 números y una letra (Ej: 12345678X).");
        }
        
        return super.save(artista);
    }
	
	public void eliminarArtista(Long id) {
	    Optional<Artista> artistaEncontrado = findById(id);
	    
	    if (artistaEncontrado.isPresent()) {
	        Artista artista = artistaEncontrado.get();
	        LocalDateTime ahora = LocalDateTime.now();
	        if (artista.getTatuajes() != null && !artista.getTatuajes().isEmpty()) {
	            List<Tatuaje> listaTatuajesSegura = new ArrayList<>(artista.getTatuajes());
	            for (Tatuaje tatuaje : listaTatuajesSegura) {
	                tatuaje.setArtista(null);
	                tatuajeService.save(tatuaje);
	            }
	        }     
	        if (artista.getCitas() != null && !artista.getCitas().isEmpty()) {
	            List<Cita> listaCitasSegura = new ArrayList<>(artista.getCitas());
	            for (Cita cita : listaCitasSegura) {
	                if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                    cita.setArtista(null);
	                    citaRepository.save(cita);
	                } else {
	                    citaRepository.delete(cita);
	                }
	            }
	        }
	        delete(artista);
	    }
	}
	
	public Map<String, Object> getDatosDashboard(String search, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    Page<Artista> artistaPage;

	    if (search != null && !search.trim().isEmpty()) {
	        artistaPage = buscarPorNombreArtistaPaginado(search.trim(), pageable);
	    } else {
	        artistaPage = findAllPaginado(pageable);
	    }

	    Map<String, Object> datos = new HashMap<>();
	    datos.put("listaArtistas", artistaPage.getContent());
	    datos.put("currentPage", artistaPage.getNumber());
	    datos.put("totalPages", artistaPage.getTotalPages());
	    return datos;
	}
}

