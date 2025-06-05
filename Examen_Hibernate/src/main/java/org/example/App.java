package org.example;

import org.hibernate.Session;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Arranco clase main de App");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            int opcionSeleccionada = printearMenuOpciones();
            switch(opcionSeleccionada){
                case 1 ->{

                }
            }
            System.out.println("Conexión a Hibernate exitosa.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int printearMenuOpciones(){
        int indiceSeleccionado;
        boolean datoValido = false;

        do{
            Scanner sc = new Scanner(System.in);
            String mensaje = "1. Crear actor\n" +
                    "2. Eliminar actor\n" +
                    "3. Crear pelicula\n" +
                    "4. Eliminar pelicula\n" +
                    "5. Crear premio\n" +
                    "6. Eliminar premio\n" +
                    "7. Modificar género de una película\n" +
                    "8. Asignar un premio a una película\n" +
                    "9. Asignar un actor a una película\n" +
                    "10. Asignar una película a una sala en una fecha y una hora\n" +
                    "11. Consulta 1\n" +
                    "12. Consulta 2\n" +
                    "13. Consulta 3\n" +
                    "14. Consulta 4\n" +
                    "15. Salir";
            System.out.println(mensaje);
            try{
                indiceSeleccionado = Integer.parseInt(sc.nextLine());
                if (indiceSeleccionado < 15 && indiceSeleccionado > 0) datoValido = true;
            }catch (NumberFormatException nfe){
                System.out.println("Debes introducir un numero valido");
            }
        }while(!datoValido);
        return indiceSeleccionado;
    }
}
