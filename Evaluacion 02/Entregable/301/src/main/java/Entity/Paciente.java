package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@Entity
@AllArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nombre;
    private LocalDate fechaNacimiento;
    private String direccion;

    @OneToMany(mappedBy = "Cita")
    private ArrayList <Cita> citas;

}
