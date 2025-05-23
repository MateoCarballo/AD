package Microservicio.controller;

import Microservicio.entity.UserCompleteDTO;
import Microservicio.entity.Usuario;
import Microservicio.entity.UserNombreContrasenaDTO;
import Microservicio.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    private final UsuariosService usuariosServiceImpl;

    @Autowired
    public UsuariosController(UsuariosService usuariosServiceImpl) {
        this.usuariosServiceImpl = usuariosServiceImpl;
    }

    @GetMapping("/todos")
    public ResponseEntity<?> obtenerTodosLosUsuarios() {
        return ResponseEntity.ok(usuariosServiceImpl.obtenerTodosLosUsuarios());
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsusario(@RequestBody Usuario usuario) {
        String cadenaRespuesta = usuariosServiceImpl.registrarUsuario(usuario);
        return ResponseEntity.ok(cadenaRespuesta);
    }

    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsusario(@RequestBody UserCompleteDTO u) {
        return ResponseEntity.ok(usuariosServiceImpl.actualizarUsuario(u));
    }

    @DeleteMapping("")
    public ResponseEntity<?> eliminarUsuario(@RequestBody UserNombreContrasenaDTO userNombreContrasenaDto) {
        return ResponseEntity.ok(usuariosServiceImpl.eliminarUsuario(userNombreContrasenaDto));
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody UserNombreContrasenaDTO userNombreContrasenaDto) {
        boolean responseOk = false;
        Usuario queryResult = usuariosServiceImpl.validarNombreConstrasena(userNombreContrasenaDto.getNombre(), userNombreContrasenaDto.getContrasena());
        if (queryResult != null) {
            responseOk = true;
        }
        return ResponseEntity.ok(responseOk);
    }

    @GetMapping("/info/id/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable("id") int user_id) {
        String userName = "No se ha encontrado ningun usuario con el id proporcionado";
        Usuario user = usuariosServiceImpl.obtenerUsuarioPorId(user_id);
        if (user != null) {
            userName = user.getNombre();
        }
        return ResponseEntity.ok(userName);
    }

    @GetMapping("/info/nombre/{nombre}")
    public ResponseEntity<Usuario> obtenerInfoUsuarioPorNombre(@PathVariable("nombre") String user_name) { //TODO revisar aqui si funciona llamandole difernete a cada cosa id -> UserId
        return ResponseEntity.ok(usuariosServiceImpl.obtenerUsuarioPorNombre(user_name));
    }

    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> comprobarId(@PathVariable("id") int user_id) {
        boolean exist = false;
        Usuario user = usuariosServiceImpl.comprobarId(user_id);
        if (user != null) {
            exist = true;
        }
        return ResponseEntity.ok(exist);
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("La aplicación está funcionando correctamente");
    }
}