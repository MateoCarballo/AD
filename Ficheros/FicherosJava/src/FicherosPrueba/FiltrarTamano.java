package FicherosPrueba;

import java.io.File;

public class FiltrarTamano {
    float minTamano;

    public FiltrarTamano(float nimTamano) {
        this.minTamano = nimTamano;
    }

    public boolean filtrar(File file, int tamanoDelArchivo) {
        return (tamanoDelArchivo> minTamano);
    }
}
