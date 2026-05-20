package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {

	

}
