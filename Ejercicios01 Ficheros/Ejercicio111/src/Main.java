import java.io.*;
import java.io.IOException;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*
        Ejercicio 111
        Crea una clase Persona, que tenga las siguientes características:

        Dos atributos nombre y edad.
        Getters y setters.
        Constructor con dos parámetros.
        Que la clase sea serializable.
        A continuación, crea una clase SerializarPersona, que tenga los siguientes métodos:

        public void escribirPersonaEnFichero(Persona persona, File fichero): escribe la información de la persona en el fichero.
        public Persona leerPersonaDeFichero(File fichero): devuelve un objeto Persona con los datos leídos del fichero.
        NOTA: debes usar las clases ObjectInputStream y ObjectOutputStream.

        Además, debes implementar un mét0d0 main que haga lo siguiente:

        Crear una persona.
        Crear un fichero persona.txt.
        Escribir los datos de la persona en el fichero.
        Leer los datos y almacenarlos en un objeto diferente.
        Mostrar por consola los datos recuperados.
         */
        Persona p1 = new Persona ("Nombre1",11);
        File f = new File(".\\src\\persona.txt");
        try {
            if (!f.exists()&&(f.createNewFile())){
                System.out.println("Fichero creado con exito");
            }
            SerializarPersona.escribirPersonaEnFichero(p1,f);
            Persona p2 = SerializarPersona.leerPersonaDeFichero(f);
            System.out.println(p2);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error del tipo "+ Arrays.toString(e.getStackTrace()));;
        }
    }
}