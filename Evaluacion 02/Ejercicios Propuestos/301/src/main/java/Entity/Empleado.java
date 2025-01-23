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
    @Column(name = "id")
    private long id_empleado;

    @Column (name = "Nombre" )
    private String nombreEmpleado;

    @Column (name = "Puesto")
    private String puesto;

    /**
    Parte 1 de la relacion 1-N.
    Aquí solo tenemos 1 departamento con N empleados

     ### IMPORTANTE ### ### IMPORTANTE ### ### IMPORTANTE ### ### IMPORTANTE ### ### IMPORTANTE ###
     ##############################################################################################
     ###  Lo relacionamos con la otra parte indicandole en la notacion de '@JoinColumn el       ###
     ### nombre de la colunma que ocupara el id                                                 ###
     ##############################################################################################

     */

    @ManyToOne
    @JoinColumn ( name = "id_departamento")
    private Departamento objeto_departamento;

    //@Column (name = "Sueldo",precision = 2, scale =7")
    @Column (name = "Sueldo", columnDefinition = "DECIMAL(7,2)")
    private double sueldo;

    private int edad;
    private String dni;
    private boolean esJefe;

    public Empleado(String nombreEmpleado, String puesto, double sueldo, int edad, String dni, boolean esJefe) {
        this.nombreEmpleado = nombreEmpleado;
        this.puesto = puesto;
        this.sueldo = sueldo;
        this.edad = edad;
        this.dni = dni;
        this.esJefe = esJefe;
    }

    public void setDepartamento (Departamento d){
        this.objeto_departamento = d;
    }

}
