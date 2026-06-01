package com.salesianostriana.dam.proyectofinalinkreserve;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.EstadoTatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.TipoTintaTatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.CitaRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.ClienteRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.repository.TatuajeRepository;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ClienteService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataSeed {

	private final ArtistaService artistaService;
	private final ClienteRepository clienteRepository;
	private final TatuajeRepository tatuajeRepository;
	private final CitaRepository citaRepository;
	private final ClienteService clienteService;

	@PostConstruct
	public void run() {

		//ARTISTAS 

		Artista a1 = Artista.builder()
				.nombreArtista("Miguel Angel")
				.especialidad("Manga")
				.precioHora(25.0)
				.dniArtista("77844091Q")
				.telefonoArtista("345345678")
				.numeroHigienico("123456789")
				.experiencia(5)
				.fotoArtista("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScskPIMZJMMekOCpeHWnVtlhll8Fi73yCoYA&s")
				.build();
		artistaService.saveArmor(a1, 1L);

		Artista a2 = Artista.builder()
				.nombreArtista("David Diaz")
				.especialidad("Realismo")
				.precioHora(50.0)
				.dniArtista("12786346H")
				.telefonoArtista("494528367")
				.numeroHigienico("2670953246")
				.experiencia(10)
				.fotoArtista("https://images.ctfassets.net/h6goo9gw1hh6/2sNZtFAWOdP1lmQ33VwRN3/e40b6ea6361a1abe28f32e7910f63b66/1-intro-photo-final.jpg?w=1200&h=992&fl=progressive&q=70&fm=jpg")
				.build();
		artistaService.saveArmor(a2, 2L);

		Artista a3 = Artista.builder()
				.nombreArtista("Paco Porras")
				.especialidad("Sombras")
				.precioHora(10.0)
				.dniArtista("48692558F")
				.telefonoArtista("298678326")
				.numeroHigienico("346845433323")
				.experiencia(2)
				.fotoArtista("https://plus.unsplash.com/premium_photo-1689568126014-06fea9d5d341?fm=jpg&q=60&w=3000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MXx8cGVyZmlsfGVufDB8fDB8fHww")
				.build();
		artistaService.saveArmor(a3, 3L);

		Artista a4 = Artista.builder()
				.nombreArtista("Marta Sanchez")
				.especialidad("Acuarela")
				.precioHora(35.0)
				.dniArtista("25678956G")
				.telefonoArtista("495678234")
				.numeroHigienico("346845433324")
				.experiencia(8)
				.fotoArtista("https://images.squarespace-cdn.com/content/v1/5d77a7f8ad30356d21445262/f25b9827-c1e5-43b5-9cdb-a718fb90f38b/fotos-de-perfil-luz-natural.jpg")
				.build();
		artistaService.saveArmor(a4, 4L);

		Artista a5 = Artista.builder()
				.nombreArtista("Francisco Sanchez")
				.especialidad("Hiperrealismo")
				.precioHora(54.0)
				.dniArtista("46213356D")
				.telefonoArtista("356834567")
				.numeroHigienico("123953923940")
				.experiencia(15)
				.fotoArtista("https://images.unsplash.com/photo-1654110455429-cf322b40a906?fm=jpg&q=60&w=3000&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8cGVyZmlsJTIwcHJlZGV0ZXJtaW5hZG98ZW58MHx8MHx8fDA%3D")
				.build();
		artistaService.saveArmor(a5, 5L);

		Artista a6 = Artista.builder()
				.nombreArtista("Pajaro Azul")
				.especialidad("Hiperrealismo")
				.precioHora(54.0)
				.dniArtista("46216879D")
				.telefonoArtista("345676767")
				.numeroHigienico("123435788")
				.experiencia(10)
				.fotoArtista("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSv_a9I-AkqF9EXOPbRQ5LLQT8jYvQClcycWQ&s")
				.build();
		artistaService.saveArmor(a6, 6L);

		Artista a7 = Artista.builder()
				.nombreArtista("Laura Vega")
				.especialidad("Blackwork")
				.precioHora(40.0)
				.dniArtista("34567890K")
				.telefonoArtista("611223344")
				.numeroHigienico("987654321")
				.experiencia(6)
				.fotoArtista("https://randomuser.me/api/portraits/women/44.jpg")
				.build();
		artistaService.saveArmor(a7, 7L);

		Artista a8 = Artista.builder()
				.nombreArtista("Carlos Ruiz")
				.especialidad("Tribal")
				.precioHora(30.0)
				.dniArtista("23456781L")
				.telefonoArtista("622334455")
				.numeroHigienico("876543210")
				.experiencia(4)
				.fotoArtista("https://randomuser.me/api/portraits/men/32.jpg")
				.build();
		artistaService.saveArmor(a8, 8L);

		Artista a9 = Artista.builder()
				.nombreArtista("Sofia Torres")
				.especialidad("Neotradicional")
				.precioHora(45.0)
				.dniArtista("12345679M")
				.telefonoArtista("633445566")
				.numeroHigienico("765432109")
				.experiencia(7)
				.fotoArtista("https://randomuser.me/api/portraits/women/68.jpg")
				.build();
		artistaService.saveArmor(a9, 9L);

		Artista a10 = Artista.builder()
				.nombreArtista("Jorge Molina")
				.especialidad("Geométrico")
				.precioHora(38.0)
				.dniArtista("98765432N")
				.telefonoArtista("644556677")
				.numeroHigienico("654321098")
				.experiencia(9)
				.fotoArtista("https://randomuser.me/api/portraits/men/76.jpg")
				.build();
		artistaService.saveArmor(a10, 10L);

		//CLIENTES 

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
		clienteService.saveArmor(c1, 1L);

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
		clienteService.saveArmor(c2, 2L);

		Cliente c3 = Cliente.builder()
				.nombreCliente("Julian Vidal")
				.telefonoCliente("569786790")
				.fechaNacimiento(LocalDate.of(2001, 7, 21))
				.direccion("C/Junco, 23")
				.codigoPostal("41927")
				.poblacion("Mairena del Aljarafe")
				.dniCliente("22957465N")
				.email("julipro@gmail.com")
				.build();
		clienteService.saveArmor(c3, 3L);

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
		clienteService.saveArmor(c4, 4L);

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
		clienteService.saveArmor(c5, 5L);

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
		clienteService.saveArmor(c6, 6L);

		Cliente c7 = Cliente.builder()
				.nombreCliente("Pedro Jimenez")
				.telefonoCliente("677889900")
				.fechaNacimiento(LocalDate.of(1993, 3, 15))
				.direccion("C/Olivos, 12")
				.codigoPostal("41003")
				.poblacion("Sevilla")
				.dniCliente("11223344A")
				.email("pedro@gmail.com")
				.build();
		clienteService.saveArmor(c7, 7L);

		Cliente c8 = Cliente.builder()
				.nombreCliente("Lucia Fernandez")
				.telefonoCliente("688990011")
				.fechaNacimiento(LocalDate.of(1999, 11, 3))
				.direccion("Av/Palmeras, 8")
				.codigoPostal("41004")
				.poblacion("Sevilla")
				.dniCliente("22334455B")
				.email("lucia@gmail.com")
				.build();
		clienteService.saveArmor(c8, 8L);

		Cliente c9 = Cliente.builder()
				.nombreCliente("Raul Moreno")
				.telefonoCliente("699001122")
				.fechaNacimiento(LocalDate.of(1990, 6, 28))
				.direccion("C/Naranjos, 3")
				.codigoPostal("41010")
				.poblacion("Sevilla")
				.dniCliente("33445566C")
				.email("raul@gmail.com")
				.build();
		clienteService.saveArmor(c9, 9L);

		Cliente c10 = Cliente.builder()
				.nombreCliente("Elena Castro")
				.telefonoCliente("600112233")
				.fechaNacimiento(LocalDate.of(2002, 1, 9))
				.direccion("C/Lirios, 7")
				.codigoPostal("41012")
				.poblacion("Sevilla")
				.dniCliente("44556677E")
				.email("elena@gmail.com")
				.build();
		clienteService.saveArmor(c10, 10L);

		//TATUAJES

		Tatuaje t1 = Tatuaje.builder()
				.cliente(c3).artista(a3)
				.imagenTatuaje("https://i.pinimg.com/736x/bc/e9/5b/bce95bf5636b6f1fe6a71cb57bbc22fb.jpg")
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
				.cliente(c2).artista(a3)
				.imagenTatuaje("https://www.avantgardetattoo.es/wp-content/uploads/2020/03/1-scaled.jpg")
				.nombreTatuaje("Break Brain")
				.descripcionTatuaje("Desata tu mente")
				.estiloTatuaje("Simplista")
				.estado(EstadoTatuaje.EN_PROCESO)
				.zonaCuerpoTatuaje("pecho")
				.sesionesTatuaje(3)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(250.0)
				.build();
		tatuajeRepository.save(t2);

		Tatuaje t3 = Tatuaje.builder()
				.cliente(c5).artista(a4)
				.imagenTatuaje("https://circetattoo.com/wp-content/uploads/2023/06/IMG_20230422_205428_087-930x620.jpg")
				.nombreTatuaje("Iluminati")
				.descripcionTatuaje("Maliante de carton")
				.estiloTatuaje("Hiperrealismo")
				.estado(EstadoTatuaje.TATUADO)
				.zonaCuerpoTatuaje("mano")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(150.0)
				.build();
		tatuajeRepository.save(t3);

		Tatuaje t4 = Tatuaje.builder()
				.cliente(c2).artista(a5)
				.imagenTatuaje("https://static.tatship.com/idea-page-posts/300eb37f-1efd-4f28-97bf-01cdf6f03419.jpg")
				.nombreTatuaje("Brujula")
				.descripcionTatuaje("Para perder el norte")
				.estiloTatuaje("Hiperrealismo")
				.estado(EstadoTatuaje.EN_PROCESO)
				.zonaCuerpoTatuaje("pierna")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(350.0)
				.build();
		tatuajeRepository.save(t4);

		Tatuaje t5 = Tatuaje.builder()
				.cliente(c4).artista(a1)
				.imagenTatuaje("https://moamadridtattoo.com/images/9651-4-tatuaje-rosa-antebrazo.jpg")
				.nombreTatuaje("Flor")
				.descripcionTatuaje("Huele flamaaa")
				.estiloTatuaje("Retro")
				.estado(EstadoTatuaje.TATUADO)
				.zonaCuerpoTatuaje("brazo")
				.sesionesTatuaje(1)
				.tipoTintaTatuaje(TipoTintaTatuaje.COLOR)
				.precioTatuaje(138.0)
				.build();
		tatuajeRepository.save(t5);

		Tatuaje t6 = Tatuaje.builder()
				.cliente(c6).artista(a2)
				.imagenTatuaje("https://lamanozurda.es/wp-content/uploads/2025/04/1-4.jpg")
				.nombreTatuaje("Cuello vuelto")
				.descripcionTatuaje("Para los frioleros")
				.estiloTatuaje("Retro")
				.estado(EstadoTatuaje.DISENO)
				.zonaCuerpoTatuaje("cuello")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(500.0)
				.build();
		tatuajeRepository.save(t6);

		Tatuaje t7 = Tatuaje.builder()
				.cliente(c4).artista(a1)
				.imagenTatuaje("https://pacocachadas.com/wp-content/uploads/tatuaje-geometrico-murcia.jpg")
				.nombreTatuaje("Mandala Lobo")
				.descripcionTatuaje("Para los amantes de los lobos")
				.estiloTatuaje("Hiperrealismo")
				.estado(EstadoTatuaje.EN_PROCESO)
				.zonaCuerpoTatuaje("brazo")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(240.0)
				.build();
		tatuajeRepository.save(t7);

		Tatuaje t8 = Tatuaje.builder()
				.cliente(c7).artista(a7)
				.imagenTatuaje("https://baltasartattoo.es/wp-content/uploads/2019/09/tatuaje-ave-fenix-mujer-madriz.jpg")
				.nombreTatuaje("Fenix")
				.descripcionTatuaje("Renace de las cenizas")
				.estiloTatuaje("Blackwork")
				.estado(EstadoTatuaje.DISENO)
				.zonaCuerpoTatuaje("espalda")
				.sesionesTatuaje(4)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(600.0)
				.build();
		tatuajeRepository.save(t8);

		Tatuaje t9 = Tatuaje.builder()
				.cliente(c8).artista(a8)
				.imagenTatuaje("https://i.pinimg.com/originals/b8/21/b4/b821b4ffbd41d1e3162aeb109fc65f1e.jpg")
				.nombreTatuaje("Polinesio")
				.descripcionTatuaje("Tribal del pacifico")
				.estiloTatuaje("Tribal")
				.estado(EstadoTatuaje.TATUADO)
				.zonaCuerpoTatuaje("hombro")
				.sesionesTatuaje(3)
				.tipoTintaTatuaje(TipoTintaTatuaje.BLACK)
				.precioTatuaje(320.0)
				.build();
		tatuajeRepository.save(t9);

		Tatuaje t10 = Tatuaje.builder()
				.cliente(c9).artista(a9)
				.imagenTatuaje("https://i.pinimg.com/originals/c2/11/e5/c211e509b095d0018a8c5396a7bcf223.jpg")
				.nombreTatuaje("Rosa Neotradicional")
				.descripcionTatuaje("Rosa con espinas y color")
				.estiloTatuaje("Neotradicional")
				.estado(EstadoTatuaje.EN_PROCESO)
				.zonaCuerpoTatuaje("antebrazo")
				.sesionesTatuaje(2)
				.tipoTintaTatuaje(TipoTintaTatuaje.COLOR)
				.precioTatuaje(280.0)
				.build();
		tatuajeRepository.save(t10);

		// ==================== CITAS ====================

		Cita ci1 = Cita.builder()
				.tatuaje(t1).artista(a4).cliente(c3)
				.fechaInicio(LocalDate.of(2026, 6, 2).atTime(10, 0))
				.fechaFinal(LocalDate.of(2026, 6, 2).atTime(12, 0))
				.duracion(2.0).precioSesion(100.0)
				.build();
		citaRepository.save(ci1);

		Cita ci2 = Cita.builder()
				.tatuaje(t3).artista(a3).cliente(c5)
				.fechaInicio(LocalDate.of(2026, 6, 3).atTime(11, 0))
				.fechaFinal(LocalDate.of(2026, 6, 3).atTime(13, 0))
				.duracion(2.0).precioSesion(100.0)
				.build();
		citaRepository.save(ci2);

		Cita ci3 = Cita.builder()
				.tatuaje(t4).artista(a1).cliente(c4)
				.fechaInicio(LocalDate.of(2026, 6, 4).atTime(16, 0))
				.fechaFinal(LocalDate.of(2026, 6, 4).atTime(18, 0))
				.duracion(2.0).precioSesion(100.0)
				.build();
		citaRepository.save(ci3);

		Cita ci4 = Cita.builder()
				.tatuaje(t6).artista(a2).cliente(c4)
				.fechaInicio(LocalDate.of(2026, 6, 5).atTime(9, 0))
				.fechaFinal(LocalDate.of(2026, 6, 5).atTime(11, 0))
				.duracion(2.0).precioSesion(150.0)
				.build();
		citaRepository.save(ci4);

		Cita ci5 = Cita.builder()
				.tatuaje(t7).artista(a1).cliente(c4)
				.fechaInicio(LocalDate.of(2026, 6, 6).atTime(12, 0))
				.fechaFinal(LocalDate.of(2026, 6, 6).atTime(14, 0))
				.duracion(2.0).precioSesion(200.0)
				.build();
		citaRepository.save(ci5);

		Cita ci6 = Cita.builder()
				.tatuaje(t2).artista(a5).cliente(c2)
				.fechaInicio(LocalDate.of(2026, 6, 7).atTime(10, 0))
				.fechaFinal(LocalDate.of(2026, 6, 7).atTime(12, 30))
				.duracion(2.5).precioSesion(180.0)
				.build();
		citaRepository.save(ci6);

		Cita ci7 = Cita.builder()
				.tatuaje(t8).artista(a7).cliente(c7)
				.fechaInicio(LocalDate.of(2026, 6, 9).atTime(11, 0))
				.fechaFinal(LocalDate.of(2026, 6, 9).atTime(14, 0))
				.duracion(3.0).precioSesion(220.0)
				.build();
		citaRepository.save(ci7);

		Cita ci8 = Cita.builder()
				.tatuaje(t9).artista(a8).cliente(c8)
				.fechaInicio(LocalDate.of(2026, 6, 10).atTime(15, 0))
				.fechaFinal(LocalDate.of(2026, 6, 10).atTime(17, 0))
				.duracion(2.0).precioSesion(130.0)
				.build();
		citaRepository.save(ci8);

		Cita ci9 = Cita.builder()
				.tatuaje(t10).artista(a9).cliente(c9)
				.fechaInicio(LocalDate.of(2026, 6, 12).atTime(10, 0))
				.fechaFinal(LocalDate.of(2026, 6, 12).atTime(12, 0))
				.duracion(2.0).precioSesion(160.0)
				.build();
		citaRepository.save(ci9);

		Cita ci10 = Cita.builder()
				.tatuaje(t5).artista(a10).cliente(c10)
				.fechaInicio(LocalDate.of(2026, 6, 14).atTime(17, 0))
				.fechaFinal(LocalDate.of(2026, 6, 14).atTime(19, 0))
				.duracion(2.0).precioSesion(140.0)
				.build();
		citaRepository.save(ci10);

		Cita ci11 = Cita.builder()
				.tatuaje(t6).artista(a6).cliente(c1)
				.fechaInicio(LocalDate.of(2026, 6, 16).atTime(10, 0))
				.fechaFinal(LocalDate.of(2026, 6, 16).atTime(12, 0))
				.duracion(2.0).precioSesion(170.0)
				.build();
		citaRepository.save(ci11);

		Cita ci12 = Cita.builder()
				.tatuaje(t2).artista(a3).cliente(c2)
				.fechaInicio(LocalDate.of(2026, 6, 18).atTime(11, 0))
				.fechaFinal(LocalDate.of(2026, 6, 18).atTime(13, 0))
				.duracion(2.0).precioSesion(190.0)
				.build();
		citaRepository.save(ci12);

		Cita ci13 = Cita.builder()
				.tatuaje(t8).artista(a7).cliente(c7)
				.fechaInicio(LocalDate.of(2026, 6, 20).atTime(9, 0))
				.fechaFinal(LocalDate.of(2026, 6, 20).atTime(12, 0))
				.duracion(3.0).precioSesion(250.0)
				.build();
		citaRepository.save(ci13);

		Cita ci14 = Cita.builder()
				.tatuaje(t10).artista(a9).cliente(c9)
				.fechaInicio(LocalDate.of(2026, 6, 23).atTime(16, 0))
				.fechaFinal(LocalDate.of(2026, 6, 23).atTime(18, 0))
				.duracion(2.0).precioSesion(160.0)
				.build();
		citaRepository.save(ci14);

		Cita ci15 = Cita.builder()
				.tatuaje(t4).artista(a5).cliente(c6)
				.fechaInicio(LocalDate.of(2026, 6, 25).atTime(15, 0))
				.fechaFinal(LocalDate.of(2026, 6, 25).atTime(17, 30))
				.duracion(2.5).precioSesion(210.0)
				.build();
		citaRepository.save(ci15);

		Cita ci16 = Cita.builder()
				.tatuaje(t2).artista(a3).cliente(c2)
				.fechaInicio(LocalDate.of(2026, 6, 2).atTime(9, 0))
				.fechaFinal(LocalDate.of(2026, 6, 2).atTime(11, 0))
				.duracion(2.0).precioSesion(120.0)
				.build();
		citaRepository.save(ci16);

		Cita ci17 = Cita.builder()
				.tatuaje(t5).artista(a1).cliente(c1)
				.fechaInicio(LocalDate.of(2026, 6, 2).atTime(11, 0))
				.fechaFinal(LocalDate.of(2026, 6, 2).atTime(13, 0))
				.duracion(2.0).precioSesion(90.0)
				.build();
		citaRepository.save(ci17);

		Cita ci18 = Cita.builder()
				.tatuaje(t6).artista(a2).cliente(c6)
				.fechaInicio(LocalDate.of(2026, 6, 2).atTime(10, 0))
				.fechaFinal(LocalDate.of(2026, 6, 2).atTime(12, 0))
				.duracion(2.0).precioSesion(150.0)
				.build();
		citaRepository.save(ci18);

		Cita ci19 = Cita.builder()
				.tatuaje(t8).artista(a7).cliente(c7)
				.fechaInicio(LocalDate.of(2026, 6, 2).atTime(15, 0))
				.fechaFinal(LocalDate.of(2026, 6, 2).atTime(17, 0))
				.duracion(2.0).precioSesion(200.0)
				.build();
		citaRepository.save(ci19);

		Cita ci20 = Cita.builder()
				.tatuaje(t10).artista(a9).cliente(c9)
				.fechaInicio(LocalDate.of(2026, 6, 2).atTime(16, 0))
				.fechaFinal(LocalDate.of(2026, 6, 2).atTime(18, 0))
				.duracion(2.0).precioSesion(175.0)
				.build();
		citaRepository.save(ci20);

		// -- Día 07/06 (mismo día que ci6, artistas distintos) --
		Cita ci21 = Cita.builder()
				.tatuaje(t1).artista(a4).cliente(c3)
				.fechaInicio(LocalDate.of(2026, 6, 7).atTime(9, 0))
				.fechaFinal(LocalDate.of(2026, 6, 7).atTime(11, 0))
				.duracion(2.0).precioSesion(110.0)
				.build();
		citaRepository.save(ci21);

		Cita ci22 = Cita.builder()
				.tatuaje(t3).artista(a1).cliente(c5)
				.fechaInicio(LocalDate.of(2026, 6, 7).atTime(13, 0))
				.fechaFinal(LocalDate.of(2026, 6, 7).atTime(15, 0))
				.duracion(2.0).precioSesion(130.0)
				.build();
		citaRepository.save(ci22);

		Cita ci23 = Cita.builder()
				.tatuaje(t9).artista(a8).cliente(c8)
				.fechaInicio(LocalDate.of(2026, 6, 7).atTime(11, 0))
				.fechaFinal(LocalDate.of(2026, 6, 7).atTime(13, 0))
				.duracion(2.0).precioSesion(145.0)
				.build();
		citaRepository.save(ci23);

		// -- Día 09/06 (mismo día que ci7, artistas distintos) --
		Cita ci24 = Cita.builder()
				.tatuaje(t5).artista(a1).cliente(c4)
				.fechaInicio(LocalDate.of(2026, 6, 9).atTime(9, 0))
				.fechaFinal(LocalDate.of(2026, 6, 9).atTime(11, 0))
				.duracion(2.0).precioSesion(95.0)
				.build();
		citaRepository.save(ci24);

		Cita ci25 = Cita.builder()
				.tatuaje(t6).artista(a2).cliente(c1)
				.fechaInicio(LocalDate.of(2026, 6, 9).atTime(10, 0))
				.fechaFinal(LocalDate.of(2026, 6, 9).atTime(12, 0))
				.duracion(2.0).precioSesion(165.0)
				.build();
		citaRepository.save(ci25);
	}
}
