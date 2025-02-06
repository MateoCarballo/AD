package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@RequiredArgsConstructor

public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String tipo;
    @Column(columnDefinition = "DECIMAL(10,2)")
    private double costo;
    @ManyToOne
    @JoinColumn(name = "id_hospital")
    private Hospital hospital;
    @OneToMany(mappedBy = "tratamiento")
    private List<Recibe> listaRecibes;


    public Tratamiento(@NonNull String tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }

    //TODO pendiente de descomposicon en 1-N

}
