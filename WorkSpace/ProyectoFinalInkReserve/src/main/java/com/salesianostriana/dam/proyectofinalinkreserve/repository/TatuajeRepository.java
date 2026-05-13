package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;

@Repository
public interface TatuajeRepository extends JpaRepository<Tatuaje, Long> {

}
