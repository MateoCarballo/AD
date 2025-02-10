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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RepoPaciente {
    private Session session;
    private Transaction transaction;

    private ArrayList<Paciente> listaPacientes;

    public RepoPaciente (Session session) {
        this.session = session;
        this.transaction = null;
        this.listaPacientes = new ArrayList();
    }

    public void Crear(int id, String nombrePaciente, LocalDate fechaNacimiento, String direccion){
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        try{
            transaction = session.beginTransaction();

            Query<Paciente> buscarPaciente = session.createQuery("FROM Paciente WHERE nombre = :nombrePaciente", Paciente.class);
            buscarPaciente.setParameter("nombrePaciente",nombrePaciente);

            //Traemos la lista desde la DB mediante la consulta
            List<Paciente> resultadosConsulta = buscarPaciente.getResultList();
            // Vaciamos la lista del repositorio
            listaPacientes.clear();
            //Guardamos el resultado de la consulta, que estaba en la lista, en nuestro hashset
            listaPacientes.addAll(resultadosConsulta);

            //Mostramos todos los pacientes con el nombre si hay mas de uno
            if(listaPacientes.size() > 1){
                for(Paciente p: listaPacientes){
                    System.out.println(p);
                }
                // Pedimos una id de las mostradas para borrar
                int entradaTeclado = Integer.parseInt(br.readLine());
                //do {
                    for(Paciente p: listaPacientes){
                        if (p.getId() == entradaTeclado){
                            Query<Cita> buscarCita = session.createQuery("FROM Cita WHERE paciente.id = :idPaciente", Cita.class);
                            buscarCita.setParameter("idPaciente", p.getId());
                            Cita cita = buscarCita.getSingleResult();
                            session.remove(cita);

                            Query<Recibe> buscarRecibe = session.createQuery("FROM Recibe WHERE paciente.id = :idPaciente", Recibe.class);
                            buscarCita.setParameter("idPaciente", p.getId());
                            Recibe recibe = buscarRecibe.getSingleResult();
                            session.remove(recibe);

                            //Si coincide la mostrada comenzamos el proceso de borrado
                            session.remove(p);
                            transaction.commit();
                        }
                    }

               // }while (true); //TODO esto asi NO!

            }else{ // TODO PROVISIONAL
                Query<Paciente> buscarPacienteUnico = session.createQuery("FROM Paciente WHERE nombre = :nombrePaciente", Paciente.class);
                buscarPacienteUnico.setParameter("nombrePaciente",nombrePaciente);

                //Traemos la lista desde la DB mediante la consulta
                Paciente pacienteUnico = buscarPacienteUnico.uniqueResult();

                borrarCitaAsociada(pacienteUnico);
                borrarRecibeAsociado(pacienteUnico);

                //Si coincide la mostrada comenzamos el proceso de borrado
                session.remove(pacienteUnico);
                transaction.commit();
            }

        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private void borrarRecibeAsociado(Paciente p) {
        // TODO cambiado a lista para ver si arregalba pero nada
        Query<Recibe> buscarRecibe = session.createQuery("FROM Recibe WHERE paciente.id = :idPaciente", Recibe.class);
        buscarRecibe.setParameter("idPaciente", p.getId());
        ArrayList <Recibe> recibes= new ArrayList<>();
        recibes = (ArrayList<Recibe>) buscarRecibe.getResultList();
        if (recibes.size() != 0) session.remove(recibes.get(0));
    }

    private void borrarCitaAsociada(Paciente p) {
        Query<Cita> buscarCita = session.createQuery("FROM Cita WHERE paciente.id = :idPaciente", Cita.class);
        buscarCita.setParameter("idPaciente", p.getId());
        ArrayList <Cita> citas= new ArrayList<>();
        citas = (ArrayList<Cita>) buscarCita.getResultList();
        if (citas.size() != 0) session.remove(citas.get(0));
    }
}
