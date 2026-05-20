package com.salesianostriana.dam.proyectofinalinkreserve.model;


import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table(name = "Artistas") @ToString(exclude = {"cliente", "artista"})
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Include
    @EqualsAndHashCode.Include
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
	@Fetch(FetchMode.SUBSELECT)
    @Builder.Default
    private List<Tatuaje> tatuajes = new ArrayList<>();
	
	@OneToMany(mappedBy = "artista")
	@Fetch(FetchMode.SUBSELECT)
	@Builder.Default
	private List<Cita> citas = new ArrayList<>();
	
	


	
}

