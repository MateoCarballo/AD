import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main implements FilenameFilter {
    static final String  RUTA_ARCHIVO = "PreparacionExamen/resources/archivos";
    public static void main(String[] args) {
        crearDirectorio();
        crearFichero("Archivo de texto.txt");
        crearFichero("Archivo binario .bin");
        crearFichero("Archivo de datos.dat");
        buscarFicheroPorExtension(RUTA_ARCHIVO, ".txt");
        buscarFicheroPorNombre(RUTA_ARCHIVO, "Archivo de texto");

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



    private static void buscarFicheroPorExtension(String directorioDeBusqueda, String extension) {
        File pathToSearch = new File(directorioDeBusqueda);
        ComprobarExtension cf = new ComprobarExtension(extension);

        String[] lista = pathToSearch.list();
        for (String item: lista){
            if (cf.accept(pathToSearch,item)){
                System.out.println("El directorio contiene un archivo con extension "+ cf.getExtension());
            }
        }
    }

    private static void buscarFicheroPorNombre(String directorioDeBusqueda, String nombreArchivo) {
        File pathToSearch = new File(directorioDeBusqueda);
        ComprobarArchivo comprobarArch = new ComprobarArchivo(nombreArchivo);

        String[] lista = pathToSearch.list();
        for (String item: lista){
            if (comprobarArch.accept(pathToSearch,item)){
                System.out.println("El directorio contiene un archivo llamado "+ comprobarArch.getNombreArchivo());
            }
        }
    }

    private static void escribirFicheroBinario() {
        
    }

    @Override
    public boolean accept(File dir, String name) {
        return false;
    }
}