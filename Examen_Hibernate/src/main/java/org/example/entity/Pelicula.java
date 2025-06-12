package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    @Column(name = "año_estreno", columnDefinition = "YEAR")
    private int anoEstreno;

    private String genero;

    @ManyToMany
    @JoinTable(
            name = "actuan",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> actores = new ArrayList<>();

    @OneToOne(mappedBy = "pelicula")
    private Premio premio;

    @OneToMany(mappedBy = "pelicula")
    private List<Proyeccion> proyecciones;

    //El set que define la bidireccionalidad es mejor desde donde mas sentido tenga
    //Lo mas logico es que al instanciar una pelicula le digamos, que actores tiene
    //Donde se va a reproducir y si tiene algun premio o no

    // Pelicula <-> Actor
    public void addActor(Actor a){
        this.actores.add(a);
        a.addPelicula(this);
    }

    // Pelicula <-> Premio
    public void setPremio(Premio premio){
        premio.setPelicula(this);
        this.premio = premio;
    }

    public void addProyeccion(Proyeccion proyeccion){
        proyecciones.add(proyeccion);
    }
}
