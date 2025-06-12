package org.example.repository;

import org.example.entity.Sala;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class SalaRepositorio implements Repositorio<Sala, Integer>{
    private Session session;

    public SalaRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Sala sala) {
        Transaction trx = null;
        try {
            trx = session.beginTransaction();
            session.persist(sala);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error en guardar del repositorio sala");
        }
    }

    @Override
    public List<Sala> encontrarTodos() {
        List listadoSalas = new ArrayList<>();
        try{
            listadoSalas = session.createQuery("SELECT s FROM Sala s",Sala.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error en encontrarTodos en el repositorio salas");
            e.printStackTrace();
        }
        return listadoSalas;
    }

    @Override
    public Optional<Sala> encontrarPorId(Integer integer) {
        Optional<Sala> sala = Optional.empty();
        try{
            sala = session.createQuery("SELECT s FROM Sala s WHERE id = :id", Sala.class)
                    .setParameter("id",integer)
                    .uniqueResultOptional();
        }catch (Exception e){
            System.out.println("Error en encontrarPorId en el repositorio salas");
            e.printStackTrace();
        }
        return sala;
    }

    @Override
    public void actualizar(Sala sala) {

    }

    @Override
    public void eliminar(Sala sala) {

    }
}
