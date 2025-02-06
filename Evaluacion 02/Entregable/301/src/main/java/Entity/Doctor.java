package Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nombre;
    private String especialidad;
    private String telefono;

    public Doctor(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
