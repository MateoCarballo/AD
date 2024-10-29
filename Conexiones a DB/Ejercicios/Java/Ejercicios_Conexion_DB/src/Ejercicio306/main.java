package Ejercicio306;

import java.sql.SQLException;

public class main {
    /**
     *  Ejercicio 306
     *  Desarrolla una aplicación, siguiendo el patrón MVC, que muestre los datos de un empleado con un número determinado.
     */
    public static void main(String[] args) throws SQLException {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo,vista);
        controlador.arrancaApp();
        vista.setController(controlador);
        // TODO: INSTANCIAR EL MODELO, LA VISTA Y EL CONTROLADOR
        // TODO: ARRANCAR LA VISTA Y ESTABLECERLER EL CONTROLADOR
    }
    
}
