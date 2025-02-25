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
            InputStream bais = new ByteArrayInputStream(toStringFicheroPequeño(libros.get(0)).getBytes());
            session.create("BaseFicherosPequeños",bais);
            for (int i = 1; i < libros.size(); i++) {
                bais = new ByteArrayInputStream(toStringFicheroPequeño(libros.get(i)).getBytes());
                session.add("BaseFicherosPequeños/libro_" + i + ".xml",bais);
                System.out.println(session.info());
            }

        }catch (IOException e) {
         e.printStackTrace();
        }
    }

    private void crearFicheroGrande() {
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

    private String toStringFicheroPequeño(Libro libro){
        StringBuilder sb = new StringBuilder();
        sb.append("<libro publicacion =\" " + libro.getPublicacion() + "\" edicion = \"" + libro.getEdidicion() + " \"> " )
                .append("    <titulo>" + libro.getTitulo() + "</titulo>")
                .append("    <autor>")
                .append("        <nombre>" + libro.getNombreAutor() + "</nombre>")
                .append("        <apellido>" + libro.getNombreAutor() + "</apellido>")
                .append("    </autor>")
                .append("    <editorial>" + libro.getEditorial() + "</editorial>")
                .append("    <paginas>" + libro.getTitulo() + "</paginas>")
                .append("    <edicionElectronica>" + libro.getTitulo() + "</edicionElectronica>")
                .append("</libro>");
        return sb.toString();
    }
}
