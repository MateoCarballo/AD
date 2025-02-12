package Repository;


import Entity.Cita;
import Entity.Doctor;
import Entity.Paciente;
import org.hibernate.Transaction;
import org.hibernate.Session;

import java.time.LocalDate;

public class RepoCita  {
    private Transaction transaction;
    private Session session;

    public RepoCita(Session session) {
        this.session = session;
    }

    public String crearCita(Paciente paciente, Doctor doctor){
        String retorno = "No se ha completado la operacion revisar datos";
        LocalDate fechaCita = LocalDate.now().plusDays(7);
        //Crear una nueva cita para paciente con el doctor
        Cita cita = Cita.builder()
                .fecha(fechaCita)
                .estado("Activa")
                .build();

        //Setea el doctor en la cita y le dice al doctor que su cita es ella misma
        cita.setDoctor(doctor);

        //Añadimos la cita a la lista de citas del paciente y a la misma cita
        //le decimos que su paciente es el que tiene la lista en la que la añado
        //cita.setPaciente(paciente);
        paciente.addCita(cita);

        try{
            transaction = session.beginTransaction();
            session.persist(cita);
            transaction.commit();
            retorno = "Operacion completada";
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return retorno;
    }
}
