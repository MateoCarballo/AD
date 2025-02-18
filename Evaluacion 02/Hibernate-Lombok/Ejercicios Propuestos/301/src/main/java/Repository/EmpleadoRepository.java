package Repository;

import Entity.Empleado;
import org.hibernate.Session;

import java.util.List;

public class EmpleadoRepository implements Repository <Empleado>{
    public Session sesionDB;

    public EmpleadoRepository(Session session){
        this.sesionDB = session;
    }


    @Override
    public void guardar(Empleado empleado) {

    }

    @Override
    public List encontrarTodos() {
        return List.of();
    }

    @Override
    public Empleado encontrarUnoPorId(long id) {
        return null;
    }

    @Override
    public void actualizar(Empleado empleado) {

    }

    @Override
    public void eliminar(Empleado empleado) {

    }

}
