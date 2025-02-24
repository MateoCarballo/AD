package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "actores")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Actor {
    /*
    id_actor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    nacionalidad VARCHAR(100) NOT NULL
     */
    @Id
    @Column(name = "id_actor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    //@Column(name = "nombre")
    private String nombre;

    @NonNull
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NonNull
    private String nacionalidad;

//    @ToString.Exclude
    @ManyToMany(mappedBy = "listadoActores")
    private List<Pelicula> listadoPeliculas;

    public Actor(int id, @NonNull String nombre, @NonNull LocalDate fechaNacimiento, @NonNull String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.listadoPeliculas = new ArrayList<>();
    }

    public void addPelicula(Pelicula p){
        p.getListadoActores().add(this);
        listadoPeliculas.add(p);
    }
}
