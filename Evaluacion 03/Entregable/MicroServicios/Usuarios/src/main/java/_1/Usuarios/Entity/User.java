package _1.Usuarios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO revisar la forma de generar ids
    @Column(name="usuario_id")
    private int id;
    @Getter
    @NonNull
    private String nombre;
    @NonNull
    private String correo_electronico;
    @NonNull
    private String direccion;
    @NonNull
    private String contrasena;

}
