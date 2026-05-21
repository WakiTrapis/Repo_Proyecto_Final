package com.salesianostriana.dam.proyectofinalinkreserve;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.EstadoTatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.TipoTintaTatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ArtistaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
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
	private final CitaRepository citaRepository;
	
	
	
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
				.fotoArtista("fotoArtista1.png")
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
				.fotoArtista("fotoArtista2.png")
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
				.fotoArtista("fotoArtista4.png")
				.build();
		
		artistaRepository.save(a3);
		
		Artista a4 = Artista.builder()
				.nombreArtista("Marta Sanchez")
				.especialidad("Acuarela")
				.precioHora(35.0)
				.dniArtista("25678956G")
				.telefonoArtista("495678234")
				.numeroHigienico("346845433323")
				.experiencia(8)
				.fotoArtista("fotoArtista3.png")
				.build();
		
		artistaRepository.save(a4);
		
		Artista a5 = Artista.builder()
				.nombreArtista("Francisco Sanchez")
				.especialidad("Hiperrealismo")
				.precioHora(54.0)
				.dniArtista("46213356D")
				.telefonoArtista("356834567")
				.numeroHigienico("123953923940")
				.experiencia(15)
				.fotoArtista("fotoArtista5.png")
				.build();
		
		artistaRepository.save(a5);
		
		Artista a6 = Artista.builder()
				.nombreArtista("Pajaro Azul")
				.especialidad("Hiperrealismo")
				.precioHora(54.0)
				.dniArtista("46216879D")
				.telefonoArtista("345676767")
				.numeroHigienico("123435788")
				.experiencia(10)
				.fotoArtista("fotoArtista6.png")
				.build();
		
		artistaRepository.save(a6);
		
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
		
		Cliente c4 = Cliente.builder()
				.nombreCliente("Ana Garcia")
				.telefonoCliente("569781245")
				.fechaNacimiento(LocalDate.of(2005, 9, 17))
				.direccion("C/Conde de Bustillos, 45")
				.codigoPostal("49776")
				.poblacion("Las cabezas de San Juan")
				.dniCliente("57398456M")
				.email("ana@gmail.com")
				.build();
		
		clienteRepository.save(c4);
		
		Cliente c5 = Cliente.builder()
				.nombreCliente("Sara Lopez")
				.telefonoCliente("235737567")
				.fechaNacimiento(LocalDate.of(1987, 4, 25))
				.direccion("C/Germen, 5")
				.codigoPostal("17596")
				.poblacion("Dos Hermanas")
				.dniCliente("25896547P")
				.email("sara@gmail.com")
				.build();
		
		clienteRepository.save(c5);
		
		Cliente c6 = Cliente.builder()
				.nombreCliente("Marta Blazquez")
				.telefonoCliente("665757577")
				.fechaNacimiento(LocalDate.of(1996, 7, 12))
				.direccion("C/Sor Milagros, 5")
				.codigoPostal("41001")
				.poblacion("Sevilla")
				.dniCliente("77844090S")
				.email("mastabs@gmail.com")
				.build();
		
		clienteRepository.save(c6);
		
		
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
		
		Tatuaje t3 = Tatuaje.builder()
				.cliente(c5)
				.artista(a4)
				.imagenTatuaje("tatuaje3.jpg")
				.nombreTatuaje("Iluminati")
				.descripcionTatuaje("Maliante de carton")
				.estiloTatuaje("Hiperrealismo")
				.estado(EstadoTatuaje.DISENO)
				.zonaCuerpoTatuaje("mano")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(150.0)
				.build();
		
		tatuajeRepository.save(t3);
		
		Tatuaje t4 = Tatuaje.builder()
				.cliente(c2)
				.artista(a5)
				.imagenTatuaje("tatuaje4.jpg")
				.nombreTatuaje("Brujula")
				.descripcionTatuaje("Para perder el norte")
				.estiloTatuaje("Hiperrealismo")
				.estado(EstadoTatuaje.DISENO)
				.zonaCuerpoTatuaje("pierna")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(350.0)
				.build();
		
		tatuajeRepository.save(t4);
		
		Cita ci1 = Cita.builder()
				.tatuaje(t1)
				.artista(a3)
				.cliente(c3)
				.fechaInicio(LocalDate.of(2026, 5, 19).atTime(16, 0))
				.fechaFinal(LocalDate.of(2026, 5, 19).atTime(18, 0))
				.duracion(2.0)
				.precioSesion(100.0)
				.build();
		
		citaRepository.save(ci1);
		
		Cita ci2 = Cita.builder()
				.tatuaje(t3)
				.artista(a3)
				.cliente(c5)
				.fechaInicio(LocalDate.of(2026, 5, 19).atTime(18, 0))
				.fechaFinal(LocalDate.of(2026, 5, 19).atTime(20, 0))
				.duracion(2.0)
				.precioSesion(100.0)
				.build();
		
		citaRepository.save(ci2);
		
		Cita ci3 = Cita.builder()
				.tatuaje(t4)
				.artista(a1)
				.cliente(c4)
				.fechaInicio(LocalDate.of(2026, 5, 20).atTime(18, 0))
				.fechaFinal(LocalDate.of(2026, 5, 20).atTime(20, 0))
				.duracion(2.0)
				.precioSesion(100.0)
				.build();
		
		citaRepository.save(ci3);
		
		Cita ci4 = Cita.builder()
				.tatuaje(t1)
				.artista(a1)
				.cliente(c4)
				.fechaInicio(LocalDate.of(2026, 5, 21).atTime(18, 0))
				.fechaFinal(LocalDate.of(2026, 5, 21).atTime(20, 0))
				.duracion(2.0)
				.precioSesion(100.0)
				.build();
		
		citaRepository.save(ci4);
				
	}
	
}
