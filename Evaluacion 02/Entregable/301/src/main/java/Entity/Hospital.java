package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    //Todo preguntar a Jose
    // Donde tengo que inicilizar la lista o el arrayList
    @OneToMany(mappedBy = "hospital")
    private Set<Tratamiento> tratamientos ;

    public Hospital(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.tratamientos = new HashSet<>();
    }

    public void addTratamiento(Tratamiento t){
        tratamientos.add(t);
        t.setHospital(this);
    }
}
