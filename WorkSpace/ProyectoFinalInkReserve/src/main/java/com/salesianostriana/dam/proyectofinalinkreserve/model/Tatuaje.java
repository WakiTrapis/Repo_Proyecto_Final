package com.salesianostriana.dam.proyectofinalinkreserve.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table(name = "Tatuajes")
public class Tatuaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String imagenTatuaje;
	
	private String nombreTatuaje;
	
	private String descripcionTatuaje;
	
	private String estiloTatuaje;
	
	private String zonaCuerpoTatuaje;
	
	private String tipoTintasTatuaje;
	
	private int sesionesTatuaje;
	
	@Enumerated(EnumType.STRING)
	private EstadoTatuaje estado;
	
	private Double precioTatuaje;
	
	
	
	
	
	
	public enum EstadoTatuaje{
		DISENO,
		EN_PROCESO,
		TATUADO
	}
	
}
