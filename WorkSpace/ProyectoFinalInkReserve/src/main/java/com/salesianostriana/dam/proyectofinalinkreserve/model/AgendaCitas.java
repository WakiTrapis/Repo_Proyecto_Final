package com.salesianostriana.dam.proyectofinalinkreserve.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AgendaCitas {
    private List<Cita> listaCitas;
    private LocalDate fechaActual;
    private String diaAnteriorStr;
    private String diaSiguienteStr;
}

