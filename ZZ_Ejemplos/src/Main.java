import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        try {
            escribirSecuencialBytes("src\\Ficheros\\Secuencial Bytes.txt");
            leerSecuencialBytes("src\\Ficheros\\Secuencial Bytes.txt");
        } catch (IOException e) {
            System.out.println("Se ha producido un error durante la escritura");;
        }
    }

    static void crearFichero(String path) throws IOException {
        File f = new File(path);
        if (!f.exists()){
            f.createNewFile();
        }
    }
    static void escribirSecuencialBytes(String path) throws IOException {
        File f = new File(path);
        FileWriter fw = null;
        char [] vectorContenido = "Este documento ha sido escrito mediante la escritura secuencial de bytes".toCharArray();
        try {
            crearFichero(path);
            fw = new FileWriter(f);
            for (int i = 0; i < vectorContenido.length; i++) {
                fw.write(vectorContenido[i]);
            }
            fw.append('.');
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        }finally {
            if (fw!=null){
                fw.close();
            }
        }
    }
    static void leerSecuencialBytes(String path) throws IOException {
        File f = new File(path);
        FileReader fr = null;
        try {
            if (!f.exists()){
                System.out.println("No se ha podido encontrar el fichero");
                return;
            }
            fr = new FileReader(f);

            //******************** ESCRIBE EL CONTENIDO DE FORMA SECUENCIAL ********************

            /*
            Cada vez que usamos el metood fr.read() saltamos una posicion de lectura cuidado al castear!!
            cuando casteamos usando el metodo fr.read() saltamos 2 posiciones
             */
            System.out.println("Contenido del archivo leido secuencialmente");
            int caracterLeido;
            while ((caracterLeido = fr.read()) != -1) {
                System.out.print((char) caracterLeido);
            }
            System.out.println(" ");

            // Es necesario cerrar el FileReader y volver a crearlo si no volveremos a leer desde el final del archivo
            fr.close();

            fr = new FileReader(f);

            //******************** ESCRIBE EL CONTENIDO VOLCANDO T0D0 EN UN ARRAY ********************

            System.out.println("Contenido del archivo volcando todo el contenido");
            char[] vectoreDeBytesLeidos = new char[(int) f.length()];
            //Vuelco t0do el contenido del archivo en el vector de caracteres
            fr.read(vectoreDeBytesLeidos);
            String contenidoArchivoComoString = new String(vectoreDeBytesLeidos);
            System.out.println(contenidoArchivoComoString);

        }catch (IOException e){
            System.out.println("Error en la lectura");
        }finally {
            if(fr!=null){
                fr.close();
            }
        }
    }
}