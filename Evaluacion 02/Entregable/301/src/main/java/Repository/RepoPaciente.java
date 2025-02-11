package Repository;

import Entity.Cita;
import Entity.Paciente;
import Entity.Recibe;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RepoPaciente {
    private Session session;

    private ArrayList<Paciente> listaPacientes;

    public RepoPaciente (Session session) {
        this.session = session;
        this.listaPacientes = new ArrayList();
    }

    public void Crear(int id, String nombrePaciente, LocalDate fechaNacimiento, String direccion){
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Paciente paciente = Paciente.builder()
                    .id(id)
                    .nombre(nombrePaciente)
                    .fechaNacimiento(fechaNacimiento)
                    .direccion(direccion)
                    .build();

            session.persist(paciente);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void borrar(String nombrePaciente){
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
                // TODO PROVISIONAL PENDIENTE DE GESTIONAR QUE HAYA DOS ENTRADAS CON MISMO NOMBRE Y DISTINTO id

                Query<Paciente> queryPaciente = session.createQuery("FROM Paciente WHERE nombre = :nombrePaciente", Paciente.class);
                queryPaciente.setParameter("nombrePaciente",nombrePaciente);

                //Traemos la lista desde la DB mediante la consulta
                Paciente pacienteUnico = queryPaciente.uniqueResult();

                //borrarCitaAsociada(pacienteUnico);
                borrarRecibeAsociado(pacienteUnico);

                //Si coincide la mostrada comenzamos el proceso de borrado
                session.remove(pacienteUnico);
                transaction.commit();

        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void modificarPaciente(int idPaciente, String nombrePaciente, LocalDate fechaNacimiento, String direccion){
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query <Paciente> pacienteQuery = session.createQuery("FROM Paciente WHERE id = : idPaciente",Paciente.class);
            pacienteQuery.setParameter("idPaciente", idPaciente);
            Paciente pacienteModificado = pacienteQuery.uniqueResult();
            pacienteModificado.setNombre(nombrePaciente);
            pacienteModificado.setFechaNacimiento(fechaNacimiento);
            pacienteModificado.setDireccion(direccion);

            //session.persist(pacienteModificado);
            session.save(pacienteModificado);
            transaction.commit();

        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    private void borrarRecibeAsociado(Paciente p) {
        Transaction transaction = null;
        try{
            // TODO cambiado a lista para ver si arregalba pero nada
            Query<Recibe> buscarRecibe = session.createQuery("FROM Recibe WHERE paciente.id = :idPaciente", Recibe.class);
            buscarRecibe.setParameter("idPaciente", p.getId());
            ArrayList <Recibe> recibes= (ArrayList<Recibe>) buscarRecibe.getResultList();
            for (Recibe r:recibes){
                session.remove(r);

            }
        }catch(Exception e){
            if (transaction != null) transaction.rollback();

        }

    }

    private void borrarCitaAsociada(Paciente paciente) {
        Transaction transaction = null;
        try{
            Query<Cita> buscarCita = session.createQuery("FROM Cita WHERE paciente.id = :idPaciente", Cita.class);
            buscarCita.setParameter("idPaciente", paciente.getId());
            ArrayList <Cita> citas = (ArrayList<Cita>) buscarCita.getResultList();
            for (Cita c:citas){
                session.remove(c);
            }
        }catch(Exception e){
            if (transaction != null) transaction.rollback();
        }


    }

    /*
    public int buscarPaciente(String nombrePaciente){
        //Buscamos si existe algun doctor con ese nombre
        Query<Integer> pacienteQuery = session.createQuery("SELECT id FROM Paciente WHERE nombre = :nombre");
        pacienteQuery.setParameter("nombre",nombrePaciente);
        int intPaciente = pacienteQuery.getSingleResult();

        //devolvemos el doctor
        return intPaciente;
    }
     */

    public Paciente buscarPaciente(String nombrePaciente){
        Transaction transaction = null;
        Paciente paciente = null;
        try{
            transaction = session.beginTransaction();
            //Buscamos si existe algun paciente con ese nombre
            Query<Paciente> pacienteQuery = session.createQuery("FROM Paciente WHERE nombre = :nombre", Paciente.class);
            pacienteQuery.setParameter("nombre",nombrePaciente);
            paciente= pacienteQuery.getSingleResult();
            //devolvemos el paciente

        }catch (Exception e){

        }
        return paciente;
    }

    public Paciente mostrarTodosDatos(String nombrePaciente) {
        Paciente paciente = null;
        try{
            Query<Paciente> queryPaciente = session.createQuery(
                    "FROM Paciente WHERE nombre = :nombrePaciente", Paciente.class);
            queryPaciente.setParameter("nombrePaciente", nombrePaciente);

            // Usamos getResultList() para evitar excepciones si no se encuentra
            List<Paciente> resultados = queryPaciente.getResultList();

            // Si hay al menos un paciente, devolvemos el primero; si no, retornamos null
            return resultados.isEmpty() ? null : resultados.get(0);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
