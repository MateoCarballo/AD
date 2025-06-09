package org.example;

import org.example.entity.Actor;
import org.example.repository.ActorRepositorio;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Arranco clase main de App");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ActorRepositorio actorRepositorioImpl = new ActorRepositorio(session);
            int opcionSeleccionada = printearMenuOpciones();
            switch(opcionSeleccionada){
                //Agregar actor
                case 1 ->{
                    actorRepositorioImpl.guardar(preguntarDatosActor());
                }
                //Eliminar actor
                case 2 -> {
                    //Obtener el id que queremos elimianr.
                    Optional<Actor> actor = actorRepositorioImpl.encontrarPorId(preguntarActorEliminar());
                    //Si existe el id que queremos eliminar, es decir, tiene contenido y no está vacío.
                    if (actor.isPresent()){
                        actorRepositorioImpl.eliminar(actor.get());
                    }
                }
            }
            System.out.println("Conexión a Hibernate exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int printearMenuOpciones(){
        int indiceSeleccionado = 0;
        boolean datoValido = false;
        do{
            String mensaje = "1. Crear actor\n" +
                    "2. Eliminar actor\n" +
                    "3. Crear pelicula\n" +
                    "4. Eliminar pelicula\n" +
                    "5. Crear premio\n" +
                    "6. Eliminar premio\n" +
                    "7. Modificar género de una película\n" +
                    "8. Asignar un premio a una película\n" +
                    "9. Asignar un actor a una película\n" +
                    "10. Asignar una película a una sala en una fecha y una hora\n" +
                    "11. Consulta 1\n" +
                    "12. Consulta 2\n" +
                    "13. Consulta 3\n" +
                    "14. Consulta 4\n" +
                    "15. Salir";
            System.out.println(mensaje);
            try{
                indiceSeleccionado = Integer.parseInt(sc.nextLine());
                if (indiceSeleccionado < 15 && indiceSeleccionado > 0) datoValido = true;
            }catch (NumberFormatException nfe){
                System.out.println("Debes introducir un numero valido");
            }
        }while(!datoValido);
        return indiceSeleccionado;
    }

    private static Actor preguntarDatosActor(){
        Actor nuevoActor= new Actor();
        System.out.println("Nombre del actor?");
        nuevoActor.setNombre(sc.nextLine());
        System.out.println("Fecha de nacimiento? (AAAA-MM-DD)");
        nuevoActor.setFechaNacimiento(LocalDate.parse(sc.nextLine()));
        System.out.println("Nombre del actor?");
        nuevoActor.setNacionalidad(sc.nextLine());
        return nuevoActor;
    }

    private static int preguntarActorEliminar(){
        int idEliminar = -1;
        do{
            try{
                System.out.println("Cual es el id del actor para eliminar?");
                idEliminar = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException nfe){
                System.out.println("Debes introducir un numero");
            }
        }while (idEliminar<0);

        return idEliminar;
    }
}
