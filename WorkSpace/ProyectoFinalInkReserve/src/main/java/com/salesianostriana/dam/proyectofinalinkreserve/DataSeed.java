package com.salesianostriana.dam.proyectofinalinkreserve;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.EstadoTatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.TipoTintaTatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ArtistaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ClienteRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.TatuajeRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {

	private final ArtistaRepository artistaRepository;
	private final ClienteRepository clienteRepository;
	private final TatuajeRepository tatuajeRepository;
	
	
	@PostConstruct
	public void run() {
		
		Artista a1 = Artista.builder()
				.nombreArtista("Miguel Angel")
				.especialidad("Manga")
				.precioHora(25.0)
				.dniArtista("77844091Q")
				.telefonoArtista("345345678")
				.numeroHigienico("123456789")
				.experiencia(5)
				.build();
		
		artistaRepository.save(a1);
		
		Artista a2 = Artista.builder()
				.nombreArtista("David Diaz")
				.especialidad("Realismo")
				.precioHora(50.0)
				.dniArtista("12786346H")
				.telefonoArtista("494528367")
				.numeroHigienico("2670953246")
				.experiencia(10)
				.build();
		
		artistaRepository.save(a2);
		
		Artista a3 = Artista.builder()
				.nombreArtista("Paco Porras")
				.especialidad("Sombras")
				.precioHora(5.0)
				.dniArtista("48692558F")
				.telefonoArtista("298678326")
				.numeroHigienico("346845433323")
				.experiencia(2)
				.build();
		
		artistaRepository.save(a3);
		
		Cliente c1 = Cliente.builder()
				.nombreCliente("Leonardo Martinez")
				.telefonoCliente("285923785")
				.fechaNacimiento(LocalDate.of(1995, 5, 20))
				.direccion("C/Picapiedras, 5")
				.codigoPostal("41927")
				.poblacion("Mairena del Aljarafe")
				.dniCliente("38592576D")
				.email("LeoMar@gmail.com")
				.build();
		
		clienteRepository.save(c1);
		
		Cliente c2 = Cliente.builder()
				.nombreCliente("Gonzalo Luna")
				.telefonoCliente("496381495")
				.fechaNacimiento(LocalDate.of(2000, 5, 20))
				.direccion("C/Paramo, 45")
				.codigoPostal("19547")
				.poblacion("Fuengirola")
				.dniCliente("38796583G")
				.email("gonza@gmail.com")
				.build();
		
		clienteRepository.save(c2);
		
		Cliente c3 = Cliente.builder()
				.nombreCliente("Julian Vidal")
				.telefonoCliente("569781245")
				.fechaNacimiento(LocalDate.of(2001, 7, 21))
				.direccion("C/Junco, 23")
				.codigoPostal("41927")
				.poblacion("Mairena del Aljarafe")
				.dniCliente("22957465N")
				.email("julipro@gmail.com")
				.build();
		
		clienteRepository.save(c3);
		
		
		Tatuaje t1 = Tatuaje.builder()
				.cliente(c3)
				.artista(a3)
				.imagenTatuaje("tatuaje1.jpg")
				.nombreTatuaje("Dragon")
				.descripcionTatuaje("Tatuaje bombona de butano")
				.estiloTatuaje("Manga")
				.estado(EstadoTatuaje.DISENO)
				.zonaCuerpoTatuaje("brazo")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(200.0)
				.build();
		
		tatuajeRepository.save(t1);
		
		Tatuaje t2 = Tatuaje.builder()
				.cliente(c2)
				.artista(a3)
				.imagenTatuaje("tatuaje2.jpg")
				.nombreTatuaje("Break Brain")
				.descripcionTatuaje("Desata tu mente")
				.estiloTatuaje("Simplista")
				.estado(EstadoTatuaje.DISENO)
				.zonaCuerpoTatuaje("pecho")
				.sesionesTatuaje(3)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(250.0)
				.build();
		
		tatuajeRepository.save(t2);
				
	}
	
}
