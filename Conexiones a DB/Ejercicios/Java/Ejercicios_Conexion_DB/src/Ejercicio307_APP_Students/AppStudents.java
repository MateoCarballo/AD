package Ejercicio307_APP_Students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AppStudents {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList <Student> students;
    static final String PATRON_INDICE_PRINCIPAL = "^[1-6]$";
    static final String PATRON_NOMBRE_Y_APELLIDOS = "^[A-Z][a-zñáéíóú]{0,19}$";
    static final String PATRON_EDAD = "^[1-9][0-9]?$";
    static final String PATRON_DNI = "^[0-9]{8}[A-Z]$";
    private final ManageStudents manage;

    public AppStudents(ManageStudents manageStudents){
        this.manage = manageStudents;
    }

    public void getValuesFromDB() {
        students = manage.getStudents();
    }

    private void menuPrincipal (){
        String entradaTeclado = "";
        boolean continuar = true;
        boolean datosOK;
        do{
            getValuesFromDB();
            System.out.println("""
                    1. MATRICULAR UN ESTUDIANTE.
                    2. DAR DE BAJA A UN ESTUDIANTE.
                    3. ACTUALIZAR DATOS DE UN ESTUDIANTE.
                    4. VER DATOS DE UN ESTUDIANTE.
                    5. VER DATOS DE TODOS LOS ESTUDIANTES.
                    6. SALIR.
                    """);
            do{
                try {
                    entradaTeclado = br.readLine();
                } catch (IOException e) {
                    System.out.println("Error al leer datos de entrada en el menu principal");
                }
                datosOK = compronarDatoIntroducido(entradaTeclado,PATRON_INDICE_PRINCIPAL);
            }while (!datosOK);

            int opcionMenu = Integer.parseInt(entradaTeclado);

            switch (opcionMenu){
              case 1 -> matricularStudent();
              case 2 -> bajaStudent();
              case 3 -> actualizaStudent();
              case 4 -> verDatosStudent();
              case 5 -> verTodosLosDatos();
              case 6 -> continuar = false;
            }
        }while(continuar);
    }

    private void matricularStudent() {
        String id = "";
        String name= "";
        String surname = "";
        int age = 0;
        //Compruebo que el dato introducido es un nombre valido si no repito hasta que lo sea.
        do{
            System.out.println("Introduce el nombre del alumno");
            try {
                name = br.readLine();
            } catch (IOException e) {
                System.out.println("Error al introducri el nombre del alumno");;
            }
        }while(!compronarDatoIntroducido(name,PATRON_NOMBRE_Y_APELLIDOS));
        //Compruebo que el dato introducido es un apellido valido si no repito hasta que lo sea.
        do{
            System.out.println("Introduce el apellido del alumno");
            try {
                surname = br.readLine();
            } catch (IOException e) {
                System.out.println("Error al introducir el apellido del alumno");;
            }
        }while(!compronarDatoIntroducido(surname,PATRON_NOMBRE_Y_APELLIDOS));
        //Compruebo que el dato introducido es una edad valido si no repito hasta que lo sea.
        do{
            System.out.println("Introduce la edad del alumno");
            try {
                String entrada = br.readLine();
                if (compronarDatoIntroducido(entrada,PATRON_EDAD)){
                    age = Integer.parseInt(entrada);
                }
            } catch (IOException e) {
                System.out.println("Error al introducir la edad del alumno");;
            }
        }while(age == 0);
        //Compruebo que el dato introducido es un DNI valido si no repito hasta que lo sea.
        do{
            System.out.println("Introduce el dni del alumno");
            try {
                id = br.readLine();
            } catch (IOException e) {
                System.out.println("Error al introducir el dni del alumno");;
            }
        }while(!compronarDatoIntroducido(id,PATRON_DNI));
        if (manage.addStudent(new Student(id,name,surname,age))){
            System.out.println("******* ALUMNO AÑADIDO CORRECTAMENTE *******");
        } else {
            System.out.println("******* EL ALUMNO NO HA PODIDO AÑADIRSE *******");
        }
    }

    private void bajaStudent() {
        String id = "";
        System.out.println("Introduzca el DNI del alumno que desea eliminar");
        try {
            do{
                id = br.readLine();
            }while(!(compronarDatoIntroducido(id,PATRON_DNI)));
            if (!existeEstudiante(id)){
                System.out.println("No se ha encontrado nignun estudiante con ese ID");
            }
            if (!manage.deleteStudent(id)){
                System.out.println("******* No se ha podido eliminar el student en el metodo 'Manage.deleteStudent' ******* ");
            } else {
                System.out.println("******* ALUMNO ELIMINADO CON EXITO *******");
            }
        } catch (IOException e) {
            System.out.println("Error al introducir el DNI para eliminar student");
        }
    }

    private boolean existeEstudiante(String id) {
        boolean existeStudent = false;
        for(Student s : students){
            if (id.equalsIgnoreCase(s.getId())){
                existeStudent = true;
            }
        }
        return existeStudent;
    }

    private void actualizaStudent() {
        String id = "";
        System.out.println("Introduzca el DNI del alumno que desea modificar");
        try {
            do{
                id = br.readLine();
            }while(!(compronarDatoIntroducido(id,PATRON_DNI)));
            if (!existeEstudiante(id)){
                System.out.println("No se ha encontrado nignun estudiante con ese ID");
            }
            if (!manage.modifyStudent(encontrarStudent(id))){
                System.out.println("******* No se ha podido modificar el student en el metodo 'Manage.modifyStudent()' ******* ");
            } else {
                System.out.println("******* ALUMNO MODIFICADO CON EXITO *******");
            }
        } catch (IOException e) {
            System.out.println("Error al introducir el DNI para eliminar student");
        }
    }

    private Student encontrarStudent(String id) {
        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getId().equalsIgnoreCase(id)){
                index = i;
            }
        }
        return students.get(index);
    }

    private static void verDatosStudent() {
    }

    private static void verTodosLosDatos() {
    }

    private static boolean compronarDatoIntroducido(String entradaTeclado, String patronRegex) {
        return entradaTeclado.matches(patronRegex);
    }


    public void arranca() {
        menuPrincipal();
    }
}
