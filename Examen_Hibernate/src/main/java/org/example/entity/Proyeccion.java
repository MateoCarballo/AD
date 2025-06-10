package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.loader.ast.spi.Loadable;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "proyecciones")
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

    /*
    En este caso particular como toda la tupla es clave primaria
    Tiene sentido que siempre que se contruya una nueva instacion de esta clase
    debe tener definidos todos los campos.
     */

    public Proyeccion(Pelicula pelicula, Sala sala, LocalDate fecha, Time horario){
        this.pelicula = pelicula;
        this.sala = sala;
        this.proyeccionId = ProyeccionPK.builder()
                .peliculaId(pelicula.getId())
                .salaId(sala.getId())
                .fecha(fecha)
                .horario(horario)
                .build();
        pelicula.addProyeccion(this);
        sala.addProyeccion(this);
    }
}
