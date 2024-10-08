package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		File rutaArchivoJson = new File(RUTA_FICHERO);
		if (!rutaArchivoJson.exists()){
			JSONArray libreria = new JSONArray();
			libreria.put(crearLibro("María Fernández","Título 1","239","Anaya"));
			libreria.put(crearLibro("Elvira Nieto","Título 2","430","McMillan"));
			try (FileWriter fw = new FileWriter(RUTA_FICHERO)){
				fw.write(libreria.toString(4));
				fw.flush();
			}catch (IOException e){
				JOptionPane.showMessageDialog(null, "Error al crear el archivo JSON: " + e.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 *
	 * @param autor contiene el nombre del autor.
	 * @param titulo contiene el titulo del libro.
	 * @param paginas contiene el numero de paginas.
	 * @param editorial contiene la editorial.
	 * @return Devuelve un objeto json con el libro completo para poder anadirlo al array de objetos JSON
	 */

	private JSONObject crearLibro(String autor,String titulo, String paginas, String editorial){
		JSONObject libro = new JSONObject();
		libro.put("autor", autor);
		libro.put("titulo", titulo);
		libro.put("paginas", paginas);
		libro.put("editorial", editorial);
		return libro;
	}

	private void guardarFicheroJson(JSONArray autores) {
		// TODO
	}

	/**
	 * @return  devuelve un JSONArray con el contenido del archivo que podemos manejar en el programa.
	 */
	private JSONArray obtenerAutoresJson() {
        String librosString;
		JSONArray librosArray=null;
        try {
            librosString = new String(Files.readAllBytes(Paths.get(RUTA_FICHERO)));
			librosArray= new JSONArray(librosString);
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Imposible leer los autores del archivo JSON: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
        }
        return librosArray;
    }
//
//	private int obtenerPosicionAutor(String nombreAutor, JSONArray autores){
//		// TODO
//	}

//	private JSONObject obtenerAutoresJson(String nombreAutor){
//		// TODO
//	}

	public void ejecutar(){
		// TODO
		this.ventanaInicioSesion = new VentanaInicioSesion(this);
		ventanaInicioSesion.setVisible(true);
		crearFicheroJson();
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
