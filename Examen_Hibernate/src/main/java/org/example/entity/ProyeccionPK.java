package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProyeccionPK implements Serializable {

    @Column(name = "pelicula_id")
    private int peliculaId;

    @Column(name = "sala_id")
    private int salaId;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "horario")
    private Time horario;
}
