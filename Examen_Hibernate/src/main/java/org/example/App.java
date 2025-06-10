package org.example;

import org.example.entity.Actor;
import org.example.entity.Pelicula;
import org.example.entity.Premio;
import org.example.repository.ActorRepositorio;
import org.example.repository.PeliculaRepositorio;
import org.example.repository.PremioRepositorio;
import org.hibernate.Session;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Arranco clase main de App");
        int opcionSeleccionada;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ActorRepositorio actorRepositorioImpl = new ActorRepositorio(session);
            PeliculaRepositorio peliculaRepositorioImpl = new PeliculaRepositorio(session);
            PremioRepositorio premioRepositorioImpl = new PremioRepositorio(session);
            do {
                opcionSeleccionada = printearMenuOpciones();
                switch (opcionSeleccionada) {
                    //Crear actor
                    case 1 -> {
                        actorRepositorioImpl.guardar(preguntarDatosActor());
                    }
                    //Eliminar actor
                    case 2 -> {
                        //Obtener el id que queremos eliminar.
                        Optional<Actor> actor = actorRepositorioImpl.encontrarPorId(
                                preguntarActorEliminar(actorRepositorioImpl)
                        );
                        //Si existe el id que queremos eliminar, es decir, tiene contenido y no está vacío.
                        actor.ifPresent(actorRepositorioImpl::eliminar);
                    }
                    //Agregar pelicula
                    case 3 -> {
                        peliculaRepositorioImpl.guardar(
                                preguntarDatosPelicula(
                                        peliculaRepositorioImpl,
                                        actorRepositorioImpl,
                                        premioRepositorioImpl
                                )
                        );
                    }
                    //Eliminar pelicula
                    case 4 -> {
                        eliminarPelicula(peliculaRepositorioImpl);
                    }
                    case 5 -> crearPremio(premioRepositorioImpl);
                    case 6 -> eliminarPremio(premioRepositorioImpl);
                    case 7 -> modificarGeneroPelicula(peliculaRepositorioImpl);
                }
            } while (opcionSeleccionada != 15);
            System.out.println("Conexión a Hibernate exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void modificarGeneroPelicula(PeliculaRepositorio peliculaRepositorioImpl) {
        peliculaRepositorioImpl.encontrarTodos().forEach(System.out::println);
        System.out.println("Introduce el id de la pelicula que deseas modificar");
        int id = Integer.parseInt(sc.nextLine());
        peliculaRepositorioImpl.encontrarPorId(id).ifPresentOrElse(
                pelicula -> {
                    System.out.println("Has seleccionado " + pelicula.getTitulo());
                    String viejoGenero = pelicula.getGenero();
                    System.out.println("Escribe el nuevo genero para la pelicula");
                    pelicula.setGenero(sc.nextLine());
                    peliculaRepositorioImpl.actualizar(pelicula);
                    System.out.println("Se ha cambiado el genero de " + viejoGenero + " a " + pelicula.getGenero());
                },
                ()-> System.out.println("Introduce un id de los que se muestran")
        );
    }

    private static void eliminarPremio(PremioRepositorio premioRepositorioImpl) {
        int idIntroducido;
        premioRepositorioImpl.encontrarTodos().forEach(System.out::println);
//        System.out.println("Introduce el id del premio que quieres eliminar");
//        idIntroducido = Integer.parseInt(sc.nextLine());
//        premioRepositorioImpl.encontrarPorId(idIntroducido).ifPresent(premioRepositorioImpl::eliminar);

//              VERSION PRO PARA TENER MENSAJE POR PANTALLA DE CONFIRMACION DE ELIMINACION
        idIntroducido = Integer.parseInt(sc.nextLine());
        premioRepositorioImpl.encontrarPorId(idIntroducido).ifPresentOrElse(
                premio -> {
                    premioRepositorioImpl.eliminar(premio);
                    System.out.println("Se ha eliminado el premio con el id " + idIntroducido);
                },
                () -> System.out.println("No se ha encontrado ningun premio con el id introducido " + idIntroducido)
        );
    }

    private static void crearPremio(PremioRepositorio premioRepositorio) {
        System.out.println("Introduce el nombre del premio");
        String nombrePremio = sc.nextLine();
        System.out.println("Introduce el año del premio");
        int anoPremio = Integer.parseInt(sc.nextLine());
        Premio nuevoPremio = Premio.builder()
                .nombre(nombrePremio)
                .anoPremio(anoPremio)
                .build();
        premioRepositorio.guardar(nuevoPremio);
    }

    private static void eliminarPelicula(PeliculaRepositorio peliculaRepositorio) {
        peliculaRepositorio.encontrarTodos().forEach(System.out::println);
        System.out.println("Que pelicula deseas eliminar ? (Escribe el id y pulsa enter)");
        int idPelicula = Integer.parseInt(sc.nextLine());
        peliculaRepositorio.encontrarPorId(idPelicula).ifPresent(peliculaRepositorio::eliminar);
        // Equivalente
        // peliculaRepositorio.encontrarPorId(idPelicula).ifPresent(p -> peliculaRepositorio.eliminar(p));
    }

    private static Pelicula preguntarDatosPelicula(PeliculaRepositorio peliculaRepositorio, ActorRepositorio actorRepositorio, PremioRepositorio premioRepositorio) {
        Pelicula nuevaPelicula = new Pelicula();
        System.out.println("Titulo de la pelicula?");
        nuevaPelicula.setTitulo(sc.nextLine());
        System.out.println("Genero?");
        nuevaPelicula.setGenero(sc.nextLine());
        System.out.println("Año de estreno?");
        nuevaPelicula.setAnoEstreno(Integer.parseInt(sc.nextLine()));
        System.out.println("Esta pelicula tiene algun premio ? (y/n)");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            System.out.println("Nombre del premio?");
            String nombrePremio = sc.nextLine();
            System.out.println("Año del premio?");
            String anoPremio = sc.nextLine();
            Premio nuevoPremio = Premio.builder()
                    .nombre(nombrePremio)
                    .anoPremio(Integer.parseInt(anoPremio))
                    .build();
            premioRepositorio.guardar(nuevoPremio);
            nuevaPelicula.setPremio(nuevoPremio);
        }

        //Necesaria guardar en esta parte en la base de datos antes de setear los actores para que no nos de error
        peliculaRepositorio.guardar(nuevaPelicula);
        // Setear los actores existentes
        relacionBidireccionalActorPelicula(actorRepositorio, nuevaPelicula);

        return nuevaPelicula;
    }

    private static void relacionBidireccionalActorPelicula(ActorRepositorio actorRepositorio, Pelicula pelicula) {
        actorRepositorio.encontrarTodos().forEach(System.out::println);
        System.out.println("Elige actores por id (-1 para salir)");
        String entradaTeclado = "0";
        while (!entradaTeclado.equalsIgnoreCase("-1")) {
            try {
                entradaTeclado = sc.nextLine();
                int id = Integer.parseInt(entradaTeclado);
                actorRepositorio.encontrarPorId(id).ifPresent(pelicula::setActor);
                System.out.println("Agregado el actor con id " + id + " al reparto de la pelicula " + pelicula.getTitulo());
            } catch (NumberFormatException e) {
                System.out.println("Introduce el id del actor");
            }
        }
    }
//    ## Esta parte al ser una relacion 1-1 no tiene sentido cada premio debe crearse para cada pelicula, solo 1 premio por año ##
//    private static Premio obtenerPremio(PremioRepositorio premioRepositorio, String tituloPelicula) {
//        Premio premio = new Premio();
//
//        System.out.println("Introduce el premio de la pelicula");
//        try {
//            List<Premio> premios = premioRepositorio.encontrarTodos();
//            premios.forEach(System.out::println);
//            String entradaTeclado = sc.nextLine();
//            int id = Integer.parseInt(entradaTeclado);
//            if (premioRepositorio.encontrarPorId(id).isPresent())
//                premio = premioRepositorio.encontrarPorId(id).get();
//            System.out.println("Agregado el premio con id " + id + " a la pelicula " + tituloPelicula);
//        } catch (NumberFormatException e) {
//            System.out.println("Introduce el id del premio");
//        }
//
//        return premio;
//    }

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
