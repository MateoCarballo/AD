package Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Recibe {

    private RecibePK idRecibe;

    @ManyToOne
    @MapsId("idPaciente")
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    @ManyToOne
    @MapsId("idTratamiento")
    @Column(name = "tratamiento_id")
    private Tratamiento tratamiento;

    @ManyToOne
    @MapsId("fechaInicio")
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    /*


    Esta nueva entidad tendrá las siguientes características:

    Se marcará como @Embeddable en lugar de como @Entity.
    Deberá implementar Serializable.
    Podrá usar la anotación @Column para configurar el nombre
    que reciben las columnas en la base de datos.


    CREATE TABLE Recibe (
    id_paciente INT,
    tratamiento_id INT,
    fecha_inicio DATE,
    fecha_fin DATE,
    PRIMARY KEY (id_paciente, tratamiento_id, fecha_inicio),
    FOREIGN KEY (id_paciente) REFERENCES Paciente(id),
    FOREIGN KEY (tratamiento_id) REFERENCES Tratamiento(id)
);

     */
}


