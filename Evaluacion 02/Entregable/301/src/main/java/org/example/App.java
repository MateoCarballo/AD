package org.example;

import Entity.Doctor;
import Entity.Paciente;
import Repository.RepoDoctor;
import Repository.RepoPaciente;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class App {

    public static void main( String[] args ) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();
        RepoDoctor repoDoctor = new RepoDoctor(session);
        RepoPaciente repoPaciente = new RepoPaciente(session);

        //
        //repoDoctor.crear(1000,"Nombre creado", "Especialidad creada", "123123123");
        //repoDoctor.modificarDoctor(2,"Cambiado","especialidad","999888777");
        //repoDoctor.borrarPorId(3);

        //repoPaciente.Crear(2000,"nombre", LocalDate.of(2008,11,1),"direccion2");
        repoPaciente.borrar("Juan Perez");
        session.close();
        System.out.println("Finalizando la conexion a MySQL");

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