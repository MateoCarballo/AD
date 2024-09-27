package FicherosPrueba;

import java.io.File;
import java.io.FilenameFilter;

public class FiltrarNombre implements FilenameFilter {
    String extension;

    public FiltrarNombre(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File file, String s) {
        return s.endsWith(extension);
    }
}
