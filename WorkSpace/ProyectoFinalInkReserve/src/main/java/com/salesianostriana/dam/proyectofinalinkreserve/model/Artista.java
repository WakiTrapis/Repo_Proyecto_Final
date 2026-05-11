package com.salesianostriana.dam.proyectofinalinkreserve.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table(name = "Artistas")
public class Artista {

	@Id
	@GeneratedValue
	private long id;
	
	
	private String nombreArtista;
	
	private String especialidad;
	

	private Double precioHora;
	
	@Column(unique = true)
	private String dni;
	
	private String telefono;
	
	@Column(name = "num_higienico_sanitario")
	private String numeroHigienico;
	
	private Integer experiencia;
	
	private String foto;
	


	
}

