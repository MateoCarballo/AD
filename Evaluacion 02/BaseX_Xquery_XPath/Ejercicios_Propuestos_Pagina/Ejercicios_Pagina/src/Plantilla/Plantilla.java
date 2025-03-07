package Plantilla;

import org.basex.examples.api.BaseXClient;

import javax.management.Query;
import java.io.*;

public class Plantilla {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final static String HOST = "localhost";
    final static int PORT = 1984;
    final static String USER = "admin";
    final static String PWD = "abc123";

    public static void main(String[] args) {
        String entradaTeclado = null;
        boolean repetir = true;

        do {
            try {
                System.out.println("""
                        1. Crear/usar dataset.
                        2. Eliminar dataset.
                        3. Añadir archivo xml.
                        4. Modificar archivo xml.
                        5. Eliminar archivo xml.
                        0. Salir.
                        """);
                entradaTeclado = br.readLine();
            } catch (IOException e) {
                System.out.println("Error al recoger datos del usuario");
            }
            switch (Integer.parseInt(entradaTeclado)) {
                case 1 -> {
                    crearDatabase();
                }
                case 2 -> {
                    eliminarDataset();
                }
//                case 3 -> {
//                    añadirArchivo();
//                }
//                case 4 -> {
//                    modificarArchivo();
//                }
//                case 5 -> {
//                    eliminarArchivo();
//                }
                case 0 -> {
                    repetir = false;
                }
                default -> {
                    System.out.println("Opcion fuera de rango");
                }
            }
        } while (repetir);
    }

    private static void crearDatabase() {
        try (BaseXClient session = new BaseXClient(HOST, PORT, USER, PWD)) {
            InputStream bais = new ByteArrayInputStream("<content>\n    HelloWorld!\n</content>".getBytes());
            session.create("Example", bais);
            System.out.println(session.info());
            System.out.println("Creada database");
        } catch (IOException e) {
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }
    }

    private static void eliminarDataset(){
        try (BaseXClient session = new BaseXClient(HOST, PORT, USER, PWD)) {
            BaseXClient.Query query = session.query("drop db Example");
            query.execute();
            System.out.println(session.info());
            System.out.println("Eliminada DB 'Example'");
        } catch (IOException e) {
            System.out.println("Error en la conexion");
            e.printStackTrace();
        }
    }
}
