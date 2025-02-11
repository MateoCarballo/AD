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
         //Creamos un objeto doctor y lo guardamos en al DB
         transaction = session.beginTransaction();
         Doctor nuevoDoctor = Doctor.builder()
                 .id(id)
                 .nombre(nombreDoctor)
                 .especialidad(especialidad)
                 .telefono(telefono)
                 .build();
         session.save(nuevoDoctor);
         transaction.commit();

      } catch (Exception e) {
         if (transaction != null){
            transaction.rollback();
         }
         e.printStackTrace();
      }
   }

   public void borrarPorId(int id){
      Transaction  transaction = null;
      int referenciasEliminadas = 0;
      int doctoresEliminados = 0;

      try {
         //Para borrar el doctor necesitamos eliminar las referencias que existen en otras tablas
         transaction = session.beginTransaction();

         Query queryEliminarReferenciaEnCita = session.createQuery("DELETE FROM Cita Where doctor.id = :idParaBorrar");
         queryEliminarReferenciaEnCita.setParameter("idParaBorrar",id);
         referenciasEliminadas = queryEliminarReferenciaEnCita.executeUpdate();

         Query queryEliminarDoctor = session.createQuery("DELETE FROM Doctor Where id = :id");
         queryEliminarDoctor.setParameter("id",id);
         doctoresEliminados = queryEliminarDoctor.executeUpdate();

         transaction.commit();
      } catch (Exception e) {
         if (transaction != null){
            transaction.rollback();
         }
         e.printStackTrace();
      }
   }

   public void modificarDoctor(int id,String nombre, String especialidad, String telefono){
      Transaction  transaction = null;
      Doctor doctor = null ;
      try {
         //Traemos el objeto doctor asociado al id
         transaction = session.beginTransaction();
         Query<Doctor> existeDoctor = session.createQuery("FROM Doctor WHERE id = :id", Doctor.class);
         existeDoctor.setParameter("id",id);
         doctor = existeDoctor.uniqueResult();

         if (doctor != null){

            //Seteamos los campos que queremos modificar
            doctor.setNombre(nombre);
            doctor.setEspecialidad(especialidad);
            doctor.setTelefono(telefono);

            //Actualizamos los nuevos valores en la tabla
            session.update(doctor);
            transaction.commit();

            /*       REALIZANDO UNA CONSULTA CON SET
            Query updateDoctor = session.createQuery(
                    "UPDATE Doctor SET nombre = :nombre, especialidad = :especialidad, telefono = :telefono WHERE id = :id");
            updateDoctor.setParameter("nombre",nombre);
            updateDoctor.setParameter("especialidad",especialidad);
            updateDoctor.setParameter("telefono",telefono);
            updateDoctor.setParameter("id",id);
            updateDoctor.executeUpdate();
             */

            /*
            Si quiero usar el objeto modificarlo
            y guardarlo necesito usar esto

            session.update(traidoDB);
             */
         }
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
