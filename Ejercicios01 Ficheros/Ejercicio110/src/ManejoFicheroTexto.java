import java.io.*;
import java.util.Arrays;

public class ManejoFicheroTexto {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        File f = new File(".\\src\\destino.txt");
        if (!f.exists()){
            f.createNewFile();
        }
        FicheroTexto ftexto = new FicheroTexto(f);
        ftexto.borrarFichero();
        int menu=0;

        do {
            try{
                System.out.println("""
                ******************** MENU ********************
                1.- Escribir en el fichero.
                2.- Leer fichero.
                3.- Salir.
                """);
                menu = Integer.parseInt(br.readLine());
                switch (menu) {
                    case 1 -> {
                        System.out.println("Introduce el texto que quieres añadir");
                        ftexto.escribir(br.readLine());
                    }
                    case 2 -> {
                        System.out.println("Contenido del fichero"+ "\n");
                        ftexto.leer();
                    }

                    case 3 -> System.out.println("Gracias por usar este PROGRAMON");
                    default -> System.out.println("Introduce un valor valido (1-3)");
                }
            }catch (Exception e){
                System.out.println("Ha ocurrido un error del tipo "+ Arrays.toString(e.getStackTrace()));
            }

        }while (menu!=3);
    }
}
