package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @ManyToMany(mappedBy = "actores")
    /*
    @JoinTable(
            name = "actuan",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
     */
    private List<Pelicula> peliculas = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("📽️ Actor [ID: %d]\n" + "   • Nombre: %s\n" + "   • Nacionalidad: %s\n" + "   • Fecha de nacimiento: %s\n", id, nombre, nacionalidad, fechaNacimiento != null ? fechaNacimiento.toString() : "No especificada"));

        sb.append("   • Películas:\n");

        if (peliculas != null && !peliculas.isEmpty()) {
            peliculas.forEach(pelicula -> sb.append("     - ").append(pelicula.getTitulo()).append("\n"));
        } else {
            sb.append("     (Sin películas registradas)\n");
        }
        return sb.toString();
    }

    public void addPelicula(Pelicula pelicula){
        peliculas.add(pelicula);
    }
}
