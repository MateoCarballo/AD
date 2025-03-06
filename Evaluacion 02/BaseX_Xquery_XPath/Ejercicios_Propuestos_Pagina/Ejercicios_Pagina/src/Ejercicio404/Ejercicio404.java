package Ejercicio404;
/*
Escribe las consultas XQuery que permitan obtener la siguiente información así como el código Java que permita ejecutarlas:

Título y editorial de todos los libros.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y la editorial de cada libro deben estar separados por un guión medio (-).
El título de todos los libros de menos de 400 páginas.
Se debe obtener únicamente los datos, sin etiquetas.
La cantidad de libros de más de 400 páginas.
Una lista HTML con el título de los libros de la editorial O'Reilly Media ordenados por título.
Título y editorial de los libros de 2018 y 2019.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y la editorial deben ir dentro de los elementos <titulo> y <editorial> respectivamente.
Título y editorial de los libros con más de un autor.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y la editorial deben ir dentro de los elementos <titulo> y <editorial> respectivamente.
Título y año de publicación de los libros que tienen versión electrónica.
Los datos de cada libro deben estar dentro de un elemento <libro>.
El título y el año de publicación deben ir dentro de los elementos <titulo> y <fecha-publicacion> respectivamente.
Título de los libros que no tienen versión electrónica.
Se debe obtener únicamente los datos, sin etiquetas.
 */

import org.basex.examples.api.BaseXClient;

import java.io.IOException;

public class Ejercicio404 {
    public static void main(String[] args) {
        tituloYEditorial();
    }

    private static void tituloYEditorial() {
        /*
        Resultado
        <libro>
            <titulo>...</titulo>
            <editorial>...</editorial>
        </libro>
         */
        try(BaseXClient session = new BaseXClient("localhost",1984,"admin","abc123")){
            BaseXClient.Query query = session.query("for $libro in db:get('Fichero')/biblioteca/libros/libro\n" +
                                                    "return <libro>{$libro/titulo/text()} - {$libro/editorial/text()}</libro>");
            while(query.more()){
                System.out.println(query.next());
            }

        } catch (IOException e) {
            System.out.println(" Error al conectar con basex");
            e.printStackTrace();
        }
    }
}
