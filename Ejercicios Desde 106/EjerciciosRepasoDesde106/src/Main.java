import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static Agenda miAgenda;
    static int continuar=0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        miAgenda = new Agenda();
        miAgenda.listaContactos.add(new Contacto("Mateo","Carballo",123456789));
        miAgenda.listaContactos.add(new Contacto("Juan","Pereira",968539876));
        miAgenda.listaContactos.add(new Contacto("Alberto","Perez",521489632));
        try {
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
                    case 3 -> miAgenda.eliminarContacto();
                    case 0-> cerrarPrograma();
                    default -> System.out.println("Fuera de rango");
                }
                miAgenda.ordenarListaContactos();
            }while(continuar!=0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void cerrarPrograma() {
        continuar=10;
    }

    public static String preguntaDevuelveString(String printPorPantalla){
        while(true){
            try {
                System.out.println(printPorPantalla);
                return br.readLine();
            } catch (IOException e) {
                System.out.println("Ha ocurrido algun error revisar que los datos introducidos sean correctos");
            }
        }
    }

    public static int preguntaDevuelveInt() {
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