package Microservicio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "hotel")
@Data
@AllArgsConstructor //Genera un constructor con todos los campos de la clase como parámetros.
@NoArgsConstructor //Genera un constructor sin parámetros, es decir, el constructor vacío.

public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private int id;

    @Column(length = 100, nullable = false)
    private String nombre;

    // Por defecto ya son 255 @Column(length = 255)
    private String direccion;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Habitacion> habitaciones;
}