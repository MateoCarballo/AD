package Ejercicio._4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="depto")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numdep;
    private String nomdep;
    private String localidad;
    //Relacion 1-1 hacia la tabla de empleados
    @OneToOne
    @JoinColumn(name = "numjefe")
    private Empleado jefe;

    public Departamento(int numdep, String nomdep, String localidad, Empleado jefe) {
        this.numdep = numdep;
        this.nomdep = nomdep;
        this.localidad = localidad;
        this.jefe = jefe;
    }
}
