package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "peliculas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pelicula")
    private int id;

    private String titulo;

    @Column(name = "año_estreno")
    private int anoEstreno;

    private String genero;

    @ManyToMany(mappedBy = "peliculas")
    private List<Actor> actores;

    @OneToOne
    @JoinColumn(name = "pelicula_id")
    // referencia a la columna
    // de la otra tabla que contiene este id de la pelicula
    private Premio premio;

}
