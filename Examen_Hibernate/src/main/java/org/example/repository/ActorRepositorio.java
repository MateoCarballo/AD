package org.example.repository;

import org.example.entity.Actor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorRepositorio implements Repositorio<Actor, Integer>{

    private final Session session;

    public ActorRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Actor actor) {
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            session.persist(actor);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al insertar un nuevo actor");
        }
    }

    @Override
    public List<Actor> encontrarTodos() {
        List<Actor> listadoActores = new ArrayList<>();
        Transaction trx = null;
        try{
            trx = this.session.beginTransaction();
            Query<Actor> query = session.createQuery("SELECT a FROM Actor a", Actor.class);
            listadoActores = query.getResultList();
            trx.commit();
            System.out.println("Consulta realizada con exito");
        }catch (Exception e){
            if (trx != null) trx.rollback();
            System.out.println("Ha ocurrido un error durante la consulta");
        }
        return listadoActores;
    }

    @Override
    public Optional<Actor> encontrarPorId(Integer id) {
        Optional<Actor> actor = Optional.empty();
        Transaction trx = null;
        try{
            actor = session.createQuery("SELECT a FROM Actor a WHERE id = :id", Actor.class)
                    .setParameter("id", id)
                    .uniqueResultOptional();
            // Forma resumida para este caso concreto
            // Optional<Actor> actor1 = Optional.ofNullable(session.find(Actor.class,id));
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al buscar por id un actor");
        }
        return actor;
    }

    @Override
    public void actualizar(Actor actor) {
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            int idActor = actor.getId();
            Optional<Actor> buscarActor = encontrarPorId(idActor);
            if (buscarActor.isPresent()){
                session.update(actor);
                trx.commit();
            }
            System.out.println("Eliminado actor con id " + idActor);
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al actualizar el actor");
        }
    }

    @Override
    public void eliminar(Actor actor) {
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            int idActor = actor.getId();
            Optional<Actor> buscarActor = encontrarPorId(idActor);
            if (buscarActor.isPresent()){
                //DELETE FROM Actor a WHERE a.id = :id
                session.remove(actor);
                trx.commit();
            }
            System.out.println("Eliminado actor con id " + idActor);
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al eliminar el actor");
        }
    }
}
