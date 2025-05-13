package Microservicio.service;

import Microservicio.entity.UserCompleteDTO;
import Microservicio.entity.Usuario;
import Microservicio.entity.UserNombreContrasenaDTO;
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

    public String registrarUsuario(Usuario u) {
        Usuario usuarioGuardado = usuariosRepositoryImpl.save(u);
        return "Nuevo usuario guardado con id " + usuarioGuardado.getId();
    }

    public String actualizarUsuario(UserCompleteDTO userCompleteDTO) {
        String cadenaResultado = "No existe el usuario que se desea modificar";
        Usuario usuario =usuariosRepositoryImpl.findById(userCompleteDTO.getU_id()).orElse(null);
        if (usuario != null) {
            usuariosRepositoryImpl.save(usuario);
            cadenaResultado = "Usuario modificado con exito";
        }
        return cadenaResultado;
    }

    public String eliminarUsuario(UserNombreContrasenaDTO userNombreContrasenaDto) {
        String response = "No se ha encontrado ningun usuario con este nombre y constrasena";
        Usuario usuario = usuariosRepositoryImpl.findByNombreAndContrasena(
                userNombreContrasenaDto.getNombre(),
                userNombreContrasenaDto.getContrasena()
                ).orElse(null);

        if( usuario != null ){
            usuariosRepositoryImpl.delete(usuario);
            response = "Usuario eliminado con exito";
        }
        return response;
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
