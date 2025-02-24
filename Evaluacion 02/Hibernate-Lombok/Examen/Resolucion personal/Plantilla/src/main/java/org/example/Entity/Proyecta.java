package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "proyecciones")
@Builder



public class Proyecta {

    @EmbeddedId
    private ProyectaPK idCompuesta;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @MapsId
    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;
}
