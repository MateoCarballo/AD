package Ejercicio._2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saludo")
public class SaludoController {
    private final SaludoService saludoService;

    @Autowired
    public SaludoController(SaludoService saludoService) {
        this.saludoService = saludoService;
    }
    /*
    @GetMapping("/{nombre}")
    public ResponseEntity<String> saludar (@PathVariable String nombre){
        String saludo = saludoService.saludarUsuario(nombre);
        return ResponseEntity.ok(saludo);
    }
     */
    @PostMapping("/{nombre}-{idioma}")
    public ResponseEntity<String> saludarIdioma1(@PathVariable String nombre, @PathVariable String idioma){
        String saludo = saludoService.saludarIdioma(nombre,idioma);
        return ResponseEntity.ok(saludo);
    }

    @PostMapping("/cuerpo")
    public ResponseEntity<String> saludarIdioma2(@RequestBody ObjetoIdioma request){
        String saludo = saludoService.saludarIdioma(request);
        return ResponseEntity.ok(saludo);
    }
}

