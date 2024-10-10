import java.io.File;
import java.io.FilenameFilter;

public class ComprobarArchivo implements FilenameFilter {

    private String nombreArchivo;

    ComprobarArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.contains(this.nombreArchivo);
    }
}