package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "Depto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "Nombre")
    private String nombreDepar;

    @Column (name = "Localidad")
    private String localidadDepar;

    @OneToMany (mappedBy = "id_departamento")
    private Set<Empleado> empleados;

    public void addEmployee(Empleado e){
        this.empleados.add(e);
    }

}
