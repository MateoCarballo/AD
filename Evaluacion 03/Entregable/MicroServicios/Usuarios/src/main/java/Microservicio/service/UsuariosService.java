package Microservicio.service;

import Microservicio.entity.Usuario;
import Microservicio.entity.UserDTO;
import Microservicio.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {
    private final UsuariosRepository usuariosRepositoryImpl;

    @Autowired
    public UsuariosService(UsuariosRepository usuariosRepositoryImpl) {
        this.usuariosRepositoryImpl = usuariosRepositoryImpl;
    }

    //METODOS PARA OPERAR SOBRE LA BASE DE DATOS USUARIOS

    public Usuario registrarUsuario(Usuario u) {
        //TODO pendiente de enviar un mensaje de usuario registrado o algo por el estilo
        return usuariosRepositoryImpl.save(u);
    }

    public String actualizarUsuario(Usuario user) {
        String cadenaResultado = "No existe el usuario que se desea modificar";
        //Aqui puedes usar save porque si tiene un id que ya existe entonces ejecuta un update en lugar de insertar una nueva tupla
        if (usuariosRepositoryImpl.existsById(user.getId())) {
            usuariosRepositoryImpl.save(user);
            cadenaResultado = "Usuario modificado con exito";
        }
        return cadenaResultado;
    }

    public String eliminarUsuario(UserDTO userDto) {
        return usuariosRepositoryImpl.deleteUserByUserDTO(userDto.getNombre(), userDto.getClave());
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return usuariosRepositoryImpl.findById(id).orElse(null);
    }

    public Usuario obtenerUsuarioPorNombre(String nombre) {
        return usuariosRepositoryImpl.findByNombre(nombre).orElse(null);
    }

    public Usuario validarNombreConstrasena(String nombre, String clave) {
        return usuariosRepositoryImpl.findByNombreAndContrasena(nombre, clave).orElse(null);
    }

    public Usuario comprobarId(int id) {
        return usuariosRepositoryImpl.findById(id).orElse(null);
    }
}
