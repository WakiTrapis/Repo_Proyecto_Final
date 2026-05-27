package com.salesianostriana.dam.proyectofinalinkreserve.controller;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.salesianostriana.dam.proyectofinalinkreserve.exception.DniInvalidoException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Cita;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Tatuaje;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.FotosService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.TatuajeService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArtistaController {

	
	private final ArtistaService artistaService;
	private final TatuajeService tatuajeService;
	private final CitaService citaService;
	private final FotosService fotosService; 
	
	
	@GetMapping("/Dashboard/Artistas")
	public String PintarDashboardArtistas(@RequestParam(name = "idEditar", required = false) Long idEditar,
			@RequestParam(name = "verPerfilId", required = false) Long verPerfilId,
			@RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {
		
		if (!model.containsAttribute("formularioArtista")) {
            model.addAttribute("formularioArtista", new Artista());
        }
		
		List<Artista> topArtistas = citaService.obtenerTop3ArtistasMasDemandados();
		Pageable pageable = PageRequest.of(page, size);
        Page<Artista> artistaPage;
        model.addAttribute("topArtistas", topArtistas);
		

        
        if (search != null && !search.trim().isEmpty()) {
            artistaPage = artistaService.buscarPorNombreArtistaPaginado(search.trim(), pageable);
            model.addAttribute("search", search);
        } else {
            artistaPage = artistaService.findAllPaginado(pageable);
        }
        model.addAttribute("listaArtistas", artistaPage.getContent());
        model.addAttribute("currentPage", artistaPage.getNumber());
        model.addAttribute("totalPages", artistaPage.getTotalPages());
		model.addAttribute("formularioArtista", new Artista());

		if (idEditar != null && artistaService.findById(idEditar).isPresent()) {
	        model.addAttribute("formularioArtista", artistaService.findById(idEditar).get());
	    } else {
	        model.addAttribute("formularioArtista", new Artista());
	    }
		

		if (verPerfilId != null && artistaService.findById(verPerfilId).isPresent()) {
	        Artista artista = artistaService.findById(verPerfilId).get();
	        model.addAttribute("perfilArtista", artista);
	    } else {
	        model.addAttribute("perfilArtista", null);
	    }
		return "DashboardArtistas";
		
	}
	
	@PostMapping("/nuevoArtistaCompleto")
	public String submit(@ModelAttribute("formularioArtista") Artista artista,
			@RequestParam("archivoFoto") MultipartFile archivo,
			Model model){
		try {
			if (!archivo.isEmpty()) {
	        String nombreFoto = fotosService.store(archivo);
	        artista.setFotoArtista(nombreFoto);
			}
	        artistaService.save(artista);
	        return "redirect:/Dashboard/Artistas";	
		} catch (DniInvalidoException ex) {
			model.addAttribute("errorDni", ex.getMessage());
	        model.addAttribute("formularioArtista", artista);
	        model.addAttribute("abrirModal", true);
	        
	            return "DashboardArtistas";
	        }
	}
	

	
	@PostMapping("/Dashboard/Artistas/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioArtista") Artista artista, 
			@RequestParam("archivoFoto") MultipartFile archivo, Model model ) {
		try {
		artistaService.editarArtista(artista, archivo);
		return "redirect:/Dashboard/Artistas";
		} catch (DniInvalidoException ex) {
			model.addAttribute("errorDni", ex.getMessage());
	        model.addAttribute("formularioArtista", artista);
	        model.addAttribute("abrirModal", true);
        
            return "DashboardArtistas";
		}
    }
	
	
	@GetMapping("/Dashboard/Artistas/Eliminar/{id}")
	public String submitEliminar(@PathVariable("id") Long id, Model model) {
	    Optional<Artista> artistaEncontrado = artistaService.findById(id);
	    
	    if (artistaEncontrado.isPresent()) {
	        Artista artista = artistaEncontrado.get();
	        LocalDateTime ahora = LocalDateTime.now();
	        if (artista.getTatuajes() != null && !artista.getTatuajes().isEmpty()) {
	            List<Tatuaje> listaTatuajesSegura = new ArrayList<>(artista.getTatuajes());
	            for (Tatuaje tatuaje : listaTatuajesSegura) {
	                tatuaje.setArtista(null);
	                tatuajeService.save(tatuaje);
	            }
	        }     
	        if (artista.getCitas() != null && !artista.getCitas().isEmpty()) {
	            List<Cita> listaCitasSegura = new ArrayList<>(artista.getCitas());
	            for (Cita cita : listaCitasSegura) {
	                if (cita.getFechaInicio() != null && cita.getFechaInicio().isBefore(ahora)) {
	                    cita.setArtista(null);
	                    citaService.save(cita);
	                } else {
	                    citaService.delete(cita);
	                }
	            }
	        }
	        artistaService.delete(artista);
	    }    
	    return "redirect:/Dashboard/Artistas";
	}	
}

