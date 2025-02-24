package Ejercicio402;

import java.util.ArrayList;

public class Ejercicio402 {

    static ArrayList<Libro> libros = new ArrayList<>();

    public Ejercicio402() {
        generar1000Libros();
    }

    private void  generar1000Libros(){
        for (int i = 0; i < 1000; i++) {
            new Libro(generarAleatorio(2000,2014),
                    generarAleatorio(1,20),
                    "titulo" + i,
                    "nombre" + i,
                    "Apellido1 - " + i + " Apellido2 - " + i,
                    "Editorial " + i,
                    generarAleatorio(150,850),

                    );
        }
    }

    private int generarAleatorio(int min, int max){
        return (int) (Math.floor(Math.random()*(max-min+1)+min));
    }
    private String generarNombre(){
        int numero = generarAleatorio(0,19);
        return "nombre"+numero;
    }

}
