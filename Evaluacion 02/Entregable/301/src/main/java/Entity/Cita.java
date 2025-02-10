package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private LocalDate fecha;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    //Este joinColumn te dice en que columna
    //tiene la clave foranea que apunta al objeto
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // orphanRemoval = true para evitar referencias huérfanas.
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    public Cita(int id, @NonNull LocalDate fecha, Paciente paciente) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
    }

    public void setDoctor(Doctor d){
        this.doctor= d;
        this.doctor.setCita(this);
        d.setCita(this);
    }


}
