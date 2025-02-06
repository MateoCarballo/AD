package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class RecibePK implements Serializable {
/*
id_paciente INT,
    tratamiento_id INT,
    fecha_inicio DATE,
 */
    @Column(name = "id_paciente")
    private int idPaciente;

    @Column(name = "tratamiento_id")
    private int idTratamiento;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

}
