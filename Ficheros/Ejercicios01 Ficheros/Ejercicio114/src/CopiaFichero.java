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

        /*
        Ejercicio 114
        Crea un subdirectorio llamado subdirectorio que esté vacío.
        A continuación, desarrolla un programa en Java que utilizando la librería Java NIO y el método move()
        permita mover el fichero destino.txt al subdirectorio, cambiándole el nombre al archivo: nuevoDestino.txt.
         */

        // PARTE DEL EJERCICIO 113
        Path directorio = Paths.get("src/directorio");
        Path ficheroOrigen = Paths.get("src/directorio/origen.txt");
        Path ficheroDestino = Paths.get("src/directorio/destino.txt");

        Path subdirectorio = Paths.get("src/directorio/subdirectorio");
        Path ficheroSubdirectorio = Paths.get("src/directorio/subdirectorio/nuevoDestino.txt");

        crearDirectorioYFicheros(directorio);
        String contenido = "Esta es la segunda vez que ejecuto el programa con el fichero creado";
        cargarContenidoFichero(ficheroOrigen,contenido);
        copiarPrimerFicheroEnSegundoFichero(ficheroOrigen,ficheroDestino);

        //PARTE DEL EJERCICIO 114
        crearDirectorioYFicheros(subdirectorio);
        moverFicheroYCambiarNombre(ficheroDestino,ficheroSubdirectorio);
    }

    public static void crearDirectorioYFicheros(Path p){
        try {
            if (!Files.exists(p)){
                Files.createDirectories(p);
            }
        } catch (IOException e) {
            System.out.println("Error durante la creacion del directorio");;
        }
    }

    private static void cargarContenidoFichero(Path pathOrigen, String contenido) {
        try (BufferedWriter writer = Files.newBufferedWriter(pathOrigen, StandardCharsets.UTF_8, StandardOpenOption.CREATE)) {
            writer.write(contenido);
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    private static void copiarPrimerFicheroEnSegundoFichero(Path origen, Path destino) {
        try {
            Files.copy(origen,destino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error durante la copia del fichero" + e.getMessage());;
        }
    }

    private static void moverFicheroYCambiarNombre(Path ficheroOrigen, Path rutaDestino) {
        try {
            Files.move(ficheroOrigen,rutaDestino, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error al mover al subdirectorio el archivo" + e.getMessage());;
        }
    }
}