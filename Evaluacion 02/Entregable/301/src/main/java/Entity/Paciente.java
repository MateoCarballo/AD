package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "paciente")
    private ArrayList <Cita> citas;

    @OneToMany(mappedBy = "paciente")
    private List<Recibe> listaRecibes;


    public Paciente(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void addCita(Cita c){
        c.setPaciente(this);
        citas.add(c);
    }
    //TODO pendiente de descomposicon en 1-N
}
