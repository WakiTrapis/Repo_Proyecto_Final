package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje.EstadoTatuaje;

@Repository
public interface TatuajeRepository extends JpaRepository<Tatuaje, Long> {


	Page<Tatuaje> findByNombreTatuajeContainingIgnoreCase(String nombre, Pageable pageable);

	Page<Tatuaje> findByArtistaIsNull(Pageable pageable);

	long countByEstado(EstadoTatuaje estado);

}
