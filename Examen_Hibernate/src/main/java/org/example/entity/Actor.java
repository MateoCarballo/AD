package org.example.entity;

import jakarta.persistence.*;
import jdk.vm.ci.meta.Local;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "actores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Actor {
    @Id
    @Column(name = "id_actor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String nacionalidad;

    @ManyToMany
    @JoinTable(
            name = "actuan",
            joinColumns = @JoinColumn(name = "id_actor"),
            inverseJoinColumns = @JoinColumn(name = "id_pelicula")
    )
    //Si necesitamos excluir del toString para evitar recursividad infinita
    //@ToString.Exclude
    private List<Pelicula> peliculas;
}
