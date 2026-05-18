package com.salesianostriana.dam.proyectofinalinkreserve.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table(name = "Clientes") 
@ToString(exclude = {"tatuajes"})
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nombreCliente;
	
	private String telefonoCliente;
	
	private LocalDate fechaNacimiento;
	
	private String direccion;
	
	private String codigoPostal;
	
	private String poblacion;
	
	@Column(unique = true, length=9)
	private String dniCliente;
	
	private String email;
	
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Tatuaje> tatuajes = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Cita> citas = new ArrayList<>();
	
	
	
}
