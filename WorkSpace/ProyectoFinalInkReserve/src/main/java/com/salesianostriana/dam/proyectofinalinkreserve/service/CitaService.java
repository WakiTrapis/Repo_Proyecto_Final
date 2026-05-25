package com.salesianostriana.dam.proyectofinalinkreserve.service;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.salesianostriana.dam.proyectofinalinkreserve.model.AgendaCitas;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.base.BaseServiceImpl;

@Service
public class CitaService extends BaseServiceImpl <Cita, Long, CitaRepository> {

	private final CitaRepository citaRepository;

	public CitaService(CitaRepository repository, CitaRepository citaRepository) {
		super(repository);
		this.citaRepository = citaRepository;
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
	
}
