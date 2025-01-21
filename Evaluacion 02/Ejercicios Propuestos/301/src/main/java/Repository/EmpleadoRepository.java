package Repository;

import java.util.List;

public class EmpleadoRepository implements Repository{
    @Override
    public void guardar(Object o) {

    }

    @Override
    public List encontrarTodos() {
        return List.of();
    }

    @Override
    public Object encontrarUnoPorId(long id) {
        return null;
    }

    @Override
    public void actualizar(Object o) {

    }

    @Override
    public void eliminar(Object o) {

    }
}
