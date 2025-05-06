package Ejercicio._2;

import org.springframework.stereotype.Service;

@Service
public class SaludoService {
    public String saludarUsuario(String nombre){
        return "¡Hola, " + nombre + "!";
    }

    public String saludarIdioma(ObjetoIdioma oi) {
        String respuesta = "";
        switch(oi.getIdioma()){
            case "español"-> {
                respuesta = "Hola, " + oi.getNombre();
            }
            case "english"-> {
                respuesta = "Hello, " + oi.getNombre();
            }
            case "francais" -> {
                respuesta = "Bonjour, " + oi.getNombre();
            }
            case "portugues" -> {
                respuesta = "Olá, " + oi.getNombre();
            }
            default -> {
                respuesta = "Idioma no soportado";
            }
        }
        return respuesta;
    }

    public String saludarIdioma(String nombre, String idioma) {
        String respuesta = "";
        switch(idioma){
            case "español"-> {
                respuesta = "Hola, " + nombre;
            }
            case "english"-> {
                respuesta = "Hello, " + nombre;
            }
            case "francais" -> {
                respuesta = "Bonjour, " + nombre;
            }
            case "portugues" -> {
                respuesta = "Olá, " + nombre;
            }
            default -> {
                respuesta = "Idioma no soportado";
            }
        }
        return respuesta;
    }
}