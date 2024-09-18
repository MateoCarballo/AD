import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static Agenda miAgenda;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        miAgenda = new Agenda();
        try {
            int continuar=0;
            do{
                System.out.println("""
                Elige una de las opciones:
                1.Agregar un contacto.
                2.Mostrar un contacto.
                3.Eliminar un contacto.
                0.Cerrar el programa.
                """);
                continuar=Integer.parseInt( br.readLine());
                switch (continuar) {
                    case 1 -> miAgenda.anadirContacto();
                    case 2 -> miAgenda.mostrarContacto();
                    case 0 -> miAgenda.eliminarContacto();
                    default -> System.out.println("Fuera de rango");
                }
            }while(continuar!=0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static String preguntarNombre(){
        while(true){
            try {
                System.out.println("Nombre del nuevo contacto?");
                return br.readLine();
            } catch (IOException e) {
                System.out.println("Ha ocurrido algun error revisar que los datos introducidos sean correctos");
            }
        }
    }

    public static String preguntarApellidos() {
        while(true){
            try {
                System.out.println("Apellidos del nuevo contacto?");
                return br.readLine();
            } catch (IOException e) {
                System.out.println("Ha ocurrido algun error revisar que los datos introducidos sean correctos");
            }
        }
    }
    public static int preguntarNumero() {
        while(true){
            try {
                System.out.println("Numero del nuevo contacto?");
                return Integer.parseInt(br.readLine());
            } catch (IOException e) {
                System.out.println("Ha ocurrido algun error revisar que los datos introducidos sean correctos");
            }
        }
    }
}