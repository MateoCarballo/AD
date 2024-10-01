import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    /*
    Ejercicio 112
    Desarrolla un programa Java que permita:
    Escribir en un fichero binario alumnos.dat de forma aleatoria la siguiente información:
    Apellido "FERNANDEZ","LOPEZ","GOMEZ","CHEN","SERRANO","CASILLAS", "ALONSO"
    Edad 17, 20, 18, 17, 19, 21, 20
    Nota
    Visualizar el contenido del fichero anterior de forma aleatoria.
     */

    public static void main(String[] args) {
        File f = new File ("src\\alumnos.dat");
        String[] apellido = {"FERNANDEZ","LOPEZ","GOMEZ","CHEN","SERRANO","CASILLAS", "ALONSO"};
        int[] edad = {17, 20, 18, 17, 19, 21, 20};
        double[] nota = {7.5, 4.2, 6.5, 8.0, 3.2, 9.2, 9.9};
        try {
            if (!f.exists()&&(f.createNewFile())){
                System.out.println("Fichero creado con exito");
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error del tipo -> "+ Arrays.toString(e.getStackTrace()));;
        }
            escribirDatos(f,apellido,edad,nota);
            leerDatos(f);


    }

    private static void leerDatos(File f) {
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(f,"r");
            raf.seek(0);
            System.out.println(" APELLIDO   "+"-"+"  EDAD  "+"-"+"   NOTA   ");
            for (int i = 0; i < 7 ; i++) {
                System.out.println(raf.readUTF()+"------"+raf.readInt()+"------"+raf.readDouble());
            }
            raf.close();
        } catch (IOException e) {
            System.out.println("Error en la escritura");
        }
    }

    private static void escribirDatos(File f, String[] apellido, int[] edad, double[] nota ) {
        RandomAccessFile raf;
        try {
            raf = new RandomAccessFile(f,"rw");
            raf.seek(raf.length());

            for (int i = 0; i < apellido.length; i++) {
                raf.writeUTF(apellido[i]);
                raf.writeInt(edad[i]);
                raf.writeDouble(nota[i]);
            }
            raf.close();
        } catch (IOException e) {
            System.out.println("Error en la escritura aleatoria");
        }
    }


}