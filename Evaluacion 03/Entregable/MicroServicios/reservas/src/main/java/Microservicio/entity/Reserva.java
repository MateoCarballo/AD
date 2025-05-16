package Microservicio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "reserva")
public class Reserva {

    public enum EstadoEnum{
        PENDIENTE, CONFIRMADA, CANCELADA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserva_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    @NonNull
    @Column(name = "usuario_id")
    private int usuarioId;

    @NonNull
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @NonNull
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Enumerated(EnumType.STRING)
    @NonNull
    @Column(name = "estado",length = 20)
    private EstadoEnum estado;

    //TODO preguntar a jose. Si las variables de mi clase java
    // tienen un nombre difernente a los de mysql aqui que debo poner en la relacion
    // el nombre de la columna mysql o el de la variable de la clase que controla esta referencia
    // en este caso que pondria habiracion_id que es la tabla o habitacionId que es la variable java

}
