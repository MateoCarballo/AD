package Repository;

import Entity.Hospital;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RepoHospital {

    private Session session;

    public RepoHospital(Session session) {
        this.session = session;
    }

    public Hospital mostrarTratamientos(String nombreHospital) {
        Hospital hospital = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query queryHospital = session.createQuery("FROM Hospital WHERE nombre = :nombreHospital", Hospital.class);
            queryHospital.setParameter("nombreHospital",nombreHospital);
            hospital = (Hospital) queryHospital.getSingleResult();
            hospital.getTratamientos();

        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return hospital;
    }

    public List<Hospital> mostratTodosTratamientosTodosHospitales() {
        Transaction transaction = null;
        List<Hospital> listadoHospitales = null;
        try{
            transaction = session.beginTransaction();
            Query<Hospital> listaHospitales = session.createQuery("FROM Hospital" ,Hospital.class);
            listadoHospitales = listaHospitales.getResultList();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
        return listadoHospitales;
    }
/*




public List<Tratamiento> mostrarTratamientos(String nombreHospital) {
    Hospital hospital = null;
    Transaction transaction = null;
    List tratamientos = null;
    try{
        transaction = session.beginTransaction();
        Query<Tratamiento> queryTratamiento = session.createQuery("FROM Tratamiento WHERE hospital.nombre = :nombreHospital", Tratamiento.class);
        queryTratamiento.setParameter("nombreHospital",nombreHospital);
        tratamientos = queryTratamiento.getResultList();

    }catch (Exception e){
        if (transaction != null) transaction.rollback();
        e.printStackTrace();
    }
    return tratamientos;
    }
 */
}
