package Ejercicios_MySQL.Ejercicio309.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static String PATRON_MENU = "^[1-6]$";
    public static void main(String[] args) {
        String entradaTeclado = "";
        do{
            System.out.println("""
                1 -> Prestar un libro
                2 -> Devolver un libro
                3 -> Listar los libros presatados
                4 -> Listar los libros que se pueden prestar
                5 -> Salir\s
               \s""");
            try {
                entradaTeclado = br.readLine();
                if (!comprobarRegex(entradaTeclado,PATRON_MENU)){

                }
            } catch (IOException e) {
                System.out.println("Error durante la introduccion por teclado");
            }
        }while(!entradaTeclado.equalsIgnoreCase("5"));

    }

    public static boolean comprobarRegex(String cadena, String patron){
        return cadena.matches(patron);
    }
}
