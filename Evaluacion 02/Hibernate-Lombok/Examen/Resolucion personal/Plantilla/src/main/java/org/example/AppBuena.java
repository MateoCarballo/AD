package org.example;


import org.example.Entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppBuena {
    private final Session session;
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public AppBuena(Session session) {
        this.session = session;
    }
    
    public void comenzarApp()  {
        lanzarMenu();
        try{
            String entradaTeclado = br.readLine();
            switch (Integer.parseInt(entradaTeclado)){
                case 1:{
                    crearActor();
                }
                case 2:{
                    eliminarActor();
                }
                case 3:{
                    crearPelicula();
                }
                case 4:{
                    eliminarPelicula();
                }
                case 5:{
                    modificarGeneroPelicula(1,"genero");
                }
                case 6:{
                    asignarPremioPelicula(1,1);
                }

                case 7:{
                    asignarActorPelicula(1,2);
                }
                case 8:{
                    asignarPeliculaSala(3,1, LocalDate.now(),LocalTime.now());
                }
                case 9:{
                    consulta1("Estadounidense");
                }
                case 10:{
                    consulta2("Inception");
                }
                case 11:{
                    consulta3("Inception");
                }

            }
                
            
        }catch (IOException e){
            
        }
    }

    private void consulta3(LocalDate fechaProyeccion) {
        //TODO
        Transaction transaction = null;
        List<Proyecta> resultadoProyecta = session.createQuery("FROM peliculas WHERE nombre = : ", Pelicula.class)
                .setParameter("nombre",nombrePelicula)
                .getResultList();
        if (!resultado.isEmpty()){
            try{
                transaction = session.beginTransaction();
                List<Actor> actores = resultado.get(0).getListadoActores();
                for (Actor a :actores){
                    System.out.println("Nombre :" + a.getNombre() + "Nacionalidad :" + a.getNacionalidad());
                }
                transaction.commit();
            }catch (Exception e){
                if (transaction != null) transaction.rollback();
            }
        }
    }

    private void consulta2(String nombrePelicula) {
        Transaction transaction = null;
        List<Pelicula> resultado = session.createQuery("FROM peliculas WHERE nombre = : ", Pelicula.class)
                .setParameter("nombre",nombrePelicula)
                .getResultList();
        if (!resultado.isEmpty()){
            try{
                transaction = session.beginTransaction();
                List<Actor> actores = resultado.get(0).getListadoActores();
                for (Actor a :actores){
                    System.out.println("Nombre :" + a.getNombre() + "Nacionalidad :" + a.getNacionalidad());
                }
                transaction.commit();
            }catch (Exception e){
                if (transaction != null) transaction.rollback();
            }
        }

    }

    private void consulta1(String nacionalidad) {
        nacionalidad = "Estadounidense";
        Transaction transaction = null;
        List<Actor> resultado = session.createQuery("FROM Actor WHERE nacionalidad = : nac ORDER BY fecha_nacimiento DESC", Actor.class)
                .setParameter("id",nacionalidad)
                .getResultList();
        if (!resultado.isEmpty()){
            try{
                transaction = session.beginTransaction();
                for (Actor a :resultado){
                    System.out.println(a.getNombre());
                }
                transaction.commit();
            }catch (Exception e){
                if (transaction != null) transaction.rollback();
            }
        }

    }

    private void asignarPeliculaSala(int idPelicula, int idSala, LocalDate fecha, LocalTime hora) {
        fecha = LocalDate.now().plusDays(7);
        hora = LocalTime.now().minusHours(1);
        Transaction transaction = null;
        List<Pelicula> resultadoPeliculas = session.createQuery("FROM Peliculas WHERE id_pelicula = :id", Pelicula.class)
                .setParameter("id",idPelicula)
                .getResultList();
        List<Sala> resultadoSala = session.createQuery("FROM Sala WHERE id_Sala = :id", Sala.class)
                .setParameter("id",idSala)
                .getResultList();
        if (!resultadoPeliculas.isEmpty() && !resultadoSala.isEmpty()){
            try{
                transaction = session.beginTransaction();
                ProyectaPK claveCompuesta = ProyectaPK.builder()
                        .idPelicula(idPelicula)
                        .idSala(idSala)
                        .fecha(fecha)
                        .horario(hora)
                        .build();
                Proyecta proyeccion = Proyecta.builder()
                        .sala(resultadoSala.get(0))
                        .pelicula(resultadoPeliculas.get(0))
                        .idCompuesta(claveCompuesta)
                        .build();
                resultadoPeliculas.get(0).addProyeccion(proyeccion);
                resultadoSala.get(0).addProyeccion(proyeccion);
                session.persist(proyeccion);
                transaction.commit();
            }catch (Exception e){
                if (transaction != null) transaction.rollback();
            }
        }


    }

    private void asignarPremioPelicula(int idPremio, int idPelicula){

        Transaction transaction = null;
        List<Pelicula> resultadoPeliculas = session.createQuery("FROM Peliculas WHERE id_pelicula = :id", Pelicula.class)
                .setParameter("id",idPelicula)
                .getResultList();
        List<Premio> resultadoPremios = session.createQuery("FROM Peliculas WHERE id_premio = :id", Premio.class)
                .setParameter("id",idPremio)
                .getResultList();
        if (!resultadoPeliculas.isEmpty() && !resultadoPremios.isEmpty()){
            try{
                transaction = session.beginTransaction();
                Pelicula pelicula = resultadoPeliculas.get(0);
                pelicula.setPremio(resultadoPremios.get(0));
                transaction.commit();
            }catch (Exception e){
                if (transaction != null) transaction.rollback();
            }
        }

    }

    private void modificarGeneroPelicula(int idPelicula,String genero) {
        //simulacion
        idPelicula = 1;
        Transaction transaction = null;
        List<Pelicula> resultado = session.createQuery("FROM Peliculas WHERE id_pelicula = :id", Pelicula.class)
                .setParameter("id",idPelicula)
                .getResultList();
        if (!resultado.isEmpty()){
           try{
               transaction = session.beginTransaction();
               Pelicula pelicula = resultado.get(0);
               pelicula.setGenero(genero);
               transaction.commit();
            }catch (Exception e){
               if (transaction != null) transaction.rollback();
           }
        }

    }

    private void asignarActorPelicula(int idPelicula,int idActor) {
        //simulacion
        Transaction transaction = null;
        List<Pelicula> resultadoPeliculas = session.createQuery("FROM Peliculas WHERE id_pelicula = :id", Pelicula.class)
                .setParameter("id",idPelicula)
                .getResultList();
        List<Actor> resultadoActores = session.createQuery("FROM Actor WHERE id_actor = :id", Actor.class)
                .setParameter("id",idActor)
                .getResultList();
        if (!resultadoPeliculas.isEmpty() && !resultadoActores.isEmpty()){
            try{
                transaction = session.beginTransaction();
                resultadoActores.get(0).addPelicula(resultadoPeliculas.get(0));
                transaction.commit();
            }catch (Exception e){
                if (transaction != null) transaction.rollback();
            }
        }

    }



    private void crearActor() {
        Transaction transaction = null;
        Actor actor = Actor.builder()
                .fechaNacimiento(LocalDate.of(2020,10,01))
                .nacionalidad("nacionalidad")
                .nombre("nombre")
                .build();
        
        try{
            transaction = session.beginTransaction();
            session.persist(actor);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null)transaction.rollback();
        }
    }

    private void eliminarActor() {
        Transaction transaction = null;
        
        //Encontrado por id en la db y previas comprobaciones
        Actor actor = Actor.builder()
                .fechaNacimiento(LocalDate.of(2020,10,01))
                .nacionalidad("nacionalidad")
                .nombre("nombre")
                .build();

        try{
            transaction = session.beginTransaction();
            
            session.createQuery("DELETE FROM actores WHERE id_actor = :id")
                    .setParameter("id",actor.getId())
                    .executeUpdate();
            
            transaction.commit();
        }catch (Exception e){
            if (transaction != null)transaction.rollback();
        }
    }

    private void crearPelicula() {
        Transaction transaction = null;
        Pelicula pelicula = Pelicula.builder()
                .anhoEstreno(2020)
                .genero("Genero")
                .titulo("titulo")
                .build();

        try{
            transaction = session.beginTransaction();
            session.persist(pelicula);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null)transaction.rollback();
        }
    }

    private void eliminarPelicula() {
        Transaction transaction = null;
        Pelicula pelicula = Pelicula.builder()
                .anhoEstreno(2020)
                .genero("Genero")
                .titulo("titulo")
                .build();

        try{
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM pelicula WHERE id_pelicula = :id")
                    .setParameter("id",pelicula.getId())
                    .executeUpdate();

            transaction.commit();
        }catch (Exception e){
            if (transaction != null)transaction.rollback();
        }
    }

    private void lanzarMenu(){
        System.out.println( ("1. Crear actor\n" +
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
                "15. Salir"));
    }
}
