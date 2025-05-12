package Microservicio.repository;

import Microservicio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreAndContrasena(String name, String contrasena);

    Optional<Usuario> findByNombre(String nombre);

    @Query("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contrasena = :contrasena")
    String deleteUserByUserDTO(@Param("nombre") String nombre, @Param("contrasena") String contrasena);


}