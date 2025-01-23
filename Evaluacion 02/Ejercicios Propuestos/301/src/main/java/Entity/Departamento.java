package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "Depto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private long idDepartamento;

    @Column (name = "Nombre")
    private String nombreDepar;

    @Column (name = "Localidad")
    private String localidadDepar;

    /**
     Parte N de la relación 1-N
     Aqui tenemos N empleados por cada departamento

     ### IMPORTANTE ### ### IMPORTANTE ### ### IMPORTANTE ### ### IMPORTANTE ### ### IMPORTANTE ###
     ##############################################################################################
     ###   Relacionamos indicando el nombre del objeto en la otra parte de la relacion          ###
     ###   En este caso el departamento que contiene el empleado se llama "objeto_departamento" ###
     ##############################################################################################
      */

    @OneToMany (mappedBy = "objeto_departamento", cascade = CascadeType.ALL)
    private List<Empleado> empleados ;

    public Departamento (String nombre, String localidad) {
        super();
        this.nombreDepar = nombre;
        this.localidadDepar = localidad;
        this.empleados = new ArrayList<Empleado>();
    }

    public void addEmployee(Empleado e){
        this.empleados.add(e);
        e.setDepartamento(this);
    }

}
