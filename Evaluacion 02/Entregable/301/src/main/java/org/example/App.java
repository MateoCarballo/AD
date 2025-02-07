package org.example;

import Entity.Doctor;
import Entity.Paciente;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        crearDoctor(session);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");

    }

    private static void crearDoctor(Session session) {
        Transaction transaction = session.beginTransaction();

        try {
            Doctor nuevoDoctor = Doctor.builder()
                    .id(101)
                    .nombre("Nombre Doctor")
                    .especialidad("Especialidad")
                    .telefono("telefono").build();

            session.save(nuevoDoctor);

            transaction.commit();
            System.out.println("Doctor creado correctamente");
        } catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
