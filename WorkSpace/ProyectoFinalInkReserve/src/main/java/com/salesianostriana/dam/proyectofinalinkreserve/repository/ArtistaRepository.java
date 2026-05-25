package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

	Page<Artista>findByNombreArtistaContainingIgnoreCase(String nombreArtista,Pageable pageable);


	
	
	
}
