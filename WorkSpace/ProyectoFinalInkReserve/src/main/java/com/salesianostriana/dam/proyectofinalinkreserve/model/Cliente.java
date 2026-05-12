package com.salesianostriana.dam.proyectofinalinkreserve.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table(name = "Clientes")
public class Cliente {

	@Id
	@GeneratedValue
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
	
	
	
	
}
