package Ejercicio308_School_MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Controlador implements ActionListener{
	private Vista view;
	private Modelo model;

	// TODO: CONSTRUCTOR DEL CONTROLADOR CON LA VISTA Y EL MODELO
	Controlador(Vista vista,Modelo modelo){
		view = vista;
		model = modelo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: USAR e.getActionCommand() PARA SABER QUE SE EST� EJECUTANDO
        //TODO: OBTENER PAR�METROS DE LA VISTA, OBTENER INFORMACI�N DE LOS ALUMNOS Y ACTUALIZAR VALOR DE LA TABLA
		if (e.getActionCommand().equalsIgnoreCase("SAVE")){
		//TODO llamar al metodo que corresponda
		}
		if (e.getActionCommand().equalsIgnoreCase("UPDATE")){
			//TODO llamar al metodo que corresponda
		}
		if (e.getActionCommand().equalsIgnoreCase("DELETE")){
			//TODO llamar al metodo que corresponda
		}

	}

	public ArrayList<Student> getDataBaseValues() {
		return model.getStudentsList();
	}
}
