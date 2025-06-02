package Ej_301.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "emp")
@RequiredArgsConstructor
public class Emp {

    @Id
    private long id;

    @NonNull
    @Column(name = "nombre")
    private String nombre;

    @NonNull
    @Column(name = "puesto")
    private String puesto;

    @NonNull
    @Column(name = "Sueldo")
    private double sueldo;

    @NonNull
    @Column(name = "edad")
    private int edad;

    @NonNull
    @Column(name = "dni")
    private String dni;

    @NonNull
    @Column(name = "esJefe")
    private boolean esJfe;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Dpto departamento;

}
