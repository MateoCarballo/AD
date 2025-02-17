package org.example.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@Data
@Builder

public class ProyectaPK implements Serializable {

    @Column(name = "pelicula_id")
    private int idPelicula;
    @Column(name = "sala_id")
    private int idSala;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "horario")
    private LocalTime horario;

    public ProyectaPK(int idPelicula, int idSala, LocalDate fecha, LocalTime horario) {
        this.idPelicula = idPelicula;
        this.idSala = idSala;
        this.fecha = fecha;
        this.horario = horario;
    }
}
