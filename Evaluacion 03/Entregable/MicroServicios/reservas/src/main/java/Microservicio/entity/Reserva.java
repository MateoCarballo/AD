package Microservicio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private int reservaId;

    @Column(name = "usuario_id", nullable = false)
    private int usuarioId; // Enlace a usuario (solo guardamos id, por ahora)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(length = 20)
    private String estado; // Pendiente, Confirmada, Cancelada

    //TODO preguntar a jose. Si las variables de mi clase java
    // tienen un nombre difernente a los de mysql aqui que debo poner en la relacion
    // el nombre de la columna mysql o el de la variable de la clase que controla esta referencia
    // en este caso que pondria habiracion_id que es la tabla o habitacionId que es la variable java

}
