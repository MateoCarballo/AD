package Ej_301.repositorio;

import Ej_301.entidades.Dpto;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DptoRepositorio implements Repositorio<Dpto>{
    private Session session;
    public DptoRepositorio (Session session){
        super();
        this.session = session;
    }
    @Override
    public void guardar(Dpto dpto) {
        Transaction trx = this.session.beginTransaction();
        session.persist(dpto);
        trx.commit();
    }
}
