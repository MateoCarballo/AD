package Ejercicios_MySQL.Ejercicio303;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio303 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        boolean continuar = true;
        do{
            int seleccion = 0;
            System.out.println("""
                            Que deseas hacer ?
                            1. Buscar nombres por inicial.
                            2. Borrar empleado por ID.
                            0. Salir.""");
            do{
                try{
                    seleccion = Integer.parseInt(br.readLine());
                }catch (Exception e ){
                    System.out.println("Revisa los datos introducidos!");
                }
            }while(seleccion<0 || seleccion >2);

            switch (seleccion){
                case 1 -> buscarEmpleadosPorInicial();
                case 2 -> borrarEmpleadoPorId();
                case 0 -> continuar = false;
            }
        }while (continuar);
    }

    private static void borrarEmpleadoPorId() throws IOException {
        System.out.println("Introduce el ID del empleado que deseas eliminar de la BD");
        String numeroSS="";
        do{
             numeroSS = br.readLine();
        }while(!comprobarFormatoNSS(numeroSS));
        BorraEmpleados.borrarEmpleadoPorNSS(Integer.parseInt(numeroSS));
    }

    public static void buscarEmpleadosPorInicial() throws IOException {
        System.out.println("Introduce una letra y devolveré todos los empleados cuyo nombre empiece por la letra dada");
        String entradaTeclado;
        do {
            entradaTeclado = br.readLine();
        } while (entradaTeclado.length() != 1);
        char inicial = entradaTeclado.charAt(0);
        ArrayList<String> lecturaDB = ConsultarNombres.devolverEmpleados(inicial);
        System.out.println("Listado de nombre que comienzan por la letra "+ inicial + ":");
        for (String e : lecturaDB) {
            System.out.println(e);
        }
    }


    public static boolean comprobarFormatoNSS(String entrada){
        String patronRegex = "\\d{5}";
        return entrada.matches(patronRegex);
    }
}
