package org.example.repository;

import org.example.dto.Consulta2DTO;
import org.example.entity.Pelicula;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PeliculaRepositorio implements Repositorio<Pelicula, Integer> {
    private final Session session;

    public PeliculaRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Pelicula pelicula) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.persist(pelicula);
            trx.commit();
            System.out.println("La pelicula " + pelicula.getTitulo() + " se ha añadido correctamente");
        } catch (Exception e) {
            if (trx != null) {
                trx.rollback();
                System.out.println("Ha ocurrido un error al guardar la pelicula");
            }
        }
    }

    @Override
    public List<Pelicula> encontrarTodos() {
        List<Pelicula> listadoPelis = new ArrayList<>();
        try {
            listadoPelis = session.createQuery("FROM Pelicula p",Pelicula.class).getResultList();;
        } catch (Exception e) {
            System.out.println("Error al buscar todos las peliculas\n" + e .getMessage());
        }
        return listadoPelis;
    }

    @Override
    public Optional<Pelicula> encontrarPorId(Integer integer) {
        Transaction trx = null;
        Optional pelicula = Optional.empty();
        try {
            pelicula = session.createQuery("SELECT p FROM Pelicula p WHERE id = :id",Pelicula.class)
                    .setParameter("id", integer)
                    .uniqueResultOptional();
        } catch (Exception e) {
            System.out.println("Error al buscar por Id");
        }
        return pelicula;
    }

    @Override
    public void actualizar(Pelicula pelicula) {
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            session.merge(pelicula);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error en el metodo actualizar del repo de peliculas" + e.getMessage());
        }
    }

    @Override
    public void eliminar(Pelicula pelicula) {
        Transaction trx = null;
        try {
            String nombrePelicula = pelicula.getTitulo();
            session.remove(pelicula);
            trx.commit();
            System.out.println("Se ha eliminado la pelicula " + nombrePelicula);
        } catch (Exception e) {
            if (trx != null) trx.rollback();
        }
    }

    public List<Consulta2DTO> consulta2(String titulo) {
        List<Consulta2DTO> listaResultadosConsutla2 = new ArrayList<>();
        try{
            listaResultadosConsutla2 = session.createQuery("""
                                SELECT new  org.example.dto.Consulta2DTO(a.nombre, a.nacionalidad)
                                FROM Pelicula p
                                JOIN p.actores a
                                WHERE p.titulo = :titulo
                                """,Consulta2DTO.class)
                    .setParameter("titulo",titulo)
                    .getResultList();

        }catch (Exception e){
            System.out.println("Error en el metood devolver id por titulo del repositorio de pelicula");
            e.printStackTrace();
        }
        return listaResultadosConsutla2;
    }
}
