package Ejercicio305;

public class main {
    
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo,vista);
        controlador.arrancarApp();
        vista.setController(controlador);

        // TODO: INSTANCIAR EL MODELO, LA VISTA Y EL CONTROLADOR
        // TODO: ARRANCHAR LA VISTA Y ESTABLECERLE EL CONTROLADOR


    }
}
