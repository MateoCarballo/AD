package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "premios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Premio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_premio")
    private int id;

    @OneToOne
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @Column(name = "nombre_premio")
    private String nombre;

    @Column(name = "año_premio", columnDefinition = "Year")
    private int anoPremio;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("🏆 Premio [ID: %d]\n" + "   • Nombre: %s\n" + "   • Año premio: %s\n", id, nombre, anoPremio));

        if (pelicula != null) {
            sb.append("Pelicula [ID] ").append(pelicula.getId()).append(" nombre ").append(pelicula.getTitulo());
        } else {
            sb.append("     (Sin películas registradas)\n");
        }
        return sb.toString();
    }
}
