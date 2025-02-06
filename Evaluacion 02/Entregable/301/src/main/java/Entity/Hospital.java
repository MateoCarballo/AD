package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
@Entity
@AllArgsConstructor

public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String nombre;
    private String ubicacion;

    @OneToMany(mappedBy = "hospital")
    private ArrayList<Tratamiento> tratamientos;

    public Hospital(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void addTratamiento(Tratamiento t){
        tratamientos.add(t);
        t.setHospital(this);
    }
}
