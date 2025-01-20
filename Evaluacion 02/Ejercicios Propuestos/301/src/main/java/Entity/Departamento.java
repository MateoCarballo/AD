package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Depto")

public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "Nombre")
    private String nombreDepar;
    @Column (name = "Localida")
    private String localidadDepar;


}
