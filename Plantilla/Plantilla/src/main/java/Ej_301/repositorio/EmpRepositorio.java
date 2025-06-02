package Ej_301.repositorio;

import Ej_301.entidades.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmpRepositorio implements Repositorio<Emp> {
    private Session session;
    public EmpRepositorio(Session session){
        super();
        this.session = session;
    }

    @Override
    public void guardar(Emp emp) {
        Transaction trx = this.session.beginTransaction();
        session.persist(emp);
        trx.commit();
    }
}
