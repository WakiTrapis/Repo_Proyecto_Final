package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

	
}
