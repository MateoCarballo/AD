package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "proyecciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Proyeccion {

    @EmbeddedId
    private ProyeccionPK proyeccionId;

    /*
        Le decimos que es una relacion n-1
        Le indicamos que parte de la clave compuesta es
        Le indicamos que columna hace referencia en la tabla extremo opuesto
        Le indicamos el objeto asociado que contendrá el id
    * */

    @ManyToOne
    @MapsId("peliculaId")
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @ManyToOne
    @MapsId("salaId")
    @JoinColumn(name = "sala_id")
    private Sala sala;
}
