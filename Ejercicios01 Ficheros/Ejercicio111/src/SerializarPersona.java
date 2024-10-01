import java.io.*;

public class SerializarPersona {

    private SerializarPersona(){

    }
    // escribe la información de la persona en el fichero.
    public static void escribirPersonaEnFichero(Persona p, File f){
        try {
            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(f));
            ous.writeObject(p);
            ous.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error durante la escritura");;
        }
    }
    //devuelve un objeto Persona con los datos leídos del fichero.
    public static Persona leerPersonaDeFichero(File f){
        Persona personaParaDevolver = new Persona("Z",0);
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            personaParaDevolver = (Persona) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error durante la lectura del fichero y creacion de la nueva persona");;
        }
        return personaParaDevolver;
    }
}
