package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

	Page<Artista>findByNombreArtistaContainingIgnoreCase(String nombreArtista,Pageable pageable);

}
