
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    static String[][] reservasPorFranja;
    static int aforo;
    public static void main(String[] args) throws Exception {
        //ejercicio101();
        //ejercicio102();
        // ejercicio103();
        //ejercicio104();
        ejercicio105();

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
        boolean continuar = true;

        int longitudPiscina = 0, anchuraPiscina=0, longitudParcela=0, anchuraParcela=0, aforo=0;
        double superficiePiscina = 1;
        double superficieParcela = 1;
        do {
            System.out.println("Introduzca la longitud de la piscina:");
            longitudPiscina= pedirIntPorTeclado("Has introducido mal la longitud piscina");

            System.out.println("Introduzca la anchura de la piscina:");
            anchuraPiscina=pedirIntPorTeclado("Has introducido mal la anchura de la piscina");

            System.out.println("Introduzca la longitud de la parcela:");
            longitudParcela=pedirIntPorTeclado("Has introducido mal la longitud de la parcela");

            System.out.println("Introduzca la anchura de la parcela:");
            anchuraParcela=pedirIntPorTeclado("Has introducido mal la anchura de la parcela");

            superficiePiscina = anchuraPiscina * longitudPiscina;
            superficieParcela = anchuraParcela * longitudParcela;

                if (superficieParcela >= superficiePiscina) {
                    aforo = (int) superficiePiscina/2;
                    System.out.println("El aforo de la piscina es de " + aforo + " personas");
                } else{
                    aforo = (int) superficieParcela/2;
                    System.out.println("El aforo de la piscina es de " + aforo + " personas");

                }
        }while(true);

    }

    public static int pedirIntPorTeclado(String mensaje) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            try{
                return Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println(mensaje);
            }
        }
    }


    public static void ejercicio105() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean continuar = true;

        definirPiscina();
        do {
            //La primera de las dimensiones son las franjas horarias la segunda son los DNI
            reservasPorFranja=new String [6][aforo];
            reservar();
        }while(continuar);
    }

    public static void definirPiscina() throws Exception {
        int longitudPiscina = 0, anchuraPiscina=0, longitudParcela=0, anchuraParcela=0;
        double superficiePiscina = 1;
        double superficieParcela = 1;
        System.out.println("Introduzca la longitud de la piscina:");
        longitudPiscina= pedirIntPorTeclado("Has introducido mal la longitud piscina");

        System.out.println("Introduzca la anchura de la piscina:");
        anchuraPiscina=pedirIntPorTeclado("Has introducido mal la anchura de la piscina");

        System.out.println("Introduzca la longitud de la parcela:");
        longitudParcela=pedirIntPorTeclado("Has introducido mal la longitud de la parcela");

        System.out.println("Introduzca la anchura de la parcela:");
        anchuraParcela=pedirIntPorTeclado("Has introducido mal la anchura de la parcela");

        superficiePiscina = anchuraPiscina * longitudPiscina;
        superficieParcela = anchuraParcela * longitudParcela;

        if (superficieParcela >= superficiePiscina) {
            aforo = (int) superficiePiscina/2;
            System.out.println("El aforo de la piscina es de " + aforo + " personas");
        } else{
            aforo = (int) superficieParcela/2;
            System.out.println("El aforo de la piscina es de " + aforo + " personas");
        }
    }

    public static void reservar() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int posicionLibre=0;
        int franjaParaComprobar=0;
        String dni="";
        try{

            do {
                System.out.println("Que numero de franja deseas reservar? (1-6)");
                franjaParaComprobar = Integer.parseInt(br.readLine());
                if(franjaParaComprobar <0 || franjaParaComprobar >6){
                    System.out.println("El numero está fuera de rango");
                }
            }while(franjaParaComprobar <0 || franjaParaComprobar >6);
            posicionLibre=comprobarDisponibilidad(franjaParaComprobar);

            if (posicionLibre!=0){
                do{
                    System.out.println("Introduce un DNI para reservar en la franja horaria "+franjaParaComprobar);
                    dni=br.readLine();
                }while(comprobarDNI(dni));
                reservasPorFranja[franjaParaComprobar][posicionLibre]=dni;
                System.out.println("Su reserva se ha completado");
            }else{
                System.out.println("No hay plazas libres para esa franja horaria "+franjaParaComprobar);
            }
        } catch (NumberFormatException e) {
            System.out.println("Has introducido algun dato mal");
        }
    }


    public static int comprobarDisponibilidad(int franjaParaComprobar)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       while(true){
           try{
               int posicionLibre=0;
               for (int i = 0; i < reservasPorFranja.length-1; i++) {
                    if (reservasPorFranja[franjaParaComprobar][i]==null){
                       posicionLibre=i;
                   }
               }
               return posicionLibre;
           } catch (Exception e) {
               System.out.println("Has introducido una franja fuera de rango o un dato del tipo incorrecto");
           }
       }
    }

    public static boolean comprobarDNI(String dni){
        //Devuelve falso para salir del bucle cuando el dni sea correcto
        String patronRegex = "^\\d{8}[A-HJ-NP-TV-Z]$";
        return !Pattern.matches(patronRegex, dni);
    }


}
