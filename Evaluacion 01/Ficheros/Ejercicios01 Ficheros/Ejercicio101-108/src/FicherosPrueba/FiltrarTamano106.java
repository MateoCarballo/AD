package FicherosPrueba;

import java.io.File;

public class FiltrarTamano106 {
    float minTamano;

    public FiltrarTamano106(float nimTamano) {
        this.minTamano = nimTamano;
    }

    public boolean filtrar(File file, int tamanoDelArchivo) {
        return (tamanoDelArchivo> minTamano);
    }
}
