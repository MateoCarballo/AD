package Repository;
import java.util.List;
public interface ZZ_Repository<T> {
    void guardar(T t);
    List<T> encontrarTodos();
    T encontrarUnoPorId(long id);
    void actualizar(T t);
    void eliminar(T t);
}
