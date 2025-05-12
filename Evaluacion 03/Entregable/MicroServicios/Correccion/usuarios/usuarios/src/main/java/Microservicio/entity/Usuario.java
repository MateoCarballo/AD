package Microservicio.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private int id;

    @NonNull
    @Column(nullable = false)
    private String nombre;

    @NonNull
    @Column(nullable = false)
    private String correo_electronico;

    @NonNull
    @Column(nullable = false)
    private String direccion;

    @NonNull
    @Column(nullable = false)
    private String contrasena;
}
