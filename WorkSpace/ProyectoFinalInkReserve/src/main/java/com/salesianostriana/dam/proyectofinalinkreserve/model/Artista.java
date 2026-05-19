package com.salesianostriana.dam.proyectofinalinkreserve.model;


import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table(name = "Artistas")
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	private String nombreArtista;
	
	private String especialidad;
	

	private Double precioHora;
	
	@Column(unique = true, length=9)
	private String dniArtista;
	
	private String telefonoArtista;
	
	@Column(name = "nº Higiénico Sanitario")
	private String numeroHigienico;
	
	private Integer experiencia;
	
	private String fotoArtista;
	
	@OneToMany(mappedBy = "artista")
    @Builder.Default
    private List<Tatuaje> tatuajes = new ArrayList<>();
	
	@OneToMany(mappedBy = "artista")
	@Builder.Default
	private List<Cita> citas = new ArrayList<>();
	
	


	
}

