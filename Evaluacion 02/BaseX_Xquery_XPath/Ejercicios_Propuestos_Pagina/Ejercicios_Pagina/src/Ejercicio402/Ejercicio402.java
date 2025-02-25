package Ejercicio402;

import org.basex.examples.api.BaseXClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Ejercicio402 {

    private ArrayList<Libro> libros;

    public Ejercicio402() {
        this.libros = new ArrayList<>();
    }
    public void realizarEjercicio(){
        generar1000Libros();
        crearFicheroGrande();
        crearFicherosPequeños();
        numeroTotalDeDocumentos();
    }

    private void  generar1000Libros(){
        for (int i = 0; i < 1000; i++) {
            libros.add(new Libro(generarAleatorio(2000,2014),
                    generarAleatorio(1,20),
                    "titulo" + i,
                    "nombre" + i,
                    "Apellido1 - " + generarAleatorio(0,19)
                            + " Apellido2 - " + generarAleatorio(0,19),
                    "Editorial " + generarAleatorio(0,100),
                    generarAleatorio(150,850),
                    generarAleatorioBoolean()));
        }
    }

    private void crearFicherosPequeños() {
        //Crear la base de datos
        try(BaseXClient session = new BaseXClient("localhost",
                1984,
                "admin",
                "abc123")){
            InputStream bais = new ByteArrayInputStream(toStringFicheroPequeño(libros.getFirst()).getBytes());
            session.create("BaseFicherosPeq.xml",bais);
            for (int i = 1; i < libros.size(); i++) {
                bais = new ByteArrayInputStream(toStringFicheroPequeño(libros.get(i)).getBytes());
                session.add("BaseFicherosPequeños/libro_" + i + ".xml",bais);
                System.out.println("Fichero pequeño " +session.info());
            }

        }catch (IOException e) {
         e.printStackTrace();
        }
    }

    private void crearFicheroGrande() {
        try(BaseXClient session = new BaseXClient("localhost",1984,"admin","abc123")){
            InputStream bais = new ByteArrayInputStream(toStringFicheroGrande(libros).getBytes());
            session.create("BaseFicheroGrande.xml",bais);
            System.out.println("Fichero grande " + session.info());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int generarAleatorio(int min, int max){
        return (int) (Math.floor(Math.random()*(max-min+1)+min));
    }

    private boolean generarAleatorioBoolean(){
        int result = generarAleatorio(0,1);
        if(result == 0) {
            return true;
        } else{
           return false;
        }
    }

    private String toStringFicheroGrande(ArrayList<Libro> libros){
        StringBuilder resultado = new StringBuilder();
        resultado.append("<libros>\n");
        for (Libro l: libros){
            resultado.append(toStringFicheroPequeño(l));
        }
        resultado.append("</libros>");
        return resultado.toString();
    }

    private String toStringFicheroPequeño(Libro libro){
        StringBuilder sb = new StringBuilder();
        sb.append("<libro publicacion =\"" + libro.getPublicacion() + "\" edicion = \"" + libro.getEdidicion() + "\"> \n" )
                .append("    <titulo>" + libro.getTitulo() + "</titulo>\n")
                .append("    <autor>\n")
                .append("        <nombre>" + libro.getNombreAutor() + "</nombre>\n")
                .append("        <apellido>" + libro.getApellidoAutor() + "</apellido>\n")
                .append("    </autor>\n")
                .append("    <editorial>" + libro.getEditorial() + "</editorial>\n")
                .append("    <paginas>" + libro.getPaginas() + "</paginas>\n")
                .append("    <edicionElectronica>" + libro.isEdicionElectronica() + "</edicionElectronica>\n")
                .append("</libro>\n");
        return sb.toString();
    }

    private void numeroTotalDeDocumentos(){

    }

}
