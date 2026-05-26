package com.salesianostriana.dam.proyectofinalinkreserve.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cliente;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

	
	List<Cita> findByFechaInicioBetween(LocalDateTime inicio, LocalDateTime fin);
	
	List<Cita> findByArtistaIsNull();
	
	@Query("SELECT c.cliente FROM Cita c GROUP BY c.cliente ORDER BY COUNT(c) DESC")
    List<Cliente> findClientesFrecuentes(Pageable pageable);
	
	@Query("SELECT c.artista FROM Cita c GROUP BY c.artista ORDER BY COUNT(c) DESC")
    List<Artista> findArtistasMasDemandados(Pageable pageable);
	
	@Query("""
		       SELECT COUNT(c) > 0 
		       FROM Cita c 
		       WHERE c.artista.id = :artistaId 
		         AND c.fechaInicio < :fechaFinal 
		         AND c.fechaFinal > :fechaInicio 
		         AND (:id IS NULL OR c.id != :id)
		       """)
		    boolean existeSolapamientoArtista(
		            @Param("artistaId") Long artistaId,
		            @Param("fechaInicio") LocalDateTime fechaInicio,
		            @Param("fechaFinal") LocalDateTime fechaFinal,
		            @Param("id") Long id
		    );
	
}
