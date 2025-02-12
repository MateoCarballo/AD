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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static String entradaTeclado;

    public void main( String[] args ) {


        boolean continuar = true;

        session = HibernateUtil.get().openSession();
        repoDoctor = new RepoDoctor(session);
        repoPaciente = new RepoPaciente(session);
        repoCita = new RepoCita(session);
        repoRecibe = new RepoRecibe(session);
        repoTratamiento = new RepoTratamiento(session);
        repoHospital = new RepoHospital(session);

        while (continuar) {

            // Imprimimos el menú en consola
            System.out.print(generarMenu());

            try {
                // TODO No me deja usar el switch mejorado
                switch (seleccionarOpcionMenu()) {
                    case 0:
                        continuar = false;
                        break;
                    /**
                     * OPERACIONES SOBRE DOCTOR
                     * Crear (1), Borrar(2) o Modificar(3)
                     */
                    case 1:
                        switch (menuOpcionesCrearBorrarModificar()){
                            case 0:
                                System.out.println("Operacion cancelada por el usuario");
                                break;
                            case 1:
                                //crearDoctor();
                                System.out.println(repoDoctor.crearDoctor(pedirDatosNuevoDoctor()));;
                                break;
                            case 2:
                                System.out.println(repoDoctor.modificarDoctor(pedirDatosModificarDoctor()));;
                                break;
                            case 3:
                                int idDoctor = pedirDatosBorrarPorId();
                                if (repoDoctor.existeDoctor(idDoctor)) System.out.println(repoDoctor.borrarPorId(idDoctor));
                                break;
                        }
                        break;
                    /**
                     * OPERACIONES SOBRE PACIENTE
                     * Crear (1), Borrar(2) o Modificar(3)
                     */
                    case 2:
                        switch (menuOpcionesCrearBorrarModificar()){
                            case 0:
                                System.out.println("Operacion cancelada por el usuario");
                                break;
                            case 1:
                                System.out.println(repoPaciente.crearPaciente(pedirDatosNuevoPaciente()));
                                break;
                            case 2:
                                System.out.println(repoPaciente.modificarPaciente(pedirDatosModificarPaciente()));;
                                break;
                            case 3:
                                String nombrePacienteEliminar = pedirDatosBorrarPaciente();
                                do{
                                    if (!nombrePacienteEliminar.isEmpty()){
                                        System.out.println(repoPaciente.borrarPaciente(nombrePacienteEliminar));
                                    }
                                }while(nombrePacienteEliminar.isEmpty());
                                break;
                        }
                        break;
                    case 3:
                        System.out.println(asignarDoctorPaciente());
                        break;
                    case 4:
                        try{
                            System.out.println("Introduce la fecha de comienzo del tratamiento (AAAA-MM-DD)");
                            LocalDate fechaComienzo = LocalDate.parse(br.readLine());
                            System.out.println("Introduce la fecha fin del tratamiento (AAAA-MM-DD)");
                            LocalDate fechaFin  = LocalDate.parse(br.readLine());
                            System.out.println("Introduce el tipo de tratamiento por id");
                            int idTratamiento = Integer.parseInt(br.readLine());
                            System.out.println("Introduce el tipo de tratamiento por id");
                            String nombrePaciente = br.readLine();
                            System.out.println(repoTratamiento.asignarFechaTratamiento(idTratamiento,
                                    nombrePaciente, fechaComienzo, fechaFin));
                        }catch(IOException e){
                            System.out.println("Error en la lectura de datos");
                        }catch(DateTimeParseException dtpe){
                            System.out.println("Error en el parseo de la fecha");
                        }
                        break;
                    case 5:
                        /*
                        Posicion 0 -> id Tratamiento
                        Posicion 1 -> nombre hospital actual
                        Posicion 2 -> nombre nuevo hospital
                         */
                        String[] datos = pedirDatosModificarHospital();
                        System.out.println(repoHospital.anhadirNuevoTratamiento(
                                Integer.parseInt(datos[0]),
                                datos[1],
                                datos[2]));
                        break;
                    case 6:
                        if (repoPaciente.mostrarTodosDatos(pedirDatosMostrarPaciente()) != null){
                            System.out.println(repoPaciente.mostrarTodosDatos(pedirDatosMostrarPaciente()));
                        }else{
                            System.out.println("El paciente no existe");
                        }
                        break;
                    case 7:
                        Hospital hospital = (repoHospital.mostrarTratamientos("Hospital Central"));
                        System.out.println(hospital.escribirHospitalCompleto());
                        break;
                    case 8:
                        List<Hospital> listadoImprimir = repoHospital.mostratTodosTratamientosTodosHospitales();
                        for (Hospital h : listadoImprimir){
                          System.out.println(h.escribirHospitalCompleto());;
                        }
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
        mostrarBarraDeCarga(1);
        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    public void crearDoctor(){

    }

    private static String asignarDoctorPaciente() {
        String doctorNombre = "";
        String pacienteNombre = "";
        String retorno = "Alguno de los campos está vacío";
        try{
            doctorNombre = br.readLine();
            pacienteNombre = br.readLine();
        }catch(IOException e){
            System.out.println("Error en la lectura de datos por teclado");
        }
        // TODO Java 8 no tiene el metodo "isBlank()",
        //  Entiendo que debo comprobar que no metan espacios en blanco,
        //  Pero no puedo con esta version de Java
        if (!doctorNombre.isEmpty() && !pacienteNombre.isEmpty()){
            retorno = asignarDotorPaciente(doctorNombre,pacienteNombre);
        }
        return retorno;
    }

    private static int seleccionarOpcionMenu() {
        String eleccion="";
        do {
            try{
                eleccion = br.readLine();
            }catch(IOException ioe){
                System.out.println("Error al leer los datos por teclado");
            }
        }while(!PatronesRegex.DIGITOS_0_9.matches(eleccion));
        return Integer.parseInt(eleccion);
    }

    private static String generarMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("-------------------------------------------------\n")
                .append("1. OPERACIONES SOBRE DOCTOR\n")
                .append("2. OPERACIONES SOBRE PACIENTE\n")
                .append("3. ASIGNAR A UN DOCTOR UN PACIENTE (DAR CITA)\n")
                .append("4. INDICAR LA FECHA FIN DEL TRATAMIENTO DE UN PACIENTE\n")
                .append("5. CAMBIAR EL HOSPITAL DE UN TRATAMIENTO\n")
                .append("6. MOSTRAR DATOS PACIENTE (Introduciendo nombre)\n")
                .append("7. MOSTRAR LOS TRATAMIENTOS Y LOS DATOS DE LOS HOSPITALES EN LOS QUE SE REALIZAN \n")
                .append("8.  MOSTRAR EL NUMERO TOTAL DE TRATAMIENTOS QUE TIENE CADA HOSPITAL (ENTRADA NOMBRE HOSPITAL)\n")
                .append("0. Cerrar aplicación\n")
                .append("-------------------------------------------------\n")
                .append("Seleccione una opción: ");
        return menu.toString();
    }

    private static String pedirDatosMostrarPaciente() {
        try{
            System.out.println("Introduce el nombre del paciente que quieres visualizar");
            entradaTeclado = br.readLine();
        } catch (IOException e) {
            System.out.println("Error de lectura");
        }
        return entradaTeclado;
    }

    private static int pedirDatosBorrarPorId() {
        String idMedico = "";
        System.out.println("Introduce el Id del medico que quieres eliminar");
        try {
             do{
                 idMedico = br.readLine();
             }while(!PatronesRegex.SOLO_NUMEROS_POSITIVOS.matches(idMedico));
        } catch (IOException e) {
            System.out.println();
        }
        return Integer.parseInt(idMedico);
    }

    private static String pedirDatosBorrarPaciente() {
        String nombrePaciente = "";
        try{
            System.out.println("Introduce el nombre del paciente");
            nombrePaciente= br.readLine();
        }catch (IOException e){
            System.out.println("Error al leer los datos");
        }
        return nombrePaciente;
    }

    private static int menuOpcionesCrearBorrarModificar() {
        String eleccion = "";
        StringBuilder menu = new StringBuilder();
        menu.append("-------------------------------------------------\n")
                .append("1. Crear\n")
                .append("2. Modifcar\n")
                .append("3. Borrar\n")
                .append("0. Anular operación\n")
                .append("Seleccione una opción: ");
        System.out.print(menu);
        do {
            try{
                eleccion = br.readLine();
            }catch(IOException ioe){
                System.out.println("Error al leer los datos por teclado");
            }
        }while(!PatronesRegex.DIGITOS_0_3.matches(eleccion));
        return Integer.parseInt(eleccion);
    }



    private static Doctor pedirDatosNuevoDoctor() {
        int id = 0 ;
        String nombreDoctor = "";
        String especialidad = "";
        String telefonoDoctor ="";

        try {
            id = repoDoctor.obtenerPrimerIdDisponible();
            // El doctor se puede llamar como quiera como si se llama %^&@
            System.out.println("Introduce el nombre del doctor");
            nombreDoctor= br.readLine();
            //TODO comprobar que exista esta especialidad
            System.out.println("Introduce la especialidad del doctor");
            especialidad = br.readLine();
            // El número como si es de Japon. MESSIRVE
            System.out.println("Introduce el telefono del doctor");
            telefonoDoctor = br.readLine();

        } catch (IOException e) {
            System.out.println("Error en lectura datos");
        }

        return Doctor.builder()
                .id(id)
                .nombre(nombreDoctor)
                .especialidad(especialidad)
                .telefono(telefonoDoctor)
                .build();
    }

    private static Paciente pedirDatosNuevoPaciente() {
        int id = 0 ;
        String nombrePaciente = "";
        LocalDate fechaNacimiento = LocalDate.of(1900,01,01);
        String direccion = "";

        try {
            id = repoPaciente.obtenerPrimerIdDisponible();
            // El doctor se puede llamar como quiera como si se llama %^&@
            System.out.println("Introduce el nombre del paciente");
            nombrePaciente= br.readLine();


            System.out.println("Fecha de nacimiento (Formato AAAA-MM-DD)");

            boolean continuar;
            do{
                continuar = true;
                try {
                    fechaNacimiento = LocalDate.parse(br.readLine(),formatoFecha);
                } catch (DateTimeParseException e) {
                    System.out.println("Fecha inválida. Asegúrate de que sea una fecha real.");
                    continuar = false;
                }
            }while (!continuar);


            //
            System.out.println("Introduce la direccion del paciente");
            direccion = br.readLine();

        } catch (IOException e) {
            System.out.println("Error en lectura datos");
        }

        return Paciente.builder()
                .id(id)
                .nombre(nombrePaciente)
                .fechaNacimiento(fechaNacimiento)
                .direccion(direccion)
                .build();
    }

    private static Doctor pedirDatosModificarDoctor() {
        Doctor doctor = null;
        String nombreDoctor = "";
        String especialidad = "";
        String telefonoDoctor ="";

        try {
            System.out.println("Que doctor deseas modificar ? (Escribe su nombre)");
            nombreDoctor = br.readLine();
            doctor = repoDoctor.buscarDoctor(nombreDoctor);
            if ( doctor != null){
                System.out.println("Deseas modificar el nombre ? (si no desea modificarlo solo pulsa enter)");
                nombreDoctor = br.readLine();
                System.out.println("Deseas modificar la especialidad ? (si no desea modificarlo solo pulsa enter)");
                especialidad = br.readLine();
                System.out.println("Deseas modificar la telefono ? (si no desea modificarlo solo pulsa enter)");
                telefonoDoctor = br.readLine();
                if (!nombreDoctor.trim().isEmpty()){
                    doctor.setNombre(nombreDoctor);
                }
                if (!especialidad.trim().isEmpty()){
                    doctor.setEspecialidad(especialidad);
                }
                if (!telefonoDoctor.trim().isEmpty()){
                    doctor.setTelefono(telefonoDoctor);
                }
            }
        } catch (IOException e) {
            System.out.println("Error en lectura datos");
        }
        return doctor;
    }



    private static Paciente pedirDatosModificarPaciente() {
        Paciente paciente = new Paciente();
        String nombrePaciente = "";
        String fechaNacimiento = "";
        String direccion ="";

        try {
            System.out.println("Que paciente deseas modificar ? (Escribe su nombre)");
            nombrePaciente = br.readLine();
            paciente = repoPaciente.buscarPaciente(nombrePaciente);
            if ( paciente != null){
                System.out.println("Deseas modificar el nombre ? (si no desea modificarlo solo pulsa enter)");
                nombrePaciente = br.readLine();
                System.out.println("Deseas modificar la fecha de nacimiento (AAAA-MM-DD) ? (si no desea modificarlo solo pulsa enter)");
                //TODO comprobar fecha antes de parsearla a localdate
                fechaNacimiento = br.readLine();
                System.out.println("Deseas modificar la direccion ? (si no desea modificarlo solo pulsa enter)");
                direccion = br.readLine();

                if (!nombrePaciente.trim().isEmpty()){
                    paciente.setNombre(nombrePaciente);
                }
                if (!fechaNacimiento.trim().isEmpty()){
                    paciente.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
                }
                if (!direccion.trim().isEmpty()){
                    paciente.setDireccion(direccion);
                }
            }
        } catch (IOException e) {
            System.out.println("Error en lectura datos");
        }
        return paciente;
    }

    private static String asignarDotorPaciente(String nombreDoctor, String nombrePaciente) {
        String retorno = "Alguno de los nombres no existe en la base de datos";
        Doctor doctor = repoDoctor.buscarDoctor(nombreDoctor);
        Paciente paciente = repoPaciente.buscarPaciente(nombrePaciente);
        if (doctor != null && paciente != null) {
            retorno = repoCita.crearCita(paciente, doctor);
        }
        return retorno;
    }



    private static String [] pedirDatosModificarHospital() {
        String[] datosConsulta = new String[3];
        try{
            System.out.println("Introduce el id del tratamiento que quieres cambiar de hospital");
            datosConsulta[0] = br.readLine();
            System.out.println("Introduce el nombre del hospital actual en el que se realiza el tratamiento");
            datosConsulta[1] = br.readLine();
            System.out.println("Introduce el nombre del nuevo hospital");
            datosConsulta[2] = br.readLine();
        }catch(IOException e ){
            System.out.println("Error en la lectura de datos");
        }
        return datosConsulta;
    }

    // Método que simula la barra de carga de 5 segundos
    private static void mostrarBarraDeCarga(int segundos) {

        System.out.println("Cerrando aplicación...");
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

}