package com.salesianostriana.dam.proyectofinalinkreserve.service;

import java.time.LocalDate;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.proyectofinalinkreserve.model.AgendaCitas;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class CitaService extends BaseServiceImpl <Cita, Long, CitaRepository> {

	private final CitaRepository citaRepository;
	private final TatuajeService tatuajeService; 
    private final ArtistaService artistaService;

	public CitaService(CitaRepository repository, CitaRepository citaRepository, TatuajeService tatuajeService, ArtistaService artistaService) {
		super(repository);
		this.citaRepository = citaRepository;
		this.artistaService = artistaService;
		this.tatuajeService = tatuajeService;
	}
	
	
	public AgendaCitas getAgendaCitasDia(String fechaStr) {
		
		LocalDate fechaSeleccionada = (fechaStr != null && !fechaStr.isEmpty())
				? LocalDate.parse(fechaStr)
				: LocalDate.now();
		
		LocalDateTime inicioDelDia = fechaSeleccionada.atStartOfDay();
		LocalDateTime finDelDia = fechaSeleccionada.atTime(LocalTime.MAX);
		List<Cita> citasFiltradas = citaRepository.findByFechaInicioBetween(inicioDelDia, finDelDia);
		String diaAnterior = fechaSeleccionada.minusDays(1).toString();
	    String diaSiguiente = fechaSeleccionada.plusDays(1).toString();
	    return AgendaCitas.builder()
	            .listaCitas(citasFiltradas)
	            .fechaActual(fechaSeleccionada)
	            .diaAnteriorStr(diaAnterior)
	            .diaSiguienteStr(diaSiguiente)
	            .build();
	}
	
	public List<Cita> obtenerCitasSinArtista() {
	    return citaRepository.findByArtistaIsNull();
	}
	
	public List<Cliente> obtenerTop3ClientesFrecuentes() {
	    return citaRepository.findClientesFrecuentes(PageRequest.of(0, 3));
	}
	
	public List<Artista> obtenerTop3ArtistasMasDemandados() {
	    return citaRepository.findArtistasMasDemandados(PageRequest.of(0, 3));
	}
	
	public Cita guardarCitaConCalculo(Cita cita) {
	    if (cita.getTatuaje() != null && cita.getArtista() != null) {
	        double precioTatuaje = cita.getTatuaje().getPrecioTatuaje();
	        int sesiones = cita.getTatuaje().getSesionesTatuaje();
	        if (sesiones <= 0) sesiones = 1;   
	        double precioHoraArtista = cita.getArtista().getPrecioHora();
	        double horas = cita.getDuracion();
	        double precioFinal = (precioTatuaje / sesiones) + (precioHoraArtista * horas);    
	        cita.setPrecioSesion(precioFinal);
	    }
	    return citaRepository.save(cita);
	}
	
	public void calcularYAsignarPrecioCita(Cita cita) {
        if (cita.getTatuaje() != null && cita.getArtista() != null) {
            
            Optional<Tatuaje> tatuajeOpt = tatuajeService.findById(cita.getTatuaje().getId());
            Optional<Artista> artistaOpt = artistaService.findById(cita.getArtista().getId());

            if (tatuajeOpt.isPresent() && artistaOpt.isPresent()) {
                Tatuaje tatuaje = tatuajeOpt.get();
                Artista artista = artistaOpt.get();

                double precioTatuaje = (tatuaje.getPrecioTatuaje() != null) ? tatuaje.getPrecioTatuaje() : 0.0;
                int sesionesTotales = (tatuaje.getSesionesTatuaje() > 0) ? tatuaje.getSesionesTatuaje() : 1;

                double precioHoraArtista = (artista.getPrecioHora() != null) ? artista.getPrecioHora() : 0.0;

                double duracionSesion = (cita.getDuracion() != null) ? cita.getDuracion() : 0.0;

                double precioCalculado = (precioTatuaje / sesionesTotales) + (precioHoraArtista * duracionSesion);

                cita.setPrecioSesion(precioCalculado);
            }
        }
	}
}
