import java.io.*;

public class Main {
    public static void main(String[] args) {

        final String RUTA = "src/Archivos";
        final String NOMBRE_ARCHIVO = "Archivo de texto2.txt";
        final String NOMBRE_ARCHIVO2 = "Archivo de texto.txt";

        crearDirectorio(RUTA);
        crearFichero(RUTA,NOMBRE_ARCHIVO);
        escribirSecuencial("Esta cadena la escribo secuancialmente",RUTA+"/"+NOMBRE_ARCHIVO);
        escribirAleatorio("Esta cadena la escribo aleatoriamente",RUTA+"/"+NOMBRE_ARCHIVO);
        leerArchivo(RUTA+"/"+NOMBRE_ARCHIVO2);

    }
    public static void crearDirectorio(String rutaDirectorio){
        File dir = new File(rutaDirectorio);
        if(!dir.exists()){
                dir.mkdirs();
            }
        }


    public static void crearFichero(String path, String nombreFichero){
        try {
            File f = new File(path,nombreFichero);
            if (!f.exists()){
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirSecuencial(String contenido,String ruta){
        File f = new File(ruta);
        try( FileWriter fw = new FileWriter(f,true)) {

            char [] cadenaParaEscribir = contenido.toCharArray();

            for (char caracter : cadenaParaEscribir) {
                fw.write(caracter);
            }
                fw.write('\n');
            fw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void escribirAleatorio(String escribir, String ruta) {

        try(RandomAccessFile raf = new RandomAccessFile(ruta,"rw");) {
            raf.seek(raf.length());
            raf.writeBytes(escribir);
            raf.writeBytes("\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerArchivo(String rutaAlArchivo) {
        try(RandomAccessFile raf = new RandomAccessFile(rutaAlArchivo,"r")){
            raf.seek(0);
            char[] contenidoArchivo = new char[(int) raf.length()];
            for (int i = 0; i < raf.length() ; i++) {
                contenidoArchivo[i] = raf.readChar();
            }

            //System.out.println(contenidoArchivo.toString());
//            raf.seek(0);
//            String contenidoString = raf.readUTF();
//            System.out.println(contenidoString);

            //raf.readChar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}