import java.util.ArrayList;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String cadena = "Nombre_Alumno# LMSXI # SI # BD # PRO # CD # FOL;Alumno 1  # 4     #    # 5  #  2  # 8  # 9;Alumno 2     # 5     # 3  # 6  #  7  # 10 # 6;Alumno 3     # 7     # 4  # 9  #  9  # 9  # 8;";;
        String[] parts = cadena.split(";");
        System.out.println(Arrays.asList(parts));
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();

        for (int i = 1; i < parts.length-1; i++) {
            listaAlumnos.add(new Alumno(parts[i]));
            System.out.println(listaAlumnos.get(i-1));
        }


//        System.out.println("""
//                Que operacion quieres realizar sobre el alumno
//                1.Calcular nota media del alumno(Solo tiene en cuenta modulo cursados).
//                2.Calcular nota media del modulo(Solo en cuenta alumnos que cursan el modulo)
//                3.Mostrar numero de alumnos con tudo aprobado.
//                4.Mostrar el modulo con mayor nota media.
//                5.Mejor mejor alumno de cada modulo.
//                """);
    }
}