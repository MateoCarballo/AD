package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "Nombre" )
    private String nombreEmpleado;

    @Column (name = "Puesto")
    private String puesto;

    @ManyToOne
    @JoinColumn ( name = "depto_id")
    private Departamento id_departamento;

    //@Column (name = "Sueldo",precision = 2, scale =7")
    @Column (name = "Sueldo", columnDefinition = "DECIMAL(7,2)")

    private double sueldo;

    public void setDepartamento (Departamento d){
        this.id_departamento =d;
    }

}
