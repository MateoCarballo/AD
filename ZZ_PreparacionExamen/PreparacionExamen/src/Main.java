import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final String  RUTA_ARCHIVO = "resources/archivos";
    public static void main(String[] args) {
        crearDirectorio();
        crearFichero("archivo.txt");
        crearFichero("archivo.bin");
        crearFichero("archivo.dat");
        escribirFicheroBinario();
    }

    private static void crearDirectorio() {
       File f = new File(RUTA_ARCHIVO);
       if (!f.exists()){
           f.mkdirs();
       }
    }

    private static void crearFichero(String nombreFichero) {
        File f = new File(RUTA_ARCHIVO,nombreFichero);
        try {
            if (!f.exists()){
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error durante la creacion del archivo");
        }
    }

    private static void escribirFicheroBinario() {
        
    }

}