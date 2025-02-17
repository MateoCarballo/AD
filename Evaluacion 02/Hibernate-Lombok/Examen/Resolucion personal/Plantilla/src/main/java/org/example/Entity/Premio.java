package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "premios")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Premio {
    /*
    CREATE TABLE premios (
    id_premio INT AUTO_INCREMENT PRIMARY KEY,
    pelicula_id INT UNIQUE,
    nombre_premio VARCHAR(100) NOT NULL,
    año_premio YEAR NOT NULL,
    FOREIGN KEY (pelicula_id) REFERENCES peliculas(id_pelicula)
);
     */

    @Id
    @Column(name = "id_premio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "id_Pelicula")
    private Pelicula pelicula;

    @NonNull
    @Column(name = "nombre_premio")
    private String nombrePremio;

    @NonNull
    @Column(name = "año_premio")
    private int anhoPremio;




}
