package com.salesianostriana.dam.proyectofinalinkreserve.service;



import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.TarifaInvalidaException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.TatuajeRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class TatuajeService extends BaseServiceImpl <Tatuaje, Long, TatuajeRepository> {

	private final TatuajeRepository tatuajeRepository;
	private final CitaRepository citaRepository;

	public TatuajeService(TatuajeRepository repository, TatuajeRepository tatuajeRepository, CitaRepository citaRepository) {
		super(repository);
		this.tatuajeRepository = tatuajeRepository;
		this.citaRepository = citaRepository;
	}
	
	public void eliminarTatuaje(Long id) {
        findById(id).ifPresent(tatuaje -> {
            LocalDateTime ahora = LocalDateTime.now();
            if (tatuaje.getCitas() != null) {
                List<Cita> citas = new ArrayList<>(tatuaje.getCitas());
                for (Cita cita : citas) {
                    if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
                        cita.setTatuaje(null);
                        citaRepository.save(cita);
                    } else {
                        citaRepository.delete(cita);
                    }
                }
            }
            delete(tatuaje);
        });
    }
	
	public void editarTatuaje(Tatuaje tatuajeEditado) {
	    if (tatuajeEditado.getPrecioTatuaje() == null) {
            throw new TarifaInvalidaException("El precio tiene que ser superior a 0.");
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
	
	public Tatuaje save(Tatuaje tatuaje) {
        if (tatuaje.getPrecioTatuaje() == null) {
            throw new TarifaInvalidaException("El precio tiene que ser superior a 0.");
        }
        
        return super.save(tatuaje);
    }
	
	public long contarPorEstado(Tatuaje.EstadoTatuaje estado) {
	    return tatuajeRepository.countByEstado(estado);
	}

	/**
	 * Obtiene los datos necesarios para mostrar en el dashboard de Tatuajes, incluyendo la lista de artistas tatuajes y el número total de páginas.
	 * @param search
	 * @param page
	 * @param size
	 * @return 
	 */
	public Map<String, Object> getDatosDashboard(String filtro, String search, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tatuaje> tatuajePage;
        Map<String, Object> datos = new HashMap<>();

        if ("sinArtista".equals(filtro)) {
            tatuajePage = obtenerTatuajesSinArtistaPaginado(pageable);
            datos.put("filtroActual", "sinArtista");
        } else if (search != null && !search.trim().isEmpty()) {
            tatuajePage = buscarPorNombreTatuajePaginado(search.trim(), pageable);
            datos.put("search", search.trim());
            datos.put("filtroActual", "search");
        } else {
            tatuajePage = findAllPaginado(pageable);
            datos.put("filtroActual", "normal");
        }

        datos.put("listaTatuajes", tatuajePage.getContent());
        datos.put("currentPage", tatuajePage.getNumber());
        datos.put("totalPages", tatuajePage.getTotalPages());
        return datos;
    }
}
