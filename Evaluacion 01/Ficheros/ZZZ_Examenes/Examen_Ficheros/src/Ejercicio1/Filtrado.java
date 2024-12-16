package Ejercicio1;

import java.io.File;
import java.io.FilenameFilter;

public class Filtrado implements FilenameFilter {
    File [] files;

    public Filtrado(File[] files) {
        this.files = files;
    }


    @Override
    public boolean accept(File dir, String name) {
        return dir.getName().equalsIgnoreCase(name);
    }
}
