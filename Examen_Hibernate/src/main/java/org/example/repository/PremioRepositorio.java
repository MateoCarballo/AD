package org.example.repository;

import org.example.entity.Pelicula;
import org.example.entity.Premio;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PremioRepositorio implements Repositorio<Premio,Integer>{

    private Session session;

    public PremioRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Premio premio) {
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            session.persist(premio);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al guardar el premio");
        }
    }

    @Override
    public List<Premio> encontrarTodos() {
        Transaction trx = null;
        List<Premio> lisadoPremios = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Premio> todosPremios = session.createQuery("FROM Premio p");
            lisadoPremios = todosPremios.getResultList();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al buscar todos las premios");
        }
        return lisadoPremios;
    }

    @Override
    public Optional<Premio> encontrarPorId(Integer integer) {
        Transaction trx = null;
        Optional premio = Optional.empty();
        try {
            premio = session.createQuery("SELECT p FROM Premio p WHERE id = :id",Premio.class)
                    .setParameter("id", integer)
                    .uniqueResultOptional();
        } catch (Exception e) {
            trx.rollback();
            System.out.println("Error al buscar por Id");
        }
        return premio;
    }

    @Override
    public void actualizar(Premio premio) {

    }

    @Override
    public void eliminar(Premio premio) {

    }
}
