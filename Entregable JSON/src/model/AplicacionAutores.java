package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import gui.VentanaBorrarAutor;
import gui.VentanaCambiarTitulo;
import gui.VentanaCrearAutor;
import gui.VentanaInicioSesion;
import gui.VentanaMenuAutor;
import gui.VentanaVerDatos;

public class AplicacionAutores
{

	private final String RUTA_FICHERO = "resources/libreria.json";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearAutor ventanaCrearAutor;
	private VentanaMenuAutor ventanaMenuAutor;
	private VentanaVerDatos ventanaVerDatos;
	private VentanaCambiarTitulo ventanaCambiarTitulo;
	private VentanaBorrarAutor ventanaBorrarAutor;

	private void crearFicheroJson()	{
		// TODO
	}

	private void guardarFicheroJson(JSONArray autores) {
		// TODO
	}
//
//	private JSONArray obtenerAutoresJson() {
//		// TODO
//	}
//
//	private int obtenerPosicionAutor(String nombreAutor, JSONArray autores){
//		// TODO
//	}
//
//	private JSONObject obtenerAutoresJson(String nombreAutor){
//		// TODO
//	}

	public void ejecutar(){
		// TODO
		this.ventanaInicioSesion = new VentanaInicioSesion(this);
		ventanaInicioSesion.setVisible(true);
	}

	public void iniciarValidacion(String nombreAutor, String tituloLibroAutor){
		// TODO
	}

	public void cerrarSesion(){
		// TODO
	}

	public void crearAutor(String nombre, String titulo, String paginas, String editorial){
		// TODO
	}

	public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo){
		// TODO
	}

	public void borrarAutor(String nombreAutor){
		// TODO
	}

	public void mostrarVentanaCrearAutor(){
		// TODO
	}

	public void mostrarVentanaVerDatos(String nombreAutor){
		// TODO
	}

	public void mostrarVentanaCambiarTitulo(String nombreAutor){
		// TODO
	}

	public void mostrarVentanaBorrarAutor(String nombreAutor){
		// TODO
	}

	public void mostrarMenuAutor(String nombreAutor){
		// TODO
	}
}
