package com.salesianostriana.dam.proyectofinalinkreserve.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotosService {

	private final Path root = Paths.get("uploads");

    public String store(MultipartFile file) {
        try {
            if (!Files.exists(root)) Files.createDirectory(root);
            
            String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Files.copy(file.getInputStream(), this.root.resolve(nombreUnico));
            return nombreUnico;
        } catch (IOException e) {
            throw new RuntimeException("No se pudo guardar la imagen", e);
        }
    }
}
