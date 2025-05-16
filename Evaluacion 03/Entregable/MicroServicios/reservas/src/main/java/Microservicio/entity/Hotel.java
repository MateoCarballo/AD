package Microservicio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private int id;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Habitacion> habitaciones;

    @Column(length = 100)
    @NonNull
    private String nombre;

    // Por defecto ya son 255 @Column(length = 255)
    @NonNull
    private String direccion;

    //Revisar si esto esta bien asi



}
