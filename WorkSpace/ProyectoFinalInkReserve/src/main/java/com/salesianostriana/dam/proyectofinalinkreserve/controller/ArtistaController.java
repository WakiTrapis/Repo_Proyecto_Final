package com.salesianostriana.dam.proyectofinalinkreserve.controller;


import java.util.List;


import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.salesianostriana.dam.proyectofinalinkreserve.exception.DniInvalidoException;
import com.salesianostriana.dam.proyectofinalinkreserve.model.Artista;
import com.salesianostriana.dam.proyectofinalinkreserve.service.ArtistaService;
import com.salesianostriana.dam.proyectofinalinkreserve.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ArtistaController {

	
	private final ArtistaService artistaService;
	private final CitaService citaService;
	
	private void cargarDatosDashboard(Model model, String search, int page, int size) {
		model.addAttribute("topArtistas", citaService.obtenerTop3ArtistasMasDemandados());
		Map<String, Object> datos= artistaService.getDatosDashboard(search, page, size);
		model.addAllAttributes(datos);
		if (search != null && !search.trim().isEmpty()) {
	        model.addAttribute("search", search);
	    }
    }
	
	@GetMapping("/api/artistas/citas/{id}")
	@ResponseBody
	public List<Map<String, Object>> getCitasArtista(@PathVariable Long id) {
	    return citaService.getCitasParaCalendario(id);
	}
	
	
	@GetMapping("/Dashboard/Artistas")
	public String PintarDashboardArtistas(@RequestParam(name = "idEditar", required = false) Long idEditar,
			@RequestParam(name = "verPerfilId", required = false) Long verPerfilId,
			@RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {
		
		if (!model.containsAttribute("formularioArtista")) {
            if (idEditar != null && artistaService.findById(idEditar).isPresent()) {
                model.addAttribute("formularioArtista", artistaService.findById(idEditar).get());
            } else {
                model.addAttribute("formularioArtista", new Artista());
            }
        }
        
        cargarDatosDashboard(model, search, page, size);

        if (verPerfilId != null && artistaService.findById(verPerfilId).isPresent()) {
            model.addAttribute("perfilArtista", artistaService.findById(verPerfilId).get());
        } else {
            model.addAttribute("perfilArtista", null);
        }
        
        return "DashboardArtistas";
    }
	
	@PostMapping("/nuevoArtistaCompleto")
	public String submit(@Valid @ModelAttribute("formularioArtista") Artista artista,
			BindingResult bindingResult,
			Model model){
		if (bindingResult.hasErrors()) {
	        model.addAttribute("abrirModal", true);
	        cargarDatosDashboard(model, null, 0, 5); 
	        return "DashboardArtistas"; 
	    }
		try {
	        artistaService.save(artista);
	        return "DashboardArtistas";	
		} catch (DniInvalidoException ex) {
			model.addAttribute("errorDni", ex.getMessage());
	        model.addAttribute("formularioArtista", artista);
	        model.addAttribute("abrirModal", true);
	        cargarDatosDashboard(model, null, 0, 5);
	        return "DashboardArtistas";
	        }
	}
	

	
	@PostMapping("/Dashboard/Artistas/Editar/submit")
	public String submitEditar(@ModelAttribute("formularioArtista") Artista artista,
		 Model model ) {
		try {
		artistaService.editarArtista(artista);
		return "redirect:/Dashboard/Artistas";
		} catch (DniInvalidoException ex) {
			model.addAttribute("errorDni", ex.getMessage());
	        model.addAttribute("formularioArtista", artista);
	        model.addAttribute("abrirModal", true);
	        cargarDatosDashboard(model, null, 0, 5);
            return "DashboardArtistas";
		}
    }
	
	
	@GetMapping("/Dashboard/Artistas/Eliminar/{id}")
	public String submitEliminar(@PathVariable("id") Long id, Model model) {
		artistaService.eliminarArtista(id);    
	    return "redirect:/Dashboard/Artistas";
	}
	
}


