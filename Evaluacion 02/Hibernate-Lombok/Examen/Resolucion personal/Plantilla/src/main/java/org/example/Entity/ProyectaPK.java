package org.example.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProyectaPK implements Serializable {

    @Column(name = "pelicula_id")
    private int idPelicula;
    @Column(name = "sala_id")
    private int idSala;
    @Column(name = "fecha")
    private LocalDate fecha;
    @Column(name = "horario")
    private LocalTime horario;

}
