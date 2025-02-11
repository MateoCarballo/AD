package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Recibe {
    @EmbeddedId
    private RecibePK idRecibe;

    @ManyToOne
    @MapsId("idPaciente")
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @MapsId("idTratamiento")
    @JoinColumn(name = "tratamiento_id")
    private Tratamiento tratamiento;

//    TODO preguntar a Jose
//    @ManyToOne
//    @MapsId("fechaInicio")
//    @Column(name = "fecha_inicio")
//    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

}


