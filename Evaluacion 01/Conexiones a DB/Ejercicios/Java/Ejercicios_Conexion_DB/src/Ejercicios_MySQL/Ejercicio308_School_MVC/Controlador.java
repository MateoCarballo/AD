package Ejercicios_MySQL.Ejercicio308_School_MVC;

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
		String [] values = view.getTextFieldValues();
		if (e.getActionCommand().equalsIgnoreCase("SAVE")){
			model.addStudent(new Student (values[0],values[1],values[2], Integer.parseInt(values[3])));
		}
		if (e.getActionCommand().equalsIgnoreCase("UPDATE")){
			model.modifyStudent(model.getStudent(values[0]));
		}
		if (e.getActionCommand().equalsIgnoreCase("DELETE")){
			model.deleteStudent(values[0]);
		}
		view.writteValues();

	}

	public ArrayList<Student> getDataBaseValues() {
		return model.getStudentsList();
	}
}
