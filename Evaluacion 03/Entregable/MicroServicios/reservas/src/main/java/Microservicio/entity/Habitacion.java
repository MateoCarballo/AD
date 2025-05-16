package Microservicio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/*
TODO preguntar a Jose.
Tiene sentido que pongamos dentro de la clase que se vincula a tabla todos los objetos a no nulo
para evitarlos en mysql y si necesitamos partes usar DTO ?
 */
@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "habitacion")
public class Habitacion {
    public enum TipoHabitacion{
        INDIVIDUAL, DOBLE, TRIPLE, SUITE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitacion_id")
    private int id;

    @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    @ManyToOne
    @JoinColumn(name = "hote_id")
    private Hotel hotel;

    @NonNull
    @Column(name = "numero_habitacion")
    private int numeroHabitacion;

    // Individual, Doble, Triple, Suite

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(length = 50, nullable = false)

    private TipoHabitacion tipo;

    @NonNull
    @Column(precision = 10, scale = 2) //DECIMAL (10,2)
    private BigDecimal precio;

    /**
     * ¿Por qué BigDecimal?
     * Precisión exacta: Evita problemas de redondeo con números decimales
     *
     * Control preciso: Ideal para operaciones monetarias
     *
     * Soporte nativo: JPA/JPA lo maneja perfectamente con anotaciones
     */
}
