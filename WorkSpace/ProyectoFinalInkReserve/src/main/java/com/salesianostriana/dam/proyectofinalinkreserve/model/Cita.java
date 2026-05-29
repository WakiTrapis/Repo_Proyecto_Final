package com.salesianostriana.dam.proyectofinalinkreserve.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder 
@Table(name = "Citas")
public class Cita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "tatuaje_id")
	private Tatuaje tatuaje;
	
	
	@ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
	
	@ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;
	
	@NotNull
	@Column(name = "fecha_inicio")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaInicio;
	
	@NotNull
	@Column(name = "fecha_final")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaFinal;
	
	@NotNull
	private Double duracion;
	
	@Column(name = "precio_sesion")
    private Double precioSesion;
	
	

}
