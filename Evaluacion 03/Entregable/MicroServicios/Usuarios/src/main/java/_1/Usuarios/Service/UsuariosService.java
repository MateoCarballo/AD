package _1.Usuarios.Service;

import _1.Usuarios.Entity.User;
import _1.Usuarios.Entity.UserDTO;
import _1.Usuarios.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {
    private UsuariosRepository usuariosRepositoryImpl;
    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepositoryImpl) {
        this.usuariosRepositoryImpl = usuariosRepositoryImpl;
    }

    //METODOS PARA OPERAR SOBRE LA BASE DE DATOS USUARIOS

    public User registrarUsuario(User u){
        return usuariosRepositoryImpl.save(u);
    }

    public String actualizarUsuario(User user){
        if (usuariosRepositoryImpl.existsById(user.getUsuario_id()) ) usuariosRepositoryImpl.actualizarUsuario(user.getNombre(),user.getCorreo_electronico(),user.getDireccion(),user.getContrasena());
        return
    }
    public String eliminarUsuario(UserDTO userDto){
        return usuariosRepositoryImpl.deleteUserByUserDTO(userDto.getNombre(),userDto.getClave());
    }
    public String obtenerUsuarioPorId(int id){
        return usuariosRepositoryImpl.findById(id);
    }
    public User obtenerUsuarioPorNombre(String nombre){
        return usuariosRepositoryImpl.findByNombre(nombre);
    }
    public boolean comprobarId(int id){
        return usuariosRepositoryImpl.existsById(id);
    }
}
