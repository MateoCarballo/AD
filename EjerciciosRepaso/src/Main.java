
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) throws Exception {
        //ejercicio101();
        //ejercicio102();
        // ejercicio103();
        ejercicio104();

    }

    public static void ejercicio101(){
        String texto = "4;5;7;8;4";

        String[] subcadenas = texto.split(";");

        int resultadoSumaCaracteres=0;

        for (int i = 0; i <= subcadenas.length - 1; i++) {
            resultadoSumaCaracteres+=Integer.parseInt(subcadenas[i]);
        }
        System.out.println(resultadoSumaCaracteres);
    }

    public static void ejercicio102() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nombre ="";
        int edad=0;
        boolean continuar= true;
        do{
            try{
                System.out.println("Nombre del interesado?");
                nombre = br.readLine();

                System.out.println("Edad del interesado?");
                edad = Integer.parseInt(br.readLine());

            } catch (Exception e) {
                throw new Exception("Has introducido datos incorrectos");
            }
        }while(continuar);

        if (edad<65){
            System.out.println(nombre + " te quedan "+(65-edad)+" años para jubilarte");
        }
        if (edad>65){
            System.out.println(nombre+ " llevas "+(edad-65)+" años jubilado");
        }
    }

    public static void ejercicio103() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Producto> productos = new ArrayList<Producto>();
        Producto p;
        double totalCompra=0.0;
        boolean continuar= true;
        int numeroDeCiclos=0;
        do{
            p = new Producto();

            System.out.println("Nombre del producto?");
            p.nombre=br.readLine();
            System.out.println("Precio del producto?");
            p.precio=Double.parseDouble(br.readLine());

            productos.add(p);

            System.out.println("Quieres introducir mas productos?(S/N)");
            if (br.readLine().equalsIgnoreCase("N")){
                continuar=false;
            }
            numeroDeCiclos++;
        }while(continuar);

        for(Producto   // Tipo de variable primitiva de la lista que vamos a iterar
                prod  // Nombre de la variable local
                : productos) {  // Nombre de la lista que vamos a iterar
            totalCompra+=prod.precio;
        }
        System.out.println(totalCompra);
    }

    public static void ejercicio104() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean continuar = true,datosOK=false;

        int longitudPiscina = 0, anchuraPiscina=0, longitudParcela=0, anchuraParcela=0, aforo=0;
        double superficiePiscina = 1;
        double superficieParcela = 1;
        do {
            do{
                System.out.println("Introduzca la longitud de la piscina:");
                longitudPiscina=pedirLongitudPiscina();
            }while(longitudPiscina == 0);

            do{
                System.out.println("Introduzca la anchura de la piscina:");
                anchuraPiscina=pedirAnchuraPiscina();
            }while(anchuraPiscina == 0);

            do{
                System.out.println("Introduzca la longitud de la parcela:");
                anchuraPiscina=pedirLongitudParcela();
            }while(anchuraPiscina == 0);

            do{
                System.out.println("Introduzca la anchura de la parcela:");
                anchuraPiscina=pedirAnchuraParcela();
            }while(anchuraPiscina == 0);

                superficiePiscina = anchuraPiscina * longitudPiscina;
                superficieParcela = anchuraParcela * longitudParcela;

                if (superficieParcela >= superficiePiscina) {
                    aforo = (int) superficiePiscina/2;
                    System.out.println("El aforo de la piscina es de " + aforo + " personas");
                } else{
                    aforo = (int) superficieParcela/2;
                    System.out.println("El aforo de la piscina es de " + aforo + " personas");

                }
        }while((superficiePiscina==1) || (superficieParcela==1));

    }

    public static int pedirLongitudPiscina() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
        return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
        System.out.println("Has introducido mal la longitud");
        }
        return 0;
    }

    private static int pedirAnchuraPiscina() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try{
        return Integer.parseInt(br.readLine());
    } catch (NumberFormatException e) {
        System.out.println("Has introducido mal la anchura");
    }
    return 0;

}


    private static int pedirAnchuraParcela() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Has introducido mal la anchura");
        }
        return 0;
    }

    private static int pedirLongitudParcela() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            return Integer.parseInt(br.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Has introducido mal la longitud");
        }
        return 0;
    }

}
