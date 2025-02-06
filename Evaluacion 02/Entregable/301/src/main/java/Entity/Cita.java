package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor

public class Cita {

    @Id
    private int id;
    @NonNull
    private LocalDate fecha;
    private String estado;
    @Column(name = "id_tratamiento")
    @ManyToOne
    @JoinColumn(name = "id_tratamiento")
    private Paciente paciente;
}
