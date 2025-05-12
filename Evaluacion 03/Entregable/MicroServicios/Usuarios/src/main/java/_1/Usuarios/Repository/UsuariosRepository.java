package _1.Usuarios.Repository;

import _1.Usuarios.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<User,Integer> {
    Optional<User> findByNombreAndContrasena(String name, int contrasena);
    Optional<User> findByNombre(String nombre);
    @Query("SELECT u FROM User u WHERE u.nombre = ? AND u.clave = ?")
    String deleteUserByUserDTO(String nombre, int contrasena);
}
