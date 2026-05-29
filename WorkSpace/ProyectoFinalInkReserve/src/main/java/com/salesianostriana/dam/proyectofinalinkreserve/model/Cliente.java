package com.salesianostriana.dam.proyectofinalinkreserve.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio.")
	private String nombreCliente;
	
	@NotBlank(message = "El teléfono es obligatorio.")
	private String telefonoCliente;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDate fechaNacimiento;
	
	
	
	private String direccion;
	
	private String codigoPostal;
	
	private String poblacion;
	
	
	@Column(unique = true, length=9)
	@NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "El DNI debe constar de 8 números y una letra.")
	private String dniCliente;
	
	private String email;
	
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Tatuaje> tatuajes = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<Cita> citas = new ArrayList<>();
	
	
	
}
