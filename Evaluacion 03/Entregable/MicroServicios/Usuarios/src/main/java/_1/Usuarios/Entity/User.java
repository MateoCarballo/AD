package _1.Usuarios.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //TODO revisar la forma de generar ids
    // especificar la columna si no coincide con el nombre de la variable @Column(name = "id")
    private int usuario_id;
    @NonNull
    private String nombre;
    @NonNull
    private String correo_electronico;
    @NonNull
    private String direccion;
    @NonNull
    private String contrasena;
}
