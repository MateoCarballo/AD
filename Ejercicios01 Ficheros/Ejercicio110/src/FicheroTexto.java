import java.io.*;

public class FicheroTexto {
    File f;

    public FicheroTexto(File f) throws IOException {
        this.f = f;
    }

    public File getF() {
        return f;
    }

    public void escribir(String texto){
        try {
            FileWriter fw = new FileWriter(this.f,true);
            fw.append(texto);
            fw.append('.');
            fw.append("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error durante la escritura");
        }
    }

    public void leer(){
        try {
            FileReader fr = new FileReader(f);
            char[] vectorFichero = new char[(int) f.length()];
            fr.read(vectorFichero);
            for (int i = 0; i < vectorFichero.length; i++) {
                System.out.print(vectorFichero[i]);
            }
            System.out.print("\n");
            fr.close();
        } catch (IOException e) {
            System.out.println("Error durante la lectura");
        }

    }

    public void borrarFichero(){
        try {
            FileWriter fw = new FileWriter(this.f);
            fw.append(" ");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error durante la escritura");
        }
    }

}
