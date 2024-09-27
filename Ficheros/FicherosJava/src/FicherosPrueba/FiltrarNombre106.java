package FicherosPrueba;

import java.io.File;
import java.io.FilenameFilter;

public class FiltrarNombre106 implements FilenameFilter {
    String extension;

    public FiltrarNombre106(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
    

}
