package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Peliculas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Pelicula {

    /*
    id_pelicula INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    año_estreno YEAR NOT NULL,
    genero VARCHAR(50) NOT NULL
     */
    @Id
    @Column(name = "id_pelicula")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String titulo;

    @NonNull
    @Column(name = "año_estreno", columnDefinition = "YEAR")
    private int anhoEstreno;

    @NonNull
    private String genero;

    @OneToOne(mappedBy = "pelicula")
    private Premio premio;

    //SETEO DESDE EL OTRO LADO DE LA RELACION LA BIDIRECCIONALIDAD

    @ManyToMany
    @JoinTable(name = "actuan",
                joinColumns = @JoinColumn(name = "pelicula_id"),
                inverseJoinColumns = @JoinColumn(name = "id_actor"))
    private List<Actor> listadoActores;

    @ManyToOne
    @JoinTable(name = "pelicula_id")
    private List<Proyecta> proyeccionesPelicula;

    public Pelicula(int id, @NonNull String titulo, @NonNull int anhoEstreno, @NonNull String genero, Premio premio) {
        this.id = id;
        this.titulo = titulo;
        this.anhoEstreno = anhoEstreno;
        this.genero = genero;
        this.premio = premio;
        this.listadoActores = new ArrayList<>();
        this.proyeccionesPelicula = new ArrayList<>();
    }

    public void setPremio(Premio p){
        p.setPelicula(this);
        premio = p;
    }

    public void addProyeccion(Proyecta p){
        addProyeccion(p);
        p.setPelicula(this);
    }
}
