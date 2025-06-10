package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "salas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private int id;

    private String nombre;

    private int capacidad;

    @OneToMany(mappedBy = "sala")
    private List<Proyeccion> proyecciones;

    public void addProyeccion(Proyeccion proyeccion){
        proyecciones.add(proyeccion);
    }
}
