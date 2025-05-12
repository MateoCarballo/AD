package _1.Usuarios.Repository;

import _1.Usuarios.Entity.User;
import _1.Usuarios.Entity.UserDTO;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;;

@Repository
public interface UsuariosRepository extends JpaRepository<User,Integer> {

    //TODO trabajando aqui
    @Query("")
    String actualizarUsuario(String newNombre,String newCorreo, String newDireccion, String newContrasena);

    User findByNombre(String name);

    String findById(int id);

    @Query("SELECT u FROM User u WHERE u.nombre = ? AND u.clave = ?")
    String deleteUserByUserDTO(String nombre, int contrasena);
}
