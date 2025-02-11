package org.example;

import Entity.Doctor;
import Entity.Paciente;
import Repository.*;
import org.hibernate.Session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class App {
    static Session session;
    static RepoDoctor repoDoctor;
    static RepoPaciente repoPaciente;
    static RepoCita repoCita;
    static RepoRecibe repoRecibe;
    static RepoTratamiento repoTratamiento;
    static RepoHospital repoHospital;

    public static void main( String[] args ) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        session = HibernateUtil.get().openSession();
        repoDoctor = new RepoDoctor(session);
        repoPaciente = new RepoPaciente(session);
        repoCita = new RepoCita(session);
        repoRecibe = new RepoRecibe(session);
        repoTratamiento = new RepoTratamiento(session);
        repoHospital = new RepoHospital(session);

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


        // 3. ASIGNAR A UN DOCTOR UN PACIENTE (DAR CITA)

        // Buscamos el id del doctor asociado al nombre
        // TODO pendiente de comprobar que exista o crearlo si no existe
        //asignarDotorPaciente("Doctor","Paciente");

        // 4. INDICAR LA FECHA FIN DEL TRATAMIENTO DE UN PACIENTE
        // repoTratamiento.asignarFechaTratamiento("Rehabilitación Física","Carlos Martínez", LocalDate.of(2025,1,10),LocalDate.of(2025,02,10));

        // 5. CAMBIAR EL HOSPITAL DE UN TRATAMIENTO
        //repoTratamiento.modificarHospital(1,"Hospital Central");

        // 6. MOSTRAR DATOS PACIENTE (Introduciendo nombre)
        //System.out.println(repoPaciente.mostrarTodosDatos("Carlos Martínez"));;

        //7. MOSTRAR LOS TRATAMIENTOS Y LOS DATOS DE LOS HOSPITALES EN LOS QUE SE REALIZAN -- BUSCAR EL HOSPITAL Y LOS TRATAMIENTOS
        //Hospital hospital = (repoHospital.mostrarTratamientos("Hospital Universitario"));
        //System.out.println(hospital.escribirHospitalCompleto());

        //8. MOSTRAR EL NUMERO TOTAL DE TRATAMIENTOS QUE TIENE CADA HOSPITAL (ENTRADA NOMBRE HOSPITAL)
//        List<Hospital> listadoImprimir = repoHospital.mostratTodosTratamientosTodosHospitales();
//        for (Hospital h : listadoImprimir){
//            System.out.println(h.escribirHospitalCompleto());;
//        }


        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static void asignarDotorPaciente(String nombreDoctor, String nombrePaciente) {
        Doctor doctor = repoDoctor.buscarDoctor(nombreDoctor);
        Paciente paciente = repoPaciente.buscarPaciente(nombrePaciente);
        repoCita.crearCita(paciente, doctor);
    }


}

// TODO preguntar a Jose por qué no puedo usar bloques de texto
        /*
        boolean continuar = true;
        do {
            String entradaTeclado;
            for (int i = 0; i < 110; i++) {
                System.out.print("\u2550");
            }
            System.out.println("");

            System.out.println("MENU\n" +
                    "1 . \n" +
                    "Crear una nueva categoría (PostgreSQL)\n" +
                    "Crear un nuevo proveedor (PostgreSQL)\n" +
                    "Eliminar un nuevo proveedor (PostgreSQL)\n" +
                    "Crear un nuevo usuario (MySQL)\n" +
                    "Eliminar un usuario (MySQL)\n" +
                    "Crear nuevo producto (nombre, precio, stock, categoria, proveedor) (MySQL + PostgreSQL)\n" +
                    "Eliminar un producto por su nombre (MySQL + PostgreSQL)\n" +
                    "Listar los productos con bajo stock (menos de X unidades disponibles) (MySQL)\n" +
                    "Obtener el total de pedidos realizados por cada usuario (MySQL)\n" +
                    "Obtener la cantidad de productos almacenados por cada almacén (PostgreSQL)\n" +
                    "Listar todos los productos con sus respectivas categorías y proveedores (PostgreSQL)\n" +
                    "Obtener todos los Usuarios que han comprado algún producto de una categoria dada (MySQL + PostgreSQL).\n");

            for (int i = 0; i < 110; i++) {
                System.out.print("\u2550");
            }
            System.out.println();

        } while (continuar);
         */