package Ejercicio402;

import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio402 {
    static Scanner sc;
    static ArrayList<Libro> biblioteca = new ArrayList<>();
    final static String nombreDatasetGrande = "ejercicio402FicheroGrande";
    final static String nombreDatasetPequeno = "ejercicio402FicherosPequenhos";

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int opcion;
        do{
            opcion = pedirInt("1. Crear datasets\n2. Realizar consultas\n3. Salir");
            switch (opcion){
                case 1:
                    crearDatasets();
                    break;
                case 2:
                    realizarConsultas();
                    break;
            }
        }while(opcion != 3);
    }

    private static void realizarConsultas() {
        try(BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            int opcion;
            do{
                opcion = pedirInt("1. Número total de documentos\n" +
                        "2. Número de libros publicados antes de cierto año\n" +
                        "3. Número de libros que escribió un determinado autor\n" +
                        "4. Número medio de palabras de todos los libros\n" +
                        "5. Número medio de palabras de los libros publicados antes de un determinado año y por un determinado autor\n" +
                        "6. Salir");
                int opcion2;
                do{
                    opcion2 = pedirInt("1. Dataset grande\n2. Dataset pequeño");
                }while(opcion2 > 2 || opcion2 < 1);

                switch (opcion) {
                    case 1:
                        totalDocumentos(session, opcion2);
                        break;
                    case 2:
                        librosPorAnho(session, opcion2);
                        break;
                    case 3:
                        librosPorAutor(session, opcion2);
                        break;
                    case 4:
                        numMedioPalabras(session, opcion2);
                        break;
                    case 5:
                        numMedioPalabrasLibroAnhoAutor(session, opcion2);
                        break;
                }
            }while(opcion != 6);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void numMedioPalabrasLibroAnhoAutor(BaseXClient session, int opcion) {
        try{
            if(opcion == 1){
                BaseXClient.Query query = session.query("avg(db:get('ejercicio402FicheroGrande')//libro[autor/nombre/text() = 'Nombre1' and @publicacion > 2010]/paginas)");
                while(query.more()){
                    System.out.println(query.next());
                }
            }
            if (opcion ==2){

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void numMedioPalabras(BaseXClient session, int opcion) {
        try{
            if(opcion == 1){
                BaseXClient.Query query = session.query("avg(db:get('ejercicio402FicheroGrande')//libro/paginas)");
                while(query.more()){
                    System.out.println(query.next());
                }
            }
            if (opcion ==2){

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void librosPorAutor(BaseXClient session, int opcion) {
        try{
            if(opcion == 1){
                BaseXClient.Query query = session.query("count(db:get('ejercicio402FicheroGrande')//libro[autor/nombre/text() = 'Nombre1'])");
                while(query.more()){
                    System.out.println(query.next());
                }
            }
            if (opcion ==2){

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void totalDocumentos(BaseXClient session, int opcion){

    }

    public static void librosPorAnho(BaseXClient session, int opcion) throws IOException {
        if(opcion == 1){
            BaseXClient.Query query = session.query("count(db:get('ejercicio402FicheroGrande')/biblioteca/libro[@publicacion<2009])");
            while(query.more()){
                System.out.println(query.next());
            }
        }
        if (opcion ==2){

        }
    }

    private static String pedirString(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return sc.next();
            }catch (Exception ignored){}
        }
    }

    private static int pedirInt(String mensaje) {
        while(true){
            try{
                System.out.println(mensaje);
                return sc.nextInt();
            }catch (Exception ignored){}
        }
    }

    private static void crearDatasets() {
        while(biblioteca.size() < 1000)
            biblioteca.add(new Libro().generarDatosAleatorios());

        try(BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {

            InputStream bais = new ByteArrayInputStream(biblioteca.get(0).toString().getBytes());

            session.create(nombreDatasetPequeno, bais);

            for (int i = 1; i < biblioteca.size(); i++) {
                bais = new ByteArrayInputStream(biblioteca.get(i).toString().getBytes());
                session.add(nombreDatasetPequeno + "/libro" + i + ".xml", bais);
            }
        }catch (Exception ignored){
            System.out.println();
            ignored.printStackTrace();
        }

        try(BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            StringBuilder sb = new StringBuilder();
            sb.append("<biblioteca>");
            for (Libro libro: biblioteca) {
                sb.append(libro.toString());
            }
            sb.append("</biblioteca>");

            InputStream bais = new ByteArrayInputStream(sb.toString().getBytes());

            session.create(nombreDatasetGrande, bais);
        }catch (Exception ignored){
            System.out.println();
            ignored.printStackTrace();
        }
    }
}