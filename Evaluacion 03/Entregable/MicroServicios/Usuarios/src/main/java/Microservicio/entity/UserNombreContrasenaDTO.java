package Microservicio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserNombreContrasenaDTO {
    private String nombre;
    private String contrasena;
}
