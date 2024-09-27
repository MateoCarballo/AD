package FicherosPrueba;

import java.io.File;
import java.io.FilenameFilter;


public class FiltrarNombre106 implements FilenameFilter {
    String extension;

    public String getExtension() {
        return extension;
    }

    public FiltrarNombre106(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }

    public  void  filtrar(File f){
        File[] listado = f.listFiles();

        if (!f.isDirectory()){
            System.out.println("No has introducido un directorio");
        }

        for (int i = 0; i < listado.length; i++) {
            if (accept(listado[i],listado[i].getName())){
                System.out.println(listado[i].getName());
            }
        }


    }
}
