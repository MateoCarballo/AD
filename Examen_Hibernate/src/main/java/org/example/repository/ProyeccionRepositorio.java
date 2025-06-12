package org.example.repository;

import org.example.entity.ProyeccionPK;
import org.example.entity.Proyeccion;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class ProyeccionRepositorio implements Repositorio< Proyeccion, ProyeccionPK> {
    private Session session;

    public ProyeccionRepositorio(Session session) {
        this.session = session;
    }

    @Override
    public void guardar(Proyeccion proyecccion) {
        Transaction trx = null;
        try{
            trx = session.beginTransaction();
            session.persist(proyecccion);
            trx.commit();
        } catch (Exception e) {
            if (trx != null) trx.rollback();
            System.out.println("Error al guardar en el repositorio de proyeccion");
        }
    }

    @Override
    public List<Proyeccion> encontrarTodos() {
        return List.of();
    }

    @Override
    public Optional<Proyeccion> encontrarPorId(ProyeccionPK proyeccionPK) {
        return Optional.empty();
    }

    @Override
    public void actualizar(Proyeccion proyecccion) {

    }

    @Override
    public void eliminar(Proyeccion proyecccion) {

    }
}
