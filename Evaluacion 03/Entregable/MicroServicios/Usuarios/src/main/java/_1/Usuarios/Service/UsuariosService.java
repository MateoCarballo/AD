package _1.Usuarios.Service;

import _1.Usuarios.Entity.User;
import _1.Usuarios.Entity.UserDTO;
import _1.Usuarios.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuariosService {
    private UsuariosRepository usuariosRepositoryImpl;
    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepositoryImpl) {
        this.usuariosRepositoryImpl = usuariosRepositoryImpl;
    }

    //METODOS PARA OPERAR SOBRE LA BASE DE DATOS USUARIOS

    public User registrarUsuario(User u){
        //TODO pendiente de enviar un mensaje de usuario registrado o algo por el estilo
        return usuariosRepositoryImpl.save(u);
    }

    public String actualizarUsuario(User user){
        String cadenaResultado = "No existe el usuario que se desea modificar";
        //Aqui puedes usar save porque si tiene un id que ya existe entonces ejecuta un update en lugar de insertar una nueva tupla
        if (usuariosRepositoryImpl.existsById(user.getId())) {
            usuariosRepositoryImpl.save(user);
            cadenaResultado = "Usuario modificado con exito";
        }
        return cadenaResultado;
    }
    public String eliminarUsuario(UserDTO userDto){
        return usuariosRepositoryImpl.deleteUserByUserDTO(userDto.getNombre(),userDto.getClave());
    }
    public User obtenerUsuarioPorId(int id){
        return usuariosRepositoryImpl.findById(id).orElse(null);
    }
    public User obtenerUsuarioPorNombre(String nombre){
        return usuariosRepositoryImpl.findByNombre(nombre).orElse(null);
    }
    public User validarNombreConstrasena(String nombre, int clave){
        return usuariosRepositoryImpl.findByNombreAndContrasena(nombre,clave).orElse(null);
    }
    public User comprobarId(int id){
        return usuariosRepositoryImpl.findById(id).orElse(null);
    }
}
