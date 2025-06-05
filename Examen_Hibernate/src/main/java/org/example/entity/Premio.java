package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "premios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premio")
    private int id;

    @OneToOne(mappedBy = "premio")
    private Pelicula pelicula;

    @Column(name = "nombre_premio")
    private String nombre;

    @Column(name = "año_premio")
    private int anoPremio;
}
