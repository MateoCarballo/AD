package Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder // Permite usar el metodo factoria y es mas visible y comodo para contruirlo

public class Paciente {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull //Esto lanza errores de nulos en Java
    @Column(nullable = false, length = 100) // Esto lanza errores de nulos en la DB
    private String nombre;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String direccion;

    //TODO preguntar a Jose
    // Por qué no usar Lazy para que solo las cargue cuando las necesita
    // Es mas eficiente, por qué no?
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE)
    private List <Cita> citas;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.REMOVE) // Cuando remueve el objeto remueve todas las referencias a esa tupla (Objeto)
    private List<Recibe> listaRecibes;

    //Constructor requerido
    public Paciente(int id, @NonNull String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Paciente(int id, String nombre, List<Cita> citas, List<Recibe> listaRecibes) {
        this.id = id;
        this.nombre = nombre;
        this.citas = new ArrayList<Cita>();
        this.listaRecibes = new ArrayList<Recibe>();
    }

    public void addCita(Cita c){
        c.setPaciente(this);
        citas.add(c);
    }

    public void addRecibe(Recibe recibe){
        listaRecibes.add(recibe);
        recibe.setPaciente(this);

    }

    /**
     @Override
     public String toString() {
     StringBuilder sb = new StringBuilder();
     sb.append("--------------------\n")
     .append("---Paciente Info---\n")
     .append("--------------------\n")
     .append(String.format("ID: %d\n", id))
     .append(String.format("Nombre: %s\n", nombre))
     .append(String.format("Fecha de Nacimiento: %s\n",
     fechaNacimiento != null ? fechaNacimiento.toString() : "No registrada"))
     .append(String.format("Dirección: %s\n",
     direccion != null ? direccion : "No registrada"));

     // Mostrar número total de citas
     int numCitas = (citas != null) ? citas.size() : 0;
     sb.append(String.format("Total de Citas: %d\n", numCitas));

     // Si hay citas, listarlas
     if (numCitas > 0) {
     sb.append("--------------------\n")
     .append("*** Detalles de Citas:***\n")
     .append("--------------------\n")
     .append("--------------------\n");
     for (Cita cita : citas) {
     sb.append("--------------------\n")
     .append("--------------------\n")
     .append(String.format("   🏥 Fecha: %s | Doctor: %s | Especialidad: %s\n",
     cita.getId() != 0 ? cita.getId() : "Sin fecha",
     cita.getFecha() != null ? cita.getFecha().toString() : "Sin fecha",
     cita.getDoctor() != null ? cita.getDoctor().getNombre() : "Desconocido",
     cita.getEstado() != null ? cita.getEstado().toString() : "Sin fecha"

     ));
     }
     }

     // Mostrar número total de recibes
     int numRecibes = (listaRecibes != null) ? listaRecibes.size() : 0;
     sb.append(String.format("📝 Total de Recibes: %d\n", numRecibes));

     // Si hay recibes, listarlas
     if (numRecibes > 0) {
     sb.append("--------------------\n")
     .append("*** Detalles del Tratamiento:***\n")
     .append("--------------------\n")
     .append("--------------------\n");
     for (Recibe recibe : listaRecibes) {
     sb.append(String.format("   🏥 Tipo de tratamiento: %s | Centro: %s | Costo: %s | Fecha inicio: %s| Fecha fin: %s\n",
     recibe.getTratamiento() != null ? recibe.getTratamiento().getTipo() : "Sin Tipo de tratamiento",
     recibe.getTratamiento().getHospital() != null ? recibe.getTratamiento().getHospital().getNombre() : "Sin Lugar de Tratamiento",
     recibe.getTratamiento() != null ? recibe.getTratamiento().getCosto() : "Sin costo de tratamiento",
     recibe.getTratamiento() != null ? recibe.getIdRecibe().getFechaInicio() : "Sin fecha Inicio",
     recibe.getTratamiento() != null ? recibe.getFechaFin() : "Sin fecha Fin"

     ));
     }
     }

     return sb.toString();
     }
     */

    @Override
    public String toString() {
        StringBuilder toStringMejorado = new StringBuilder();
        toStringMejorado
                .append("---------------------------------------\n")
                .append("--- Paciente " + this.nombre + " ---\n")
                .append("Id -> " + this.id + "\n")
                .append("Fecha de Nacimiento -> " + this.fechaNacimiento + "\n")
                .append("Direccion -> " + this.direccion + "\n")
                .append("---------------------------------------\n")
                .append("Citas:\n ");
                for (Cita c :citas){
                    toStringMejorado.append(c.toStringParaPaciente());
                }
                toStringMejorado.append("---------------------------------------\n")
                                .append("Recibe:\n ");
                for (Recibe r :listaRecibes){
                    toStringMejorado.append(r.toStringParaPaciente());
                }
                toStringMejorado.append("---------------------------------------\n");
        return toStringMejorado.toString();
    }


}
