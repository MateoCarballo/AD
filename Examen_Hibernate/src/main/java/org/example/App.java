package org.example;

import org.example.entity.Actor;
import org.example.entity.Pelicula;
import org.example.entity.Premio;
import org.example.repository.ActorRepositorio;
import org.example.repository.PeliculaRepositorio;
import org.example.repository.PremioRepositorio;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Arranco clase main de App");
        int opcionSeleccionada = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ActorRepositorio actorRepositorioImpl = new ActorRepositorio(session);
            PeliculaRepositorio peliculaRepositorioImpl = new PeliculaRepositorio(session);
            PremioRepositorio premioRepositorioImpl = new PremioRepositorio(session);
            do{
                opcionSeleccionada = printearMenuOpciones();
                switch (opcionSeleccionada) {
                    //Agregar actor
                    case 1 -> {
                        actorRepositorioImpl.guardar(preguntarDatosActor());
                    }
                    //Eliminar actor
                    case 2 -> {
                        //Obtener el id que queremos elimianr.
                        Optional<Actor> actor = actorRepositorioImpl.encontrarPorId(
                                preguntarActorEliminar(actorRepositorioImpl)
                        );
                        //Si existe el id que queremos eliminar, es decir, tiene contenido y no está vacío.
                        if (actor.isPresent()) {
                            actorRepositorioImpl.eliminar(actor.get());
                        }
                    }
                    //Agregar pelicula
                    case 3 -> {
                        peliculaRepositorioImpl.guardar(
                                preguntarDatosPelicula(
                                        actorRepositorioImpl,
                                        premioRepositorioImpl
                                )
                        );
                    }

                    //Eliminar pelicula
                    case 4 -> {

                    }
                }
            }while(opcionSeleccionada != 15);
            System.out.println("Conexión a Hibernate exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Pelicula preguntarDatosPelicula(ActorRepositorio actorRepositorio, PremioRepositorio premioRepositorio) {
        Pelicula nuevaPelicula = new Pelicula();
        System.out.println("Titulo de la pelicula?");
        nuevaPelicula.setTitulo(sc.nextLine());
        System.out.println("Genero?");
        nuevaPelicula.setGenero(sc.nextLine());
        System.out.println("Año de estreno?");
        nuevaPelicula.setAnoEstreno(Integer.parseInt(sc.nextLine()));
        // Setear el premio si tiene alguno entre los existentes
        nuevaPelicula.setPremio(obtenerPremio(premioRepositorio,nuevaPelicula.getTitulo()));
        // Setear los actores existentes
        nuevaPelicula.setActores(obtenerReparto(actorRepositorio, nuevaPelicula.getTitulo()));
        return nuevaPelicula;
    }

    private static List<Actor> obtenerReparto(ActorRepositorio actorRepositorio,String titulo) {
        List<Actor> reparto = new ArrayList<>();

        System.out.println("Elige actores por id (-1 para salir)");
        actorRepositorio.encontrarTodos();
        String entradaTeclado = "0";
        while (!entradaTeclado.equalsIgnoreCase("-1")) {
            try {
                entradaTeclado = sc.nextLine();
                int id = Integer.parseInt(entradaTeclado);
                if (actorRepositorio.encontrarPorId(id).isPresent())
                    reparto.add(actorRepositorio.encontrarPorId(id).get());
                System.out.println("Agregado el actor con id " + id + " al reparto de la pelicula " + titulo);
            } catch (NumberFormatException e) {
                System.out.println("Introduce el id del actor");
            }
        }
        return reparto;
    }

    private static Premio obtenerPremio(PremioRepositorio premioRepositorio, String tituloPelicula) {
        Premio premio = new Premio();

        System.out.println("Introduce el premio de la pelicula");
        try {
            List<Premio> premios = premioRepositorio.encontrarTodos();
            premios.forEach(System.out::println);
            String entradaTeclado = sc.nextLine();
            int id = Integer.parseInt(entradaTeclado);
            if (premioRepositorio.encontrarPorId(id).isPresent())
                premio = premioRepositorio.encontrarPorId(id).get();
            System.out.println("Agregado el premio con id " + id + " a la pelicula " + tituloPelicula);
        } catch (NumberFormatException e) {
            System.out.println("Introduce el id del premio");
        }

        return premio;
    }

    private static int printearMenuOpciones() {
        int indiceSeleccionado = 0;
        boolean datoValido = false;
        do {
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
            try {
                indiceSeleccionado = Integer.parseInt(sc.nextLine());
                if (indiceSeleccionado < 15 && indiceSeleccionado > 0) datoValido = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Debes introducir un numero valido");
            }
        } while (!datoValido);
        return indiceSeleccionado;
    }

    private static Actor preguntarDatosActor() {
        Actor nuevoActor = new Actor();
        System.out.println("Nombre del actor?");
        nuevoActor.setNombre(sc.nextLine());
        System.out.println("Fecha de nacimiento? (AAAA-MM-DD)");
        nuevoActor.setFechaNacimiento(LocalDate.parse(sc.nextLine()));
        System.out.println("Nacionalidad del actor?");
        nuevoActor.setNacionalidad(sc.nextLine());
        return nuevoActor;
    }

    private static int preguntarActorEliminar(ActorRepositorio actorRepositorio) {
        int idEliminar = -1;
        do {
            try {
                System.out.println("Cual es el id del actor para eliminar?");
                List<Actor> actoresDisponibles = actorRepositorio.encontrarTodos();
                actoresDisponibles.forEach(System.out::println);
                idEliminar = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Debes introducir un numero");
            }
        } while (idEliminar < 0);

        return idEliminar;
    }
}
