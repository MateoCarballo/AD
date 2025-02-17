package Repository;

import Entity.Departamento;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DepartamentoRepository implements Repository <Departamento> {

    private Session session;

    public DepartamentoRepository(Session session){
        this.session = session;
    }
    @Override
    public void guardar(Departamento departamento) {
        Transaction transaction = session.beginTransaction();
        session.persist(departamento);
        transaction.commit();
    }

    @Override
    public List<Departamento> encontrarTodos() {
        return List.of();
    }

    @Override
    public Departamento encontrarUnoPorId(long id) {
        return null;
    }

    @Override
    public void actualizar(Departamento departamento) {

    }

    @Override
    public void eliminar(Departamento departamento) {

    }
}
