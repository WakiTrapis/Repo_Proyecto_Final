package com.salesianostriana.dam.proyectofinalinkreserve.model;

import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data @NoArgsConstructor @AllArgsConstructor @Entity @Builder 
@Table(name = "Tatuajes") 
public class Tatuaje {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ToString.Include       
    @EqualsAndHashCode.Include
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "artista_id")
	private Artista artista;
	
	
	private String imagenTatuaje;
	
	@NotBlank(message = "El nombre es obligatorio.")
	private String nombreTatuaje;
	
	@Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
	private String descripcionTatuaje;
	
	private String estiloTatuaje;
	
	@NotBlank(message = "La zona es obligatoria.")
	private String zonaCuerpoTatuaje;
	
	
    @Min(value = 1, message = "Como mínimo debe requerir 1 sesión.")
	private Integer sesionesTatuaje;
	
    @NotNull(message = "El precio es obligatorio.")
	private Double precioTatuaje;
	
	@Enumerated(EnumType.STRING)
	private TipoTintaTatuaje  tipoTintaTatuaje;
	
	@Enumerated(EnumType.STRING)
	private EstadoTatuaje estado;
	
	
	@OneToMany(mappedBy = "tatuaje", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Cita> citas = new ArrayList<>();
	
	
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
