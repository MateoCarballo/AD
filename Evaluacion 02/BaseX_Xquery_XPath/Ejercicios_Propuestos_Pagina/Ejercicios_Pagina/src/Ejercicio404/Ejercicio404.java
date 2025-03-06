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
        try(BaseXClient session = new BaseXClient("localhost",1984,"admin","abc123")){
            //tituloYEditorial();
            //librosMenos400paginas();
            //numeroDeLibrosDeMasDe400Paginas();
            //listaHTMLEditorialOReillyMedia();
            //tituloYEditorialLibro18_19(session);
            //tituloAnhoPublicacionLibrosConVersionElectronica(session);
            tituloLibrosNoVersionElectronica(session);
        }catch (IOException e){
            System.out.println("Error al conectar con BaseX");
        }
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

    private static void librosMenos400paginas() {
        try(BaseXClient session = new BaseXClient("localhost",1984,"admin","abc123")){
        BaseXClient.Query queryPaginas = session.query("for $libro in db:get('Fichero')/biblioteca/libros/libro\n" +
                                                          "where $libro/paginas < 400\n"  +
                                                        "return $libro/titulo/text()");
        /*
        BaseXClient.Query queryPaginas = session.query("for $titulo in db:get('Fichero')/biblioteca/libros/libro[paginas<400]/titulo/text()\n" +
                                                        "return $titulo");
         */
        while(queryPaginas.more()){
            System.out.println(queryPaginas.next());
        }

        } catch (IOException e) {
            System.out.println(" Error al conectar con basex");
            e.printStackTrace();
        }
    }


    private static void numeroDeLibrosDeMasDe400Paginas() {
        try(BaseXClient session = new BaseXClient("localhost",1984,"admin","abc123")){
            BaseXClient.Query queryPaginas = session.query("count(db:get('Fichero')/biblioteca/libros/libro[paginas<400])");

            while(queryPaginas.more()){
                System.out.println(queryPaginas.next());
            }

        } catch (IOException e) {
            System.out.println(" Error al conectar con basex");
            e.printStackTrace();
        }
    }



    private static void listaHTMLEditorialOReillyMedia() {

        try(BaseXClient session = new BaseXClient("localhost",1984,"admin","abc123")){
        BaseXClient.Query query = session.query("<html>\n" +
                                                "<ul>\n" +
                                                "{for $elemento in db:get('Fichero')/biblioteca/libros/libro[editorial= \"O'Reilly Media\"]/titulo order by $elemento\n" +
                                                "return <li>{$elemento}</li>}\n" +
                                                "</ul>\n" +
                                                "</html>");

            while(query.more()){
                System.out.println(query.next());
            }
        }catch (IOException e){
            System.out.println("Error basex");
            e.printStackTrace();
        }
    }


    private static void tituloYEditorialLibro18_19(BaseXClient session) {
        try{
            BaseXClient.Query query = session.query("for $libro in db:get('Fichero')//libro[@publicacion = 2018 or @publicacion = 2019]\n"+
                    "return <libro>{$libro/titulo}{$libro/editorial}</libro>");
            while(query.more()){
                System.out.println(query.next());
            }
        } catch (Exception e) {
            System.out.println("Error en el metodo titulo editorial 2019 2019");
            e.printStackTrace();
        }
    }

    private static void tituloAnhoPublicacionLibrosConVersionElectronica(BaseXClient session) {
        try{
            BaseXClient.Query query = session.query("for $libro in db:get('Fichero')/biblioteca/libros/libro[edicionElectronica='true']\n" +
                                                    "return <libro>" +
                                                    "<titulo>{$libro/titulo/text()}</titulo>" +
                                                    "<fecha-publicacion>{$libro/@publicacion/string()}</fecha-publicacion>" +
                                                    "</libro>");
            /*
             BaseXClient.Query query = session.query("for $libro in db:get('Fichero')/biblioteca/libros/libro[edicionElectronica='true']\n" +
                                                    "return <libro publicacion = '{$libro/@publicacion}'>{$libro/titulo}</libro>");
             */
            while(query.more()){
                System.out.println(query.next());
            }
        }catch (IOException e){
            System.out.println("Error al filtar por edicion digital");
            e.printStackTrace();
        }
    }


    private static void tituloLibrosNoVersionElectronica(BaseXClient session) {
        try{
            BaseXClient.Query query = session.query("for $resultado in db:get('Fichero')/biblioteca/libros/libro[exists(edicionElectronica)]\n" +
                                                    "return $resultado/titulo/text()");
            while(query.more()){
                System.out.println(query.next());
            }
        } catch (IOException e) {
            System.out.println("Error en la consulta libros sin version electronica");
        }
    }

}
