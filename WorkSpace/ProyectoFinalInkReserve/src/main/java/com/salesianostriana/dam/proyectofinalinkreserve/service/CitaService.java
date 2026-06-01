package com.salesianostriana.dam.proyectofinalinkreserve.service;

import java.time.LocalDate;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.proyectofinalinkreserve.exception.CitaSolapadaException;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.TarifaInvalidaException;
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
	
	/**
	 * Método para obtener los datos necesarios para el dashboard, dependiendo del filtro (fecha o sin artista).
	 * @param fechaStr
	 * @param filtro
	 * @return Un mapa con los datos necesarios para mostrar en el dashboard, incluyendo la lista de citas filtrada y las fechas para navegación si se filtra por fecha.
	 */
	public Map<String, Object> getDatosDashboard(String fechaStr, String filtro) {
	    AgendaCitas agenda = getAgendaCitasDia(fechaStr);
	    Map<String, Object> datos = new HashMap<>();

	    if ("sinArtista".equals(filtro)) {
	        datos.put("listaCitas", obtenerCitasSinArtista());
	        datos.put("filtroActual", "sinArtista");
	    } else {
	        datos.put("listaCitas", agenda.getListaCitas());
	        datos.put("fechaActual", agenda.getFechaActual());
	        datos.put("diaAnteriorStr", agenda.getDiaAnteriorStr());
	        datos.put("diaSiguienteStr", agenda.getDiaSiguienteStr());
	        datos.put("filtroActual", "fecha");
	    }
	    return datos;
	}
	
	/*
	 * Método para obtener las citas de un día específico, junto con las fechas del día anterior y siguiente para facilitar la navegación en el dashboard.
	 */
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
	
	//Consultas personalizadas
	public List<Cita> obtenerCitasSinArtista() {
	    return citaRepository.findByArtistaIsNull();
	}
	
	public List<Cliente> obtenerTop3ClientesFrecuentes() {
	    return citaRepository.findClientesFrecuentes(PageRequest.of(0, 3));
	}
	
	public List<Artista> obtenerTop3ArtistasMasDemandados() {
	    return citaRepository.findArtistasMasDemandados(PageRequest.of(0, 3));
	}

	public List<Cita> obtenerProximasCitas() {
	    return citaRepository.findByFechaInicioAfterOrderByFechaInicioAsc(LocalDateTime.now());
	}

	public List<Cita> obtenerTop5ProximasCitas() {
	    return citaRepository.findByFechaInicioAfterOrderByFechaInicioAsc(LocalDateTime.now())
	            .stream().limit(5).toList();
	}

	public List<Cita> obtenerCitasDelDiaMasProximo() {
	    List<Cita> proximas = citaRepository.findByFechaInicioAfterOrderByFechaInicioAsc(LocalDateTime.now());
	    if (proximas.isEmpty()) return proximas;
	    LocalDate diaMasProximo = proximas.get(0).getFechaInicio().toLocalDate();
	    return proximas.stream()
	            .filter(c -> c.getFechaInicio().toLocalDate().equals(diaMasProximo))
	            .toList();
	}
	
	
	
	public void calcularYAsignarPrecioCita(Cita cita) {
        if (cita.getTatuaje() != null && cita.getArtista() != null) {
            
            Optional<Tatuaje> tatuajeOpt = tatuajeService.findById(cita.getTatuaje().getId());
            Optional<Artista> artistaOpt = artistaService.findById(cita.getArtista().getId());
            double precioTatuaje;
            int sesionesTotales;
            double precioHoraArtista;
            double duracionSesion;
            double precioCalculado;
            
            if (tatuajeOpt.isPresent() && artistaOpt.isPresent()) {
                Tatuaje tatuaje = tatuajeOpt.get();
                Artista artista = artistaOpt.get();

                if (artista.getPrecioHora() == null || artista.getPrecioHora() <= 0) {
                    throw new TarifaInvalidaException("El artista seleccionado no dispone de una tarifa por hora válida (mayor que 0€).");
                }
                if (cita.getDuracion() == null || cita.getDuracion() <= 0) {
                    throw new TarifaInvalidaException("La duración de la sesión debe ser superior a 0 horas.");
                }
                
                precioTatuaje = tatuaje.getPrecioTatuaje();
                sesionesTotales = tatuaje.getSesionesTatuaje();
                precioHoraArtista = artista.getPrecioHora();
                duracionSesion = cita.getDuracion();

                precioCalculado = (precioTatuaje / sesionesTotales) + (precioHoraArtista * duracionSesion);

                if (precioCalculado <= 0) {
                    throw new TarifaInvalidaException("Error en el cálculo: El precio de la sesión no puede ser igual o inferior a 0€.");
                }
                cita.setPrecioSesion(precioCalculado);
            }
        }
	}
	
	@Override
	public Cita save(Cita cita) {
		validarDisponibilidadArtista(cita);
		calcularYAsignarPrecioCita(cita);
		return super.save(cita);
	}
	
	@Override
	public Cita edit(Cita cita) {
		validarDisponibilidadArtista(cita);
		if (cita.getPrecioSesion() == null || cita.getPrecioSesion() <= 0) {
			calcularYAsignarPrecioCita(cita);
		}
		return super.save(cita);
	}
	
	private void validarDisponibilidadArtista(Cita cita) {
		if (cita.getArtista() != null && cita.getFechaInicio() != null && cita.getFechaFinal() != null) {
			boolean estaOcupado = citaRepository.existeSolapamientoArtista(
				cita.getArtista().getId(),
				cita.getFechaInicio(),
				cita.getFechaFinal(),
				cita.getId()
			);

			if (estaOcupado) {
				throw new CitaSolapadaException("El artista seleccionado ya tiene una cita programada en ese rango horario.");
			}
		}
	}
	
	/**
	 * Método para obtener las citas de un artista para el calendario de la ficha.
	 * @param Id
	 * @return Listado de citas formateado para mostrar en el calendario, incluyendo título, cliente y fechas de inicio y fin.
	 */
	public List<Map<String, Object>> getCitasParaCalendario(Long Id) {
	    List<Cita> citas = citaRepository.findByArtistaId(Id);
	    
	    return citas.stream().map(cita -> {
	        Map<String, Object> evento = new HashMap<>();
	        evento.put("id", cita.getId());
	        evento.put("title", cita.getTatuaje() != null ? 
	                   cita.getTatuaje().getNombreTatuaje() : "Cita");
	        evento.put("cliente", cita.getCliente() != null ? 
	                   cita.getCliente().getNombreCliente() : "Sin cliente");
	        evento.put("inicio", cita.getFechaInicio().toString());
	        evento.put("fin", cita.getFechaFinal().toString());
	        return evento;
	    }).toList();
	}
}
