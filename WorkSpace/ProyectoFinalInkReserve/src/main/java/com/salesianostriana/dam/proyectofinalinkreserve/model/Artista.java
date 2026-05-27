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
import jakarta.validation.constraints.*;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder @Table(name = "Artistas") 
public class Artista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Include
    @EqualsAndHashCode.Include
	private long id;
	
	@NotBlank(message = "El nombre del artista no puede estar vacío")
	private String nombreArtista;
	
	private String especialidad;
	
	@NotNull(message = "El precio por hora es obligatorio.")
    @DecimalMin(value = "10.0", message = "La tarifa mínima son 10€/hora.")
	private Double precioHora;
	
	@Column(unique = true, length=9)
	@NotBlank(message = "El DNI es obligatorio.")
    @Pattern(regexp = "^[0-9]{8}[A-Za-z]$", message = "El DNI debe constar de 8 números y una letra.")
	private String dniArtista;
	
	@NotBlank(message = "El Telefono es obligatorio.")
	@Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos numéricos.")
	private String telefonoArtista;
	
	@Column(name = "nº Higiénico Sanitario")
	private String numeroHigienico;
	
	@Min(value = 0, message = "La experiencia no puede ser negativa.")
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

