import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        ejercicio101();
        ejercicio102();
    }

    static void ejercicio101() throws IOException {

        /*
        Ejercicio 101
        Desarrolla un programa Java que permita mostrar por pantalla la siguiente información de un fichero o de un directorio:

        Nombre
        Ruta relativa
        Ruta absoluta
        Si permite lectura
        Si permite escritura
        Su tamaño
        Si es un fichero o no
         */

        try {
            System.out.println("Ejercicio 101");
            System.out.println("Introduce el nombre del archivo (ruta/al/archivo))");
            String entradaTeclado= br.readLine();
            File ruta = new File("src/"+entradaTeclado);
            System.out.println(ruta.getPath());

            if (!ruta.exists()){
                System.out.println("Archivo o directorio no encontrado");
                return;
            }
            if (ruta.isDirectory()){
                System.out.println("El valor introducido es un directorio y sus propiedades son:");
            } else if (ruta.isFile()) {
                System.out.println("El valor introducido es un fichero y sus propiedades son:");
            }
            System.out.println("Nombre del archivo -> " + ruta.getName());
            System.out.println("Ruta absoluta del archivo -> " + ruta.getAbsolutePath());
            System.out.println("Ruta relativa -> " + ruta.getPath());
            System.out.println("Permite lectura -> " + ruta.canRead());
            System.out.println("Permite escritura -> " + ruta.canWrite());
            System.out.println("tamaño en bytes -> " + ruta.length());
        } catch (IOException e) {
            System.out.println("Fichero no encontrado");
        }


    }

    static void ejercicio102() throws IOException {
        /*
        Desarrolla un programa Java que permita enumerar todos los ficheros y subdirectorios que tiene un directorio dado.
         */
        System.out.println("Ejercicio 102");
        System.out.println("Introduce el directorio");
        File directorio = new File(br.readLine());
        listarContenido(directorio);
    }

    static void listarContenido (File f){
        for (int i = 0; i < f.list().length; i++) {
            System.out.println(f.list()[i]);
            if(!f.list()[i].contains(".")){
               listarContenido(new File(f.getPath() + "/" + f.list()[i]));
            }
        }
    }

}