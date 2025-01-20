package Entity;

import jakarta.persistence.*;

@Entity
@Table (name = "Emp")
public class Empleado {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "Nombre" )
    private String nombreEmpleado;

    @Column (name = "Puesto")
    private String puesto;

    @Column (name = "Sueldo",precision = 7, scale = 2)
    private double sueldo;

}
