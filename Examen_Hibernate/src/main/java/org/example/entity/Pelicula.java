package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
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

    @Column(name = "año_estreno", columnDefinition = "Year")
    private int anoEstreno;

    private String genero;

    @ManyToMany(mappedBy = "peliculas")
    private List<Actor> actores;

    @OneToOne(mappedBy = "pelicula")
    private Premio premio;

    @OneToMany(mappedBy = "pelicula")
    private List<Proyeccion> proyecciones;
}
