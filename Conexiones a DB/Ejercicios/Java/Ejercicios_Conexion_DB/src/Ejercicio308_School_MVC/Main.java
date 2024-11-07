package Ejercicio308_School_MVC;

public class Main {

	public static void Main(String[] args) {
		// TODO: INSTANCIAR EL MODELO, LA VISTA Y EL CONTROLADOR
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador (vista,modelo);
        // TODO: ARRANCAR LA VISTA Y ESTABLECERLE EL CONTROLADOR
		vista.arrancar(controlador);
		// TODO: ACTUALIZAR LA TABLA CON LA LISTA DE TODOS LOS ALUMNOS
	}

}
