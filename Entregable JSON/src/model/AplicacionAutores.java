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

	/**
	 * Este metodo comprueba si el fichero existe y si no existe lo crea y lo llena con el contenido en formato .json
	 * usando el metood .toString() de la clase JSONArray.
	 * Creando nuevos objetos libros mediante el metodo crearLibro() que se encuentra justo debajo en la Clase AplicacionAutores.
	 */
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
	 * En este metodo mi objetivo es:
	 * 	1. Coger tod0 el contenido del .json y guardarlo en un JSONArray.
	 * 	2. Recorrer ese array y crear un nuevo JSONObject con esta forma "autor": "María Fernández".
	 * 	3. Anhadir este nuevo objeto json a la nueva JSONArray de autores.
	 * 	4. Devolver el JSONArray de autores.
	 *
	 *
	 * @return devuelve un objeto JSONArray con todos los autores dentro del fichero especificado en la ruta del programa.
	 */
	private JSONArray obtenerAutoresJson() {
        String librosString;
		JSONArray librosArray=null;
		JSONArray autoresArray=null;
        try {
            librosString = new String(Files.readAllBytes(Paths.get(RUTA_FICHERO)));
			librosArray= new JSONArray(librosString);
			for (Object obj : librosArray){
				JSONObject jsonObj = (JSONObject) obj;
				JSONObject autor=null;
				autor.put("autor",jsonObj.getString("autor")) ;
				autoresArray.put(autor);
			}
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Imposible leer los autores del archivo JSON: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
        }
        return autoresArray;
    }

	/**
	 * Este método recoore el JSONArray buscando en cada objeto si el valor de 'autor:'coincide con el valor que pasamos como parametro al metodo.
	 * En caso de encontrar un autor con el nombre que buscamos, guardamos el indice en el que se encuentra este objeto dentro de 'indiceAutor' y lo devolvemos
	 *
	 * @param nombreAutor Este parametro es el nombre del autor que buscamos dentro del JSONArray.
	 * @param autores Es la lista de autores sobre la que vamos buscar un autor dado.
	 * @return devulve el indice en el que se encuentra el autor que buscamos y si no esta contenido en el JSONArray nos devuelve '-1'.
	 */
	private int obtenerPosicionAutor(String nombreAutor, JSONArray autores){
		int indiceAutor=-1;
		JSONObject autor=null;
		for (int i = 0; i < autores.length(); i++) {
			autor = (JSONObject) autores.get(i);
			if (autor.getString("autor").equalsIgnoreCase(nombreAutor)){
				indiceAutor = i;
			}
		}
		return indiceAutor;
	}

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
