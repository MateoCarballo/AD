package Repository;

import Entity.Doctor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.print.Doc;

public class RepoDoctor {
   private Session session;

   public RepoDoctor(Session session) {
      this.session = session;
   }

   public void crear(int id,String nombreDoctor, String especialidad, String telefono){
      Transaction  transaction = null;

      try {
         transaction = session.beginTransaction();
         //TODO
         //metodo para comrpobar el ultimo id y añadir 1

         Doctor nuevoDoctor = Doctor.builder()
                 .id(id)
                 .nombre(nombreDoctor)
                 .especialidad(especialidad)
                 .telefono(telefono)
                 .build();

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

   public void borrarPorId(int id){
      Transaction  transaction = null;

      try {

         transaction = session.beginTransaction();

         Query query1 = session.createQuery("DELETE FROM Cita Where doctor.id = :idParaBorrar");
         query1.setParameter("idParaBorrar",id);
         query1.executeUpdate();

         Query query2 = session.createQuery("DELETE FROM Doctor Where id = :id");
         query2.setParameter("id",id);
         int comprobacion = query2.executeUpdate();

         if (comprobacion > 0){
            System.out.println("Borrados " + comprobacion + " elementos");
         }


         transaction.commit();
         System.out.println("Doctor creado correctamente");
      } catch (Exception e) {
         if (transaction != null){
            transaction.rollback();
         }
         e.printStackTrace();
      }
   }

   public void modificarDoctor(int id,String nombre, String especialidad, String telefono){
      Transaction  transaction = null;
      Doctor traidoDB = null ;
      try {
         transaction = session.beginTransaction();

         Query existeDoctor = session.createQuery("SELECT d FROM Doctor d WHERE d.id = :id");
         existeDoctor.setParameter("id",id);
         traidoDB = (Doctor) existeDoctor.uniqueResult();

         System.out.println(traidoDB.getCita().toString());
         if (traidoDB.getId() > 0){ //esot implica que existe una tupla con esta id
            Query updateDoctor = session.createQuery("UPDATE Doctor SET nombre = :nombre, especialidad = :especialidad, telefono = :telefono WHERE id = :id");
            updateDoctor.setParameter("nombre",nombre);
            updateDoctor.setParameter("especialidad",especialidad);
            updateDoctor.setParameter("telefono",telefono);
            updateDoctor.setParameter("id",id);
            updateDoctor.executeUpdate();

            /*
            Si quiero usar el objeto modificarlo
            y guardarlo necesito usar esto

            session.update(traidoDB);
             */
            transaction.commit();
         }


         System.out.println("Doctor creado correctamente");
      } catch (Exception e) {
         if (transaction != null){
            transaction.rollback();
         }
         e.printStackTrace();
      }
   }

   /*
   Busca un doctor por nombre y devulve su id
   public int buscarDoctor(String nombreDoctor){
      //Buscamos si existe algun doctor con ese nombre
      Query<Integer> doctorQuery = session.createQuery("SELECT id FROM Doctor WHERE nombre = :nombre");
      doctorQuery.setParameter("nombre",nombreDoctor);
      int idDoctor = doctorQuery.getSingleResult();

      //devolvemos el doctor
      return idDoctor;
   }
    */

   //Busca un doctor por nombre y devuelve el objeto asociado
   public Doctor buscarDoctor(String nombreDoctor){
      //Buscamos si existe algun doctor con ese nombre
      Query<Doctor> doctorQuery = session.createQuery("FROM Doctor WHERE nombre = :nombre", Doctor.class);
      doctorQuery.setParameter("nombre",nombreDoctor);
      Doctor doctor = doctorQuery.uniqueResult();

      //devolvemos el doctor
      return doctor;
   }

}
