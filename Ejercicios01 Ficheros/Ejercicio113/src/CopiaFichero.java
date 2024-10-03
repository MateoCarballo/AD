
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class CopiaFichero {

    public static void main(String[] args) {
        /*
        Ejercicio 113
        Crea un directorio llamado directorio que tenga dos archivos: origen.txt y destino.txt.
        Crea una clase CopiaFichero, que implemente mét0d0 main. Utiliza las
        clases Paths y Files de la librería Java NIO,
        y el mét0d0 copy(), implementa un programa en Java que permita copiar
        el contenido del fichero origen.txt en el fichero destino.txt.
         */
        Path directorio = Paths.get("src/directorio");
        Path origen = Paths.get("src/directorio/origen.txt");
        Path destino = Paths.get("src/directorio/destino.txt");
        crearDirectorioYFicheros(directorio,origen,destino);
        String contenido = "Esta es la segunda vez que ejecuto el programa con el fichero creado";
        cargarContenidoFicheroOrigen(origen,contenido);
        copiarPrimerFicheroEnSegundoFichero(origen,destino);
    }

    private static void copiarPrimerFicheroEnSegundoFichero(Path origen, Path destino) {
        try {
            Files.copy(origen,destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error durante la copia del fichero" + e.getMessage());;
        }
    }

    private static void cargarContenidoFicheroOrigen(Path pathOrigen, String contenido) {
        try (BufferedWriter writer = Files.newBufferedWriter(pathOrigen, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            writer.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public static void crearDirectorioYFicheros(Path p,Path f1,Path f2){
        try {
            if (!Files.exists(p)){
                Path newDir = Files.createDirectories(p);
                Path newFile1 = Files.createFile(f1);
                Path newFile2 = Files.createFile(f2);
            }
        } catch (IOException e) {
            System.out.println("Error durante la creacion del directorio");;
        }
    }
}