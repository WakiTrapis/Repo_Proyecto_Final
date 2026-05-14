package com.salesianostriana.dam.proyectofinalinkreserve.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "artista_id")
	private Artista artista;
	
	private String imagenTatuaje;
	private String nombreTatuaje;
	private String descripcionTatuaje;
	private String estiloTatuaje;
	private String zonaCuerpoTatuaje;
	private int sesionesTatuaje;
	
	@Enumerated(EnumType.STRING)
	private TipoTintaTatuaje  tipoTintaTatuaje;
	
	@Enumerated(EnumType.STRING)
	private EstadoTatuaje estado;
	
	private Double precioTatuaje;
	
	
	public enum EstadoTatuaje{
		DISENO,
		EN_PROCESO,
		TATUADO
	}
	
	public enum TipoTintaTatuaje{
		COLOR,
		BLACK
	}
	
	
}
