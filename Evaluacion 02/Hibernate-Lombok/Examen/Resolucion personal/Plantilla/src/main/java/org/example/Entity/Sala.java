package org.example.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "salas")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Sala {
    /*
    id_sala INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    capacidad INT NOT NULL
     */

    @Id
    @Column(name = "id_sala")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String nombre;

    @NonNull
    private int capacidad;

    @ManyToOne
    @JoinTable(name = "sala_id")
    private List<Proyecta> proyeccionesSala;

    public Sala(int id, @NonNull String nombre, @NonNull int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.proyeccionesSala = new ArrayList<>();
    }

    public void addProyeccion (Proyecta p){
        proyeccionesSala.add(p);
        p.setSala(this);
    }
}
