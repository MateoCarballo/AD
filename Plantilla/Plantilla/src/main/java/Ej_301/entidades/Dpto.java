package Ej_301.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Data
@Table(name = "depto")
@NoArgsConstructor
@RequiredArgsConstructor
public class Dpto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dpto_id")
    private long id;

    @NonNull
    @Column(name = "Nombre")
    private String nombreDepto;

    @NonNull
    @Column(name = "Localidad")
    private String localidad;

    @OneToMany(mappedBy = "departamento")
    private ArrayList<Emp> empleados;

    public void addEmps(Emp emp) {
        this.empleados.add(emp);
        emp.setDepartamento(this);
    }

}
