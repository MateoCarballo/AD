package Ejercicio303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio303 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduce una letra y devolveré todos los empleados cuyo nombre empiece por la letra dada");
        String entradaTeclado;
        do {
            entradaTeclado = br.readLine();
        } while (entradaTeclado.length() != 1);
        char inicial = entradaTeclado.charAt(0);
        ArrayList<String> lecturaDB = ConsultarNombres.devolverEmpleados(inicial);
        System.out.println("Listado de nombre que comienzan por la letra J :");
        for (String e : lecturaDB) {
            System.out.println(e);
        }
    }
}
