package EjerciciosExamen.resources;

import EjerciciosExamen.Usuario;

import java.io.*;

public class Examen {
    final static String RUTA_CARPETA = "src" + File.separator + "EjerciciosExamen" + File.separator + "resources";
    final static String NOMBRE_ARCHIVO_DAT = "ejercicio2.dat";
    public static void main(String[] args) throws IOException {
        ejercicio2();
    }

    private static void ejercicio2() throws IOException {
           /*ejercicio 2_
         .creación fichero.
        clase Usuario para almacenarlo.
        .Almacena objeto.
        .Utiliza clases binarias.
        .Escritura fichero.
        .Lectura fichero.*/


        ejercicio2CrearDirectorio(RUTA_CARPETA);
        ejercicio2CrearArchivo(RUTA_CARPETA + File.separator + "ejercicio2.dat");
        ejercicio2AlmacenarObjeto(new Usuario("nombre","contrasena","usuario"));
        ejercicio2EscrituraArchivo("Fichero escrito en binario.txt");
        ejercicio2LecturaFichero(RUTA_CARPETA + File.separator + "Fichero escrito en binario.txt");

    }

    private static void ejercicio2LecturaFichero(String rutaCompletaAlArchivo) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(rutaCompletaAlArchivo))){
            StringBuilder reconstruccion = new StringBuilder("");
            int enteroLeido = fis.read();
            while (enteroLeido != -1) {
                reconstruccion.append((char) enteroLeido);
                enteroLeido = fis.read();
            }
            System.out.println(reconstruccion);

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Lanzada excepcion FileNotFoundException desde el metodo \" ejercicio2LecturaFichero \" ");
        } catch (IOException e) {
            throw new IOException("Lanzada excepcion IOException desde el metodo \" ejercicio2LecturaFichero \" ");
        }
    }

    private static void ejercicio2EscrituraArchivo(String nombreArchivo) {
        File f = new File(RUTA_CARPETA + File.separator + nombreArchivo );
        try (FileOutputStream fos = new FileOutputStream(f)){
            if (!f.exists()){
                if (f.createNewFile()){
                    System.out.println("Creado el archivo -> " + f.getPath());
                }
            }
            String contenido = "Este es el contenido que quiero escribir.";
            char [] arrayDeChars = contenido.toCharArray();
            for (char c : arrayDeChars) {
                fos.write(c);
            }
            fos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static void ejercicio2AlmacenarObjeto(Usuario user) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(RUTA_CARPETA + File.separator + NOMBRE_ARCHIVO_DAT)))) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void ejercicio2CrearArchivo(String archivo) throws IOException {
        File f = new File(archivo);
        try {
            if(f.createNewFile()){
                System.out.println("Creado archivo en la ubicacion -> " + f.getPath());
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private static void ejercicio2CrearDirectorio(String directorio){
        File f = new File(directorio);
        if (!f.exists()){
            if (f.mkdirs()){
                System.out.println("creado directorio -> " +  f.getPath());
            }
        }
    }
}
