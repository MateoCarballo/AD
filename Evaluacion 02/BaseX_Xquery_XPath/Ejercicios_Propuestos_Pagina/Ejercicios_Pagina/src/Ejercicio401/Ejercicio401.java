package Ejercicio401;

import org.basex.examples.api.BaseXClient;

import java.io.*;
import java.nio.Buffer;
import java.util.Scanner;

public class Ejercicio401 {
    final static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
        /*
        <alumno>
            <nombre></nombre>
            <apellidos></apellidos>
            <edad></edad>
            <correo></correo>
         </alumno>
         */

        //Crear base de datos si lo necesita
        //crearDBBaseX();

        //Eliminar base de datos si lo necesitas
        //eliminarBaseX();

        do{
            crearAlumnoParaInsertar();
            //hardcodeado para poder probar
            //insertarFichero("Ejercicio401",prueba);
            System.out.println("Seguir introduciendo Si/No");
        }while((scanner.next().equalsIgnoreCase("Si")));

        System.out.println("""
                Una vez finalizada la introducción de datos el sistema mostrará un menú con las siguientes opciones:
                1 -> Número total de documentos en el sistema
                2 ->Media de edad de los alumnos.
                3 ->Mostrar un XML con la edad del alumno mayor y del alumno menor (todo en el mismo documento XML).
                4 ->Mostrar el nombre de los alumnos ordenado por edad de mayor a menor.
                5 ->Mostrar el nombre de un alumno de forma aleatoria.
          
                """);
        int eleccion = 0;
        try{
            eleccion= Integer.parseInt(br.readLine());
        }catch (Exception e){
            System.out.println("Error al elegir en el menu");
        }
        switch (eleccion){
            case 1 -> {numeroTotalDocumentos()}
            case 2 -> {mediaEdadAlumnos()}
            case 3 -> {mostrarXMLEdadAlumnoMayor()}
            case 4 ->{mostrarAlumnosEdadMayorMenor()}
            case 5 ->{mostrarAlumnoAleatorio()}
            default -> {}
        }
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

        insertarFichero("Fichero401",sb.toString());

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

    private static void insertarFichero(String nombreDB, String Alumno){
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
            session.add(nombreDB + "/Alumno.xml",bais);
        } catch (IOException e) {
            System.out.println("Error al añadir fichero");
            e.printStackTrace();
        }

    }
}
