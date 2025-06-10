package org.example.repository;

import org.example.entity.Pelicula;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        Transaction trx = null;
        List<Pelicula> listadoPelis = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Pelicula> todasPeliculas = session.createQuery("FROM Pelicula p");
            listadoPelis = todasPeliculas.getResultList();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al buscar todos las peliculas");
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
            trx.rollback();
            System.out.println("Error al buscar por Id");
        }
        return pelicula;
    }

    @Override
    public void actualizar(Pelicula pelicula) {

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
}
