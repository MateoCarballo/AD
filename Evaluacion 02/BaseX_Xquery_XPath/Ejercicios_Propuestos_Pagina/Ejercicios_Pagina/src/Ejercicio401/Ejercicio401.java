package Ejercicio401;

import org.basex.examples.api.BaseXClient;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Ejercicio401 {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int ultimoIndice;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("<alumno>\n");
        sbuilder.append("<nombre>");
        sbuilder.append("nombre");
        sbuilder.append("</nombre>\n");
        sbuilder.append("<apellidos>");
        sbuilder.append("apellidos apellidos");
        sbuilder.append("</apellidos>\n");
        sbuilder.append("<edad>");
        sbuilder.append("edad");
        sbuilder.append("</edad>\n");
        sbuilder.append("<correo>");
        sbuilder.append("correo");
        sbuilder.append("</correo>\n");
        sbuilder.append("</alumno>\n");
        String prueba = sbuilder.toString();
        //Crear la db Ejercicio 401
        //Iterar sobre objetos para insertarlos

        /**
        Se pide desarrollar una aplicación Java que permita:

        Conectarse a una base de datos XML gestionada por BaseX denominada ejercicio401
        El usuario introducirá datos en el sistema de forma que complete un XML con la siguiente estructura:

        <alumno>
          <nombre></nombre>
          <apellidos></apellidos>
          <edad></edad>
          <correo></correo>
        </alumno>

        El sistema pedirá valores de forma indefinida hasta que el usuario indique lo contrario.
        Cada vez que se cree un fichero XML se añadirá a la base de datos.
        Una vez finalizada la introducción de datos el sistema mostrará un menú con las siguientes opciones:

        Número total de documentos en el sistema
        Media de edad de los alumnos.
        Mostrar un XML con la edad del alumno mayor y del alumno menor (toodo en el mismo documento XML).
        Mostrar el nombre de los alumnos ordenado por edad de mayor a menor.
        Mostrar el nombre de un alumno de forma aleatoria.
         */

        //Crear base de datos si lo necesita
        //crearDBBaseX();

        //Eliminar base de datos si lo necesitas
        //eliminarBaseX();

        do{
            ultimoIndice = contarNumeroAlumnos();
            crearAlumnoParaInsertar();
            //hardcodeado para poder probar
            //insertarFichero("Ejercicio401",prueba);
            System.out.println("Seguir introduciendo Si/No");
        }while((scanner.next().equalsIgnoreCase("Si")));

        System.out.println("""
                Una vez finalizada la introducción de datos el sistema mostrará un menú con las siguientes opciones:
                1 -> Número total de documentos en el sistema
                2 -> Media de edad de los alumnos.
                3 -> Mostrar un XML con la edad del alumno mayor y del alumno menor (todo en el mismo documento XML).
                4 -> Mostrar el nombre de los alumnos ordenado por edad de mayor a menor.
                5 -> Mostrar el nombre de un alumno de forma aleatoria.
          
                """);
        int eleccion = 0;
        try{
            eleccion= Integer.parseInt(br.readLine());
        }catch (Exception e){
            System.out.println("Error al elegir en el menu");
        }
        switch (eleccion){
            case 1 -> {numeroTotalDocumentos();}
            case 2 -> {mediaEdadAlumnos();}
            case 3 -> {mostrarXMLEdadAlumnoMayor();}
            case 4 ->{mostrarAlumnosEdadMayorMenor();}
            case 5 ->{mostrarAlumnoAleatorio();}
            default -> {System.out.println("La opcion seleccionada no es valida");}
        }
    }

    private static int contarNumeroAlumnos() {
        int numeroAlumnos = -1;
        try(BaseXClient session = new BaseXClient(
                "localhost",
                1984,
                "admin",
                "abc123")

        ){
            //Apertura base de datos
            numeroAlumnos = Integer.parseInt(session.execute("count(Ejercicio401/alumno)"));

        } catch (IOException e) {
            System.out.println("Error al añadir fichero");
            e.printStackTrace();
        }
        return numeroAlumnos;
    }

    private static void numeroTotalDocumentos() {
        try(BaseXClient session = new BaseXClient(
                "localhost",
                1984,
                "admin",
                "abc123")

        ){
            //Apertura base de datos
            session.execute("""
                    for $x in doc("libros.xml")/biblioteca/libros/libro
                      where $x/editorial = "O'Reilly"
                      order by $x/titulo
                      return <li>{data($x/titulo)}</li>
                    """);

        } catch (IOException e) {
            System.out.println("Error al añadir fichero");
            e.printStackTrace();
        }
    }

    private static void mediaEdadAlumnos() {
    }

    private static void mostrarXMLEdadAlumnoMayor() {
    }

    private static void mostrarAlumnosEdadMayorMenor() {
    }

    private static void mostrarAlumnoAleatorio() {
    }

    private static void crearAlumnoParaInsertar() {
        StringBuilder sb = new StringBuilder();
        try{
            sb.append("<alumno>\n");
            System.out.println("Nombre");
            sb.append("<nombre>");
            String nombre = br.readLine();
            sb.append(nombre);
            sb.append("</nombre>\n");
            System.out.println("Apellidos");
            sb.append("<apellidos>");
            String apellidos = br.readLine();
            sb.append(apellidos);
            sb.append("</apellidos>\n");
            System.out.println("Edad");
            sb.append("<edad>");
            String edad = br.readLine();
            sb.append(edad);
            sb.append("</edad>\n");
            System.out.println("Correo");
            sb.append("<correo>");
            String correo = br.readLine();
            sb.append(correo);
            sb.append("</correo>\n");
            sb.append("</alumno>");
        }catch (IOException e){
            System.out.println("Error al leer");
        }

        insertarFichero("Fichero401",sb.toString(),(ultimoIndice+1));

    }

    public static void crearDBBaseX(){
        try(BaseXClient session = new BaseXClient(
                "localhost",
                1984,
                "admin",
                "abc123")
        ){
            //Creando la DB (Donde colocar los archivos .xml)
            final InputStream bais = new ByteArrayInputStream("<xml>Ejercicio401</xml>".getBytes());
            session.create("Ejercicio401",bais);
            System.out.println(session.info());
        } catch (IOException e) {
            System.out.println("Error durante la lectura de datos");
        }
    }

    public static void eliminarBaseX(){
        try(BaseXClient session = new BaseXClient(
                "localhost",
                1984,
                "admin",
                "abc123")
        ){
            //Borrar base de datos
            final InputStream bais = new ByteArrayInputStream("<xml>Ejercicio401</xml>".getBytes());
            session.execute("drop db Ejercicio401");
        } catch (IOException e) {
            System.out.println("Error durante la lectura de datos");
        }
    }

    private static void insertarFichero(String nombreDB, String Alumno, int indice){
        try(BaseXClient session = new BaseXClient(
                "localhost",
                1984,
                "admin",
                "abc123")

        ){
            //Apertura base de datos
            session.execute("open Ejercicio401");
            final InputStream bais = new ByteArrayInputStream(Alumno.getBytes());

            //Insertar un archivo .xml
            session.add(nombreDB + "/Alumno" + indice +".xml",bais);
        } catch (IOException e) {
            System.out.println("Error al añadir fichero");
            e.printStackTrace();
        }

    }
}
