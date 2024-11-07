package Ejercicio307_APP_Students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AppStudents {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private ArrayList <Student> students;
    static final String PATRON_INDICE_PRINCIPAL = "^[1-6]$";
    static final String PATRON_INDICE_MODIFICAR = "^[1-5]$";
    static final String PATRON_NOMBRE_Y_APELLIDOS = "^[A-Z][a-zñáéíóú]{0,19}$";
    static final String PATRON_EDAD = "^[1-9][0-9]?$";
    static final String PATRON_DNI = "^[0-9]{8}[A-Z]$";
    private final ManageStudents manage;

    public AppStudents(ManageStudents manageStudents){
        this.manage = manageStudents;
    }

    public void getValuesFromDB() {
        students = manage.getStudents();
        System.out.println("Valores cargados en programa desde DB");
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
        String dniAnterior = "";
        String opcionElegida = "";
        do{
            try {
                System.out.println("Introduzca el DNI del alumno que desea modificar");
                dniAnterior = br.readLine();
            } catch (IOException e) {
                System.out.println("Error durante la introduccion del dni del alumno.");
            }
            if (!compronarDatoIntroducido(dniAnterior,PATRON_DNI)){
                System.out.println("Revisa el DNI introducido, no es un DNI valido o el alumno no está registrado");
            }
        }while(!compronarDatoIntroducido(dniAnterior,PATRON_DNI) || (!existeEstudiante(dniAnterior)));
        
        try {
            do{
                System.out.println("""
                    1. Modificar DNI.
                    2. Modificar nombre.
                    3. Modificar apellido.
                    4. Modificar edad.
                    5. Cancelar operación.
                    """);
                opcionElegida = br.readLine();
            }while(!compronarDatoIntroducido(opcionElegida,PATRON_INDICE_MODIFICAR));
            
            int opcionElegidaInt = Integer.parseInt(opcionElegida);
            
            switch (opcionElegidaInt){
                case 1 -> modificarId(dniAnterior);
                case 2 -> modificarNombre(dniAnterior);
                case 3 -> modificarApellido(dniAnterior);
                case 4 -> modificarEdad(dniAnterior);
            }
            
        } catch (IOException e) {
            System.out.println("Error al introducir el DNI para eliminar student");
        }
    }
    
    private void modificarId(String dniAnterior) {
        String nuevoDNI = "";
        do{
            try {
                System.out.println("Introduce el nuevo DNI para el alumno");
                nuevoDNI = br.readLine();
                if(compronarDatoIntroducido(nuevoDNI,PATRON_DNI)){
                    manage.modifyStudent(encontrarStudent(dniAnterior),0,nuevoDNI);
                }
            } catch (IOException e) {
                System.out.println("Error en la lectura del nuevo DNI");
            }

        }while(!compronarDatoIntroducido(dniAnterior,PATRON_DNI));
    }
    
    
    private void modificarNombre(String dni) {
        String nuevoNombre = "";
        do{
            try {
                System.out.println("Introduce el nuevo nombre para el alumno");
                nuevoNombre = br.readLine();
                if(compronarDatoIntroducido(nuevoNombre,PATRON_NOMBRE_Y_APELLIDOS)){
                    manage.modifyStudent(encontrarStudent(dni),1,nuevoNombre);
                }
            } catch (IOException e) {
                System.out.println("Error en la lectura del nuevo nombre");
            }

        }while(!compronarDatoIntroducido(nuevoNombre,PATRON_NOMBRE_Y_APELLIDOS));
    }
    private void modificarApellido(String dni) {
        String nuevoApellido = "";
        do{
            try {
                System.out.println("Introduce el nuevo apellido para el alumno");
                nuevoApellido = br.readLine();
                if(compronarDatoIntroducido(nuevoApellido,PATRON_NOMBRE_Y_APELLIDOS)){
                    manage.modifyStudent(encontrarStudent(dni),2,nuevoApellido);
                }
            } catch (IOException e) {
                System.out.println("Error en la lectura del nuevo apellido");
            }

        }while(!compronarDatoIntroducido(nuevoApellido,PATRON_NOMBRE_Y_APELLIDOS));
    }
    private void modificarEdad(String dni) {
        String nuevaEdad = "";
        do{
            try {
                System.out.println("Introduce la nueva edad para el alumno");
                nuevaEdad = br.readLine();
                if(compronarDatoIntroducido(nuevaEdad,PATRON_EDAD)){
                    manage.modifyStudent(encontrarStudent(dni),3,nuevaEdad);
                }
            } catch (IOException e) {
                System.out.println("Error en la lectura de la nueva edad");
            }

        }while(!compronarDatoIntroducido(nuevaEdad,PATRON_EDAD));
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
