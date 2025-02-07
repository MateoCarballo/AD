package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder // Permite usar el metodo factoria y es mas visible y comodo para contruirlo
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull //Esto lanza errores de nulos en Java
    @Column(nullable = false, length = 100) // Esto lanza errores de nulos en la DB
    private String nombre;

    private LocalDate fechaNacimiento;
    private String direccion;

    //TODO preguntar a Jose
    // Por qué no usar Lazy para que solo las cargue cuando las necesita
    // Es mas eficiente, por qué no?
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List <Cita> citas;

    @OneToMany(mappedBy = "paciente")
    private List<Recibe> listaRecibes;

    //Constructor requerido
    public Paciente(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Paciente(int id, String nombre, List<Cita> citas, List<Recibe> listaRecibes) {
        this.id = id;
        this.nombre = nombre;
        this.citas = new ArrayList<Cita>();
        this.listaRecibes = new ArrayList<Recibe>();
    }

    public void addCita(Cita c){
        c.setPaciente(this);
        citas.add(c);
    }

    public void addRecibe(Recibe recibe){
        listaRecibes.add(recibe);
        recibe.setPaciente(this);

    }

    //TODO pendiente de descomposicon en 1-N
}
