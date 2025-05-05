package Ejercicio._1;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {
    public String saludarUsuario(String nombre){
        return "¡Hola, " + nombre + "!";
    }
}
