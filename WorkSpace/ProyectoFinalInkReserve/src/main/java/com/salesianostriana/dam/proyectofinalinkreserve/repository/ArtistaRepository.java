package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

	Page<Artista>findByNombreArtistaContainingIgnoreCase(String nombreArtista,Pageable pageable);


	Optional<Artista> findByNombreArtista(String nombreArtista);
	
	boolean existsByDniArtistaAndIdNot(String dniArtista, Long id);
	
	boolean existsByTelefonoArtistaAndIdNot(String telefonoArtista, Long id);
}
