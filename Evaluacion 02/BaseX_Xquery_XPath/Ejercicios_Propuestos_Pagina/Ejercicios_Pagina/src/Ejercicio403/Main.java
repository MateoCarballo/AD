package Ejercicio403;

import org.basex.examples.api.BaseXClient;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
Se pide desarrollar una aplicación Java que permita gestionar bases de datos. Para ello el sistema debe permitir:

Gestión de bases de datos:

Crear, si no existe, una base de datos con el nombre que elija el usuario.
Utiliza una base de datos ya existente en el caso de que exista.
Eliminar una base de datos.
Gestionar los documentos de las bases de datos:

Crear una estructura XML para los documentos de esa base de datos.
Añadir nuevos documentos siguiendo la estructura XML.
Modificar los valores de un documento.
Eliminar un documento.
Para cada base de datos se debe poder:

Consultar el número de documentos.
Listar la información por cada campo que tenga la estructura XML.
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        String entradaTeclado = null;
        boolean repetir = true;

        do {
            try {
                System.out.println("""
                        1. Crear/usar dataset.
                        2. Eliminar dataset.
                        3. Crear estructura XML.
                        4. Añadir nuevo documento.
                        5. Modificar archivo.
                        6. Eliminar documento.
                        7. Numero total documentos.
                        8. Listar toda la informacion de todos los documentos.
                        0. Salir.
                        """);
                entradaTeclado = br.readLine();
            } catch (IOException e) {
                System.out.println("Error al recoger datos del usuario");
            }
            switch (Integer.parseInt(entradaTeclado)) {
                case 1 -> {
                    crearDataset();
                }
                case 2 -> {
                    eliminarDataset();
                }
                case 3 -> {
                    crearEstructuraXml();
                }
                case 4 -> {
                    añadirDocumentoXml();
                }
                case 5 -> {
                    modificarArchivo();
                }
                case 6 -> {
                    eliminarDocumento();
                }
                case 7 -> {
                    numeroTotalDocumentos();
                }
                case 8 -> {
                    listarContenidoTodosDocumentos();
                }
                case 0 -> {
                    repetir = false;
                }
                default -> {
                    System.out.println("Opcion fuera de rango");
                }
            }
        } while (repetir);
    }

    private static void crearDataset() {
        String entradaTeclado = null;
        System.out.println("Introduce el nombre para crear/usar dataset");
        try {
            entradaTeclado = br.readLine().trim();
        } catch (IOException e) {
            System.out.println("Error al recoger por teclado el nombre del dataset a crear/usar");
        }

        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            BaseXClient.Query query = session.query("db:exists('" + entradaTeclado + "')");

            //Si no existe creamos dataset con un helloworld
            if (!("true".equalsIgnoreCase(query.next()))) {
                InputStream bais = new ByteArrayInputStream("<content>Hello world!</content>".getBytes());
                session.create(entradaTeclado, bais);
                System.out.println("Dataset creado con nombre -> " + entradaTeclado);
            } else {
                System.out.println("Usando dataset con nombre -> " + entradaTeclado);
            }

        } catch (IOException e) {
            System.out.println("Error al crear/conectarse con BaseX");
            e.printStackTrace();
        }
    }

    private static void eliminarDataset() {
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "abc123")) {
            //Imprime la lista de bases de datos
            BaseXClient.Query q = session.query("db:list()");
            while(q.more()){
                System.out.println(q.next());
            }
            String nombreDb = null;
            //Recogemos cual queremos eliminar. Podria comprobar que exista antes de nada
            try {
                nombreDb = br.readLine();
            } catch (IOException e) {
                System.out.println("Error al leer datos por teclado");
            }

            BaseXClient.Query query = session.query("db:exists('" + nombreDb + "')");
            if ("true".equalsIgnoreCase(query.next())) {
                session.execute("drop db " + nombreDb);
                System.out.println("Realizado borrado");
            } else {
                System.out.println("No existe ninguna base de datos con ese nombre");
            }
        } catch (IOException e) {
            System.out.println("Error en al conexion para borrar db");
            e.printStackTrace();
        }

    }

    private static void crearEstructuraXml() {
    }

    private static void añadirDocumentoXml() {
    }

    private static void modificarArchivo() {
    }

    private static void eliminarDocumento() {
    }

    private static void numeroTotalDocumentos() {
    }

    private static void listarContenidoTodosDocumentos() {
    }
}
