package com.salesianostriana.dam.proyectofinalinkreserve.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity
public class Artista {

	@Id
	@GeneratedValue
	private long id;
	
	private String nombre;
	private String especialidad;
	private double precioHora;
	private String dni;
	private String telefono;
	private String numeroHigienico;
	private int experiencia;
	
}

