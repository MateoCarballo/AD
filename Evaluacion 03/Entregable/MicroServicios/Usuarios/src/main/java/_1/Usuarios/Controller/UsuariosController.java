package _1.Usuarios.Controller;

import _1.Usuarios.Entity.User;
import _1.Usuarios.Entity.UserDTO;
import _1.Usuarios.Service.UsuariosService;
import org.apache.catalina.connector.Response;
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

    @PostMapping("/registrar")
    public ResponseEntity<User> registrarUsusario(@RequestBody User u){
        //TODO aqui quiza deberia comprobar que tenga como respuesta un usuario y si la tengo entonces darle otro mensaje mas amigable
        return ResponseEntity.ok(usuariosServiceImpl.registrarUsuario(u));
    }
    @PutMapping("/registrar")
    public ResponseEntity<String> actualizarUsusario(@RequestBody User u){
        return ResponseEntity.ok(usuariosServiceImpl.actualizarUsuario(u));
    }
    @DeleteMapping("")
    public ResponseEntity<String> eliminarUsuario(@RequestBody UserDTO userDto){
        return ResponseEntity.ok(usuariosServiceImpl.eliminarUsuario(userDto));
    }
    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarUsuario(@RequestBody UserDTO userDto){
        Boolean responseOk = false;
        User queryResult = usuariosServiceImpl.validarNombreConstrasena(userDto.getNombre(),userDto.getClave());
        if(queryResult != null){
            responseOk = true;
        }
        return ResponseEntity.ok(responseOk);
    }
    @GetMapping("/info/{id}")
    public ResponseEntity<String> obtenerInfoUsuarioPorId(@PathVariable("id") int user_id){ //TODO revisar aqui si funciona llamandole difernete a cada cosa id -> UserId
        String userName = "No se ha encontrado ningun usuario con el id proporcionado";
        User user = usuariosServiceImpl.obtenerUsuarioPorId(user_id);
        if (user != null){
            userName = user.getNombre();
        }
        return ResponseEntity.ok(userName);
    }
    @GetMapping("/info/{nombre}")
    public ResponseEntity<User> obtenerInfoUsuarioPorNombre(@PathVariable("nombre") String user_name){ //TODO revisar aqui si funciona llamandole difernete a cada cosa id -> UserId
        return ResponseEntity.ok(usuariosServiceImpl.obtenerUsuarioPorNombre(user_name));
    }
    @GetMapping("/checkIfExist/{id}")
    public ResponseEntity<Boolean> comprobarId(@PathVariable("id") int user_id){
        boolean exist = false;
        User user = usuariosServiceImpl.comprobarId(user_id);
        if (user!=null){
            exist = true;
        }
        return ResponseEntity.ok(exist);
    }
}
