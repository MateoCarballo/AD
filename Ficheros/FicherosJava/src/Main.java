import FicherosPrueba.FiltrarNombre106;
import FicherosPrueba.ManejoFicheros105;

import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
//        ejercicio101();
//        ejercicio102();
//        ejercicio104();
//        ejercicio105();
        ejercicio106();
//        probarRutas();

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

    static void ejercicio104() throws IOException {
        /*
        Desarrolla un programa Java que permita:
        Crear un fichero de texto de forma secuencial en el que vaya el siguiente texto:
        Ejemplo de escritura en un fichero de texto o txt
        Visualizar el contenido del fichero
         */

        String cadenaParaEscribir = "Ejemplo de escritura en un fichero de texto o txt";
        ej104CopiarString(cadenaParaEscribir);
        ej104leerArchivo("FicherosJava\\src\\FicherosPrueba\\Ficheros104\\Fichero.txt");

    }

    private static void ej104leerArchivo(String rutaAlArchivo) {
        try {
            File f = new File(rutaAlArchivo);
            FileReader fr = new FileReader(f);

            /*char b[]=new char[150];

            fr.read(b);
            for (int i = 0; i < b.length; i++){
                System.out.print((char)b[i]);
            }*/
            //Lectura caracter a carácter hasta llegar al fin
            int i;
            while((i = fr.read()) != -1){
                System.out.print((char) i);
            }

            fr.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");;
        }


    }

    static void ej104CopiarString(String cadenaParaCopiar){
        String[] vectorCadena = cadenaParaCopiar.split(" ");
        BufferedWriter bw= null;
        try {
            File archivo = new File("FicherosJava\\src\\FicherosPrueba\\Ficheros104\\Fichero.txt");
            if (!archivo.exists()){
                archivo.createNewFile();
            }
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), "UTF-8"));

            for(String c :vectorCadena ){
                bw.write(c+" ");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error durante la escritura del rutaAlArchivo");
        }

    }

    static void probarRutas() throws IOException {
        File f = new File("FileConRutaRelativa");

        File[] listaFicheros = f.listFiles();

        System.out.println(f.getParent());
//        for (int i = 0; i < listaFicheros.length ; i++) {
//            System.out.println(listaFicheros[i].getName());
//        }
        System.out.println();
    }

    static void ejercicio105() throws IOException {
        /*
        Ejercicio 105
        Desarrolla un programa Java que defina una clase ManejoFicheros que implemente los siguientes métodos:
        void crearFichero(String fichero): crea el fichero indicado
        void borrarFichero(String fichero): borra el fichero indicado.
        void crearDirectorio(String ruta): crea el directorio indicado.
        void borrarDirectorio(String ruta): borra el directorio indicado.
        void listarDirectorio(String ruta): lista el contenido del directorio
        Clase main que permita comprobar el funcionamiento de los métodos anteriores.
        Nota: usa la clase File para realizar el ejercicio




         */
        String rutaFichero = "src\\FicherosPrueba\\Ficheros105\\Fichero105.txt";
        String rutaDirectorio = "src\\FicherosPrueba\\Ficheros105";
        ManejoFicheros105.crearFichero(rutaFichero);
        ManejoFicheros105.borrarFichero(rutaFichero);
        ManejoFicheros105.crearDirectorio(rutaDirectorio);
        ManejoFicheros105.borrarDirectorio(rutaDirectorio);
        ManejoFicheros105.listarDirectorio("src\\FicherosPrueba");
    }

    static void ejercicio106(){
    /*
    Ejercicio 106
    Utilizando la interfaz FilenameFilter realiza los siguientes apartados:
    Crear una clase FiltrarNombre que implemente el siguiente método:
    filtrar(String ruta, String extension): lista solo
    aquellos archivos de la ruta que tienen una determinada extension
    Crear una clase FiltrarTamano que implemente el siguiente método:
    filtrar(String ruta, float minTamano): lista solo
    aquellos archivos de la ruta que tienen un tamaño mayor que el especificado.
    Realizar un programa main que permita comprobar el
    funcionamiento de los métodos anteriores
    */
        String rutaFichero = "src\\FicherosPrueba\\.";
        File f = new File (rutaFichero);
        FiltrarNombre106 fn = new FiltrarNombre106(".txt");

        File[] listadoDirectorio = f.listFiles();

        for (int i = 0; i < listadoDirectorio.length; i++) {
            if (fn.accept(listadoDirectorio[i],listadoDirectorio[i].getName())) {
                System.out.println(listadoDirectorio[i].getName());
            }
        }
    }

}