package org.example;

import org.example.entity.*;
import org.example.repository.*;
import org.hibernate.Session;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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
            SalaRepositorio salaRepositorio = new SalaRepositorio(session);
            ProyeccionRepositorio proyectaRespositorio = new ProyeccionRepositorio(session);

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
                    case 8 -> asignarPremioAPelicula(peliculaRepositorioImpl, premioRepositorioImpl);
                    case 9 -> asignarActorAPelicula(actorRepositorioImpl, peliculaRepositorioImpl);
                    case 10 -> asignarPeliculaSalaFechaHora(peliculaRepositorioImpl, salaRepositorio, proyectaRespositorio);
                    case 11 -> realizarConsulta1(actorRepositorioImpl);
                }
            } while (opcionSeleccionada != 15);
            System.out.println("Conexión a Hibernate exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void realizarConsulta1(ActorRepositorio actorRepositorioImpl) {
        System.out.println("Escribe la nacionalidad para filtrar");
        String nacionalidad = sc.nextLine();
        actorRepositorioImpl.encontrarActoresNacionalidad(nacionalidad).forEach(System.out::println);
    }

    private static void asignarPeliculaSalaFechaHora(PeliculaRepositorio peliculaRepositorioImpl, SalaRepositorio salaRepositorio, ProyeccionRepositorio proyeccionRepositorio) {
        //Escoger una pelicula por id
        peliculaRepositorioImpl.encontrarTodos().forEach(System.out::println);
        System.out.println("Escoge una pelicula por id");
        int idPelicula = Integer.parseInt(sc.nextLine());
        Optional<Pelicula> peliculaOpt = peliculaRepositorioImpl.encontrarPorId(idPelicula);
        if (peliculaOpt.isEmpty()){
            System.out.println("El id pelicula introducido no existe en la base de datos");
            return;
            //Como dejo de realizar codigo desde aqui ?
        }

        //Escoger una sala por id
        salaRepositorio.encontrarTodos().forEach(System.out::println);
        System.out.println("Escoge una sala por id");
        int idSala = Integer.parseInt(sc.nextLine());

        Optional<Sala> salaOpt = salaRepositorio.encontrarPorId(idSala);
        if(salaOpt.isEmpty()){
            System.out.println("El id sala introducido no existe en la base de datos");
            return;
        }

        Proyeccion nuevaProyeccion = new Proyeccion(
                peliculaOpt.get(),
                salaOpt.get(),
                LocalDate.now(),
                LocalTime.now()
        );


//        ProyeccionPK proyeccionPK = ProyeccionPK.builder()
//                .peliculaId(peliculaOpt.get().getId())
//                .salaId(salaOpt.get().getId())
//                .fecha(LocalDate.now())
//                .horario(LocalTime.now())
//                .build();
//
//        Proyeccion nuevaProyeccion = Proyeccion.builder()
//                .pelicula(peliculaOpt.get())
//                .sala(salaOpt.get())
//                .proyeccionId(proyeccionPK)
//                .build();
//

        proyeccionRepositorio.guardar(nuevaProyeccion);
    }

    private static void asignarActorAPelicula(ActorRepositorio actorRepositorioImpl, PeliculaRepositorio peliculaRepositorioImpl) {
        peliculaRepositorioImpl.encontrarTodos().forEach(System.out::println);
        System.out.println("Introduce el id de la pelicula");
        int idPelicula = Integer.parseInt(sc.nextLine());

        actorRepositorioImpl.encontrarTodos().forEach(System.out::println);
        System.out.println("Introduce el id del actor");
        int idActor = Integer.parseInt(sc.nextLine());

        actorRepositorioImpl.encontrarPorId(idActor).ifPresentOrElse(
                actor -> {
                    peliculaRepositorioImpl.encontrarPorId(idPelicula).ifPresentOrElse(
                            pelicula -> {
                                pelicula.addActor(actor);
                                peliculaRepositorioImpl.actualizar(pelicula);
                                System.out.println("Se han vinculado las pelicula " + pelicula.getTitulo() + " y " + " el actor " + actor.getNombre());
                            },
                            () -> System.out.println("No existe ninguna pelicula con este id")
                    );
                },
                () -> System.out.println("No existe ningun actor con este id")
        );
    }

    private static void asignarPremioAPelicula(PeliculaRepositorio peliculaRepositorioImpl, PremioRepositorio premioRepositorioImpl) {
        peliculaRepositorioImpl.encontrarTodos().forEach(System.out::println);
        System.out.println("Introduce el id de la pelicula");
        int idPelicula = Integer.parseInt(sc.nextLine());

        premioRepositorioImpl.encontrarTodos().forEach(System.out::println);
        System.out.println("Introduce el id del premio");
        int idPremio = Integer.parseInt(sc.nextLine());

        premioRepositorioImpl.encontrarPorId(idPremio).ifPresentOrElse(
                premio -> {
                    peliculaRepositorioImpl.encontrarPorId(idPelicula).ifPresentOrElse(
                            pelicula -> {
                                pelicula.setPremio(premio);
                                System.out.println("Se han vinculado las pelicula " + pelicula.getTitulo() + " y \n" + " el premio " + premio.getNombre());
                            },
                            () -> System.out.println("No existe ninguna pelicula con este id")
                    );
                },
                () -> System.out.println("No existe ningun premio con este id")
        );
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
                actorRepositorio.encontrarPorId(id).ifPresent(pelicula::addActor);
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
