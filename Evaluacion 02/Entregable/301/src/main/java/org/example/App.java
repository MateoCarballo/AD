package org.example;

import Entity.Doctor;
import Entity.Hospital;
import Entity.Paciente;
import Repository.*;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class App {
    static Session session;
    static RepoDoctor repoDoctor;
    static RepoPaciente repoPaciente;
    static RepoCita repoCita;
    static RepoRecibe repoRecibe;
    static RepoTratamiento repoTratamiento;
    static RepoHospital repoHospital;
    public static void main( String[] args ) {
        final String PATRON_MENU = "^\\d{1}$";
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean continuar = true;

        session = HibernateUtil.get().openSession();
        repoDoctor = new RepoDoctor(session);
        repoPaciente = new RepoPaciente(session);
        repoCita = new RepoCita(session);
        repoRecibe = new RepoRecibe(session);
        repoTratamiento = new RepoTratamiento(session);
        repoHospital = new RepoHospital(session);

        while (continuar) {
            // Creamos el StringBuilder para el menú
            StringBuilder menu = new StringBuilder();
            menu.append("-------------------------------------------------\n")
                    .append("1. Opción 1\n")
                    .append("2. Opción 2\n")
                    .append("3. Opción 3\n")
                    .append("4. Opción 4\n")
                    .append("5. Oción 5\n")
                    .append("6. Opción 6\n")
                    .append("7. Opción 7\n")
                    .append("8. Opción 8\n")
                    .append("9. Opción 9\n")
                    .append("0. Cerrar aplicación\n")
                    .append("-------------------------------------------------\n")
                    .append("Seleccione una opción: ");

            // Imprimimos el menú en consola
            System.out.print(menu);

            try {
                String input="";
                do {
                    try{
                         input = br.readLine();
                    }catch(IOException ioe){
                        System.out.println("Error al leer los datos por teclado");
                    }
                }while(!comprobarPatronRegex(PATRON_MENU,input));

                int opcion = Integer.parseInt(input);

                // TODO No me deja usar el switch mejorado
                switch (opcion) {
                    case 0:
                        System.out.println("Cerrando aplicación...");
                        continuar = false;
                        break;
                    case 1:
                        System.out.println("Has seleccionado la Opción 1.");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2.");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la Opción 3.");
                        break;
                    case 4:
                        System.out.println("Has seleccionado la Opción 4.");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la Opción 5.");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la Opción 6.");
                        break;
                    case 7:
                        System.out.println("Has seleccionado la Opción 7.");
                        break;
                    case 8:
                        System.out.println("Has seleccionado la Opción 8.");
                        break;
                    default:
                        System.out.println("Opción no válida, por favor seleccione una opción del 0 al 8.");
                        break;
                }

            } catch (NumberFormatException e) {
                // En caso de error (entrada no válida o error de I/O)
                System.out.println("Error en la entrada. Por favor, ingrese un número válido.");
            }
        }

        /**
         * 1. OPERACIONES SOBRE DOCTOR >--< COMPLETADO
         *         repoDoctor.crear(2000,"Doctor2", "Especialidad2", "222555888");
         *         repoDoctor.modificarDoctor(2000,"Cambiado","Cambiado","Cambiado");
         *         repoDoctor.borrarPorId(3);
         */

        /**
         * 2. OPERACIONES SOBRE PACIENTE >--< COMPLETADO
         *         repoPaciente.crear(2000,"Creado", LocalDate.of(2000,1,1),"Creada");
         *         repoPaciente.borrar("Ana Lopez");
         *         repoPaciente.modificarPaciente(1,"nuevo nombre",LocalDate.of(2000,5,11), "nueva direccion");
         */

        /**
         * 3. ASIGNAR A UN DOCTOR UN PACIENTE (DAR CITA) >--< COMPLETADO
         *         Buscamos el id del doctor asociado al nombre
         *         asignarDotorPaciente("Doctor","Paciente");
         */

        /**
         * 4. INDICAR LA FECHA FIN DEL TRATAMIENTO DE UN PACIENTE >--< COMPLETADO
         *         LocalDate fechaComienzo = LocalDate.of(2025,1,10);
         *         LocalDate fechaFin  = LocalDate.of(2025,02,10);
         *         System.out.println(repoTratamiento.asignarFechaTratamiento(
         *                 "Terapia Neurológica",
         *                 "María Gómez",
         *                 fechaComienzo,
         *                 fechaFin));
         */
        /**
        * 5. CAMBIAR EL HOSPITAL DE UN TRATAMIENTO >--< COMPLETADO
         *         repoTratamiento.modificarHospital(1,"Hospital Central");
         *         System.out.println(repoHospital.anhadirNuevoTratamiento(
         *                 1,
         *                 "Hospital del Norte",
         *                 "Hospital Central"));
        */


        /**
         * 6. MOSTRAR DATOS PACIENTE (Introduciendo nombre) >--< COMPLETADO
         *         System.out.println(repoPaciente.mostrarTodosDatos("Carlos Martínez"));
         *         System.out.println(repoPaciente.mostrarTodosDatos("Juan Perez"));
         */


        /**
         * 7. MOSTRAR LOS TRATAMIENTOS Y LOS DATOS DE LOS HOSPITALES EN LOS QUE SE REALIZAN -- BUSCAR EL HOSPITAL Y LOS TRATAMIENTOS >--< COMPLETADO
         *         Hospital hospital = (repoHospital.mostrarTratamientos("Hospital Universitario"));
         *         System.out.println(hospital.escribirHospitalCompleto());
         */


        /**
         * 8. MOSTRAR EL NUMERO TOTAL DE TRATAMIENTOS QUE TIENE CADA HOSPITAL (ENTRADA NOMBRE HOSPITAL)
         *         List<Hospital> listadoImprimir = repoHospital.mostratTodosTratamientosTodosHospitales();
         *         for (Hospital h : listadoImprimir){
         *             System.out.println(h.escribirHospitalCompleto());;
         *         }
         */
        mostrarBarraDeCarga(10);
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static void asignarDotorPaciente(String nombreDoctor, String nombrePaciente) {
        Doctor doctor = repoDoctor.buscarDoctor(nombreDoctor);
        Paciente paciente = repoPaciente.buscarPaciente(nombrePaciente);
        repoCita.crearCita(paciente, doctor);
    }

    // Método que simula la barra de carga de 5 segundos
    private static void mostrarBarraDeCarga(int segundos) {
        int totalTiempo = segundos * 1000; // Convertir a milisegundos
        int tiempoRestante = totalTiempo;
        int intervalo = 100; // Intervalo de actualización de la barra

        System.out.println("Iniciando la barra de carga...");

        // Realizamos el bucle de la barra de carga
        for (int i = 0; i <= totalTiempo; i += intervalo) {
            tiempoRestante = totalTiempo - i;
            int porcentaje = (int) ((i / (float) totalTiempo) * 100);

            // Limpiamos la línea anterior para que la barra se actualice
            System.out.print("\r[");
            int cantidad = porcentaje / 2; // Calculamos el número de caracteres para la barra
            for (int j = 0; j < cantidad; j++) {
                System.out.print("#");
            }
            for (int j = cantidad; j < 50; j++) {
                System.out.print(" ");
            }
            System.out.print("] " + porcentaje + "% - " + tiempoRestante / 1000 + " segundos restantes");

            try {
                Thread.sleep(intervalo); // Esperamos 100ms entre actualizaciones
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\nCierre completado.");
    }

    private static boolean comprobarPatronRegex(String patron, String cadenaComprobar){
        return Pattern.matches(patron, cadenaComprobar);
    }
}