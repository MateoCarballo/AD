import java.io.*;
import java.sql.SQLOutput;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        final String RUTA = "src/Archivos";
        final String NOMBRE_ARCHIVO_FILEWRITER = "Escrito con FileWriter.txt";
        final String NOMBRE_ARCHIVO_BUFFERWRITER = "Escrito con BufferWriter.txt";
        final String NOMBRE_ARCHIVO_DATAOUTPUTSTREAM = "Escrito con DataOutputStream.txt";
        final String NOMBRE_ARCHIVO_OBJECTOUTPUTSTREAM = "Escrito con ObjectOutputStream.dat";

        //CREACION DEL DIRECTORIO
        crearDirectorio(RUTA);

        //CREACION DE LOS ARCHIVOS
        crearFichero(RUTA,NOMBRE_ARCHIVO_FILEWRITER);
        crearFichero(RUTA,NOMBRE_ARCHIVO_BUFFERWRITER);
        crearFichero(RUTA,NOMBRE_ARCHIVO_DATAOUTPUTSTREAM);
        crearFichero(RUTA,NOMBRE_ARCHIVO_OBJECTOUTPUTSTREAM);

        //ESCRIBIR ARCHIVOS CON 
        escribirArchivoFileWriter(RUTA + File.separator + NOMBRE_ARCHIVO_FILEWRITER );
        escribirArchivoBufferedWriter(RUTA + File.separator + NOMBRE_ARCHIVO_BUFFERWRITER);
        escribirArchivoDataOutputStream(RUTA + File.separator + NOMBRE_ARCHIVO_DATAOUTPUTSTREAM);
        escribirArchivoObjectOutputStream(RUTA + File.separator + NOMBRE_ARCHIVO_OBJECTOUTPUTSTREAM);

        //LEER ARCHIVOS CON LOS EQUIVALENTES A LA CLASE USADA PARA ESCRIBIR

        leerArchivoFileReader(new File(RUTA + File.separator + NOMBRE_ARCHIVO_FILEWRITER));
        leerArchivoBufferedReader(new File(RUTA + File.separator + NOMBRE_ARCHIVO_FILEWRITER));
        leerArchivoDataInputStream(new File(RUTA + File.separator + NOMBRE_ARCHIVO_FILEWRITER));
        leerArchivoObjectInputStream(new File (RUTA + File.separator + NOMBRE_ARCHIVO_OBJECTOUTPUTSTREAM));
    }

    private static void leerArchivoObjectInputStream(File file) {
        Persona objetoLeido = new Persona();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            objetoLeido = (Persona) ois.readObject();
            System.out.println(objetoLeido);
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Capturada excepcion ");
        }

    }

    private static void escribirArchivoObjectOutputStream(String ruta) {

        Persona objetoEscrito = new Persona("nombre","apellidos",30);
        File f = new File(ruta);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));) {
            oos.writeObject(objetoEscrito);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerArchivoDataInputStream(File file) {

        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))){

            System.out.println("*************************** LECTURA CON DATAINPUTSTREAM ***********************");
            dis.readChar();
        }catch (IOException e){
            System.out.println("Error durante la lectura");
        }
    }

    private static void leerArchivoBufferedReader(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            System.out.println("*************************** LECTURA CON BUFFEREDREADER ***********************");
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine());
            System.out.println(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leerArchivoFileReader(File file) {

        try(FileReader fr = new FileReader(file)) {
            int lecturaEnInt = fr.read();
            System.out.print((char)lecturaEnInt);

            while (lecturaEnInt != -1){
                lecturaEnInt= fr.read();
                System.out.print((char) lecturaEnInt);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static void escribirArchivoDataOutputStream(String ruta) {
        File f = new File(ruta);

        try(DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)))){
            dos.writeUTF("Esto es un char introducido como int"+"\n");
            dos.writeChar(100);
            dos.writeUTF("Esto es un int"+"\n");
            dos.writeInt(2);
            dos.writeUTF("Esto es un boolean");
            dos.writeBoolean(true);
        }catch (IOException e){
            System.out.println("Error durante la escritura con BufferedWriter");
        }
    }

    private static void escribirArchivoBufferedWriter(String ruta) {
        File f = new File(ruta);
        char [] vectorChar = {'V','E','C','T','O','R'};
        String [] vectorString = {"Vector","de","Strings"};
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))){
            bw.write("Esto es un String"+"\n");
            bw.write("Cadena");
            bw.write("Esto es un int"+"\n");
            bw.write(5);
            bw.write("Esto es un vector de chars"+"\n");
            bw.write(vectorChar+"\n");
            bw.write("Esto es un vector de Strings"+"\n");
            bw.write(Arrays.toString(vectorString)+"\n");

        }catch (IOException e){
            System.out.println("Error durante la escritura con BufferedWriter");
        }
    }

    private static void escribirArchivoFileWriter(String ruta) {
        File f = new File(ruta);
        char [] vectorChar = {'V','E','C','T','O','R'};
        String [] vectorString = {"Vector","de","Strings"};
        try (FileWriter fw = new FileWriter((f))){
            fw.write("Esto es un String"+"\n");
            fw.write("Cadena");
            fw.write("Esto es un int"+"\n");
            fw.write(5);
            fw.write("Esto es un vector de chars"+"\n");
            fw.write(vectorChar+"\n");
            fw.write("Esto es un vector de Strings"+"\n");
            fw.write(Arrays.toString(vectorString)+"\n");

        }catch (IOException e){
            System.out.println("Error durante la escritura");
        }

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

}