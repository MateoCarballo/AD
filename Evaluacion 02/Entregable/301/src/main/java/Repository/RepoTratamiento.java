package Repository;

import Entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;

public class RepoTratamiento   {

    private Session session;

    public RepoTratamiento(Session s){
        this.session = s;
    }

    public void asignarFechaTratamiento(String nombreTratamiento, String nombrePaciente, LocalDate fechaInicio, LocalDate fechaFin){
        Paciente paciente = null;
        Tratamiento tratamiento = null;
        RecibePK claveCompuesta = null;

        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();
            // 1. Obtener id paciente
            Query<Paciente> queryIdPaciente = session.createQuery("FROM Paciente WHERE nombre = :nombre", Paciente.class);
            queryIdPaciente.setParameter("nombre",nombrePaciente);
            paciente = queryIdPaciente.getSingleResult();

            // 2. Obtener id tratamiento
            Query<Tratamiento> queryIdTratamiento = session.createQuery("FROM Tratamiento WHERE tipo = :tipo", Tratamiento.class);
            queryIdTratamiento.setParameter("tipo",nombreTratamiento);
            tratamiento = queryIdTratamiento.getSingleResult();

            // 3. Crear clave compuesta
            if ((paciente != null) && (tratamiento != null) && (claveCompuesta == null)){
                 claveCompuesta = new RecibePK(paciente.getId(),tratamiento.getId(),fechaInicio);
            }

            // 4. Crear el objeto recibe
            Recibe recibe = new Recibe(claveCompuesta,paciente,tratamiento,fechaFin);

            // 5. Escribir cambios y hacer commit
            session.save(recibe);
            transaction.commit();

        }catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }



    public void modificarHospital(int idTratamiento, String nombreNuevoHospital){
        Transaction transaction = null;
        Hospital nuevoHospital = null;
        Tratamiento tratamiento = null;
        try{
            transaction = session.beginTransaction();

            //Obtengo el objeto hospital asociado al nombre que recibo
            Query<Hospital> queryHospital = session.createQuery("FROM Hospital WHERE nombre = :nombreHospital", Hospital.class);
            queryHospital.setParameter("nombreHospital",nombreNuevoHospital);
            nuevoHospital = queryHospital.getSingleResult();

            //Traigo el objeto asociado al idTratamiento
            Query<Tratamiento> queryTratamiento = session.createQuery("FROM Tratamiento WHERE id = :idTratamiento", Tratamiento.class);
            queryTratamiento.setParameter("idTratamiento",idTratamiento);
            tratamiento = queryTratamiento.getSingleResult();

            //Cargo el nuevo hospital del tratamiento
            tratamiento.setHospital(nuevoHospital);

            //Inserto el tratamiento actualizado y comiteo
            transaction.commit();

        }catch(Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
