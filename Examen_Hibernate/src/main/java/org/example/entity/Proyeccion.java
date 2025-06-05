package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "proyecciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Proyeccion {

    @EmbeddedId
    private ProyeccionPK id;

    /*
        Le decimos que es una relacion n-1
        Le indicamos que parte de la clave compuesta es
        Le indicamos que columna hace referencia en la tabla extremo opuesto
        Le indicamos el objeto asociado que contendrá el id
    * */

    @ManyToOne
    @MapsId("peliculaId")
    @JoinColumn(name = "id_pelicula")
    private Pelicula pelicula;

}
