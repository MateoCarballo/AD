package Ejercicio401;

import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Ejercicio401 {
    public static void main(String[] args) {
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
        //crearDBBaseX();
        //eliminarBaseX();
        insertarFichero();

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

    private static void insertarFichero(){
        try(BaseXClient session = new BaseXClient(
                "localhost",
                1984,
                "admin",
                "abc123")

        ){
            //Apertura base de datos
            session.execute("open Ejercicio401");
            final InputStream bais = new ByteArrayInputStream("<xml>Ejercicio401</xml>".getBytes());

            //Insertar un archivo .xml
            session.add("src/Ejercicio401/Alumno.xml",bais);
        } catch (IOException e) {
            System.out.println("Error al añadir fichero");
        }

    }
}
