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
    @Column(name = "pelicula_id")
    @OneToMany(mappedBy = "proyeccionesPelicula")
    private Pelicula pelicula;

    @MapsId
    @Column(name = "sala_id")
    @OneToMany(mappedBy = "proyeccionesSala")
    private Sala sala;

    public Proyecta(ProyectaPK idCompuesta) {
        this.idCompuesta = idCompuesta;
    }
}
