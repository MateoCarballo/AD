package EjerciciosExamen;


import java.io.File;
import java.io.FilenameFilter;

public class FiltradoExtensiones implements FilenameFilter {

    private String extension;

    public FiltradoExtensiones(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return dir.getName().contains(name);
    }
}
