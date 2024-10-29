package Ejercicio303;

import java.util.ArrayList;

public class Ejercicio303 {
    public static void main(String[] args) {
        ArrayList<String> lecturaDB = ConsultarNombres.devolverEmpleados('J');
        System.out.println("Listado de nombre que comienzan por la letra J :");
        for(String e : lecturaDB){
            System.out.println(e);
        }
    }
}
