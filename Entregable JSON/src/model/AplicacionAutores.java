package model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
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

	private JSONArray listadoAutores;

	public JSONArray getListadoAutores() {
		return listadoAutores;
	}

	public void setListadoAutores(JSONArray listadoAutores) {
		this.listadoAutores = listadoAutores;
	}

	/**
	 * Este metodo comprueba si el fichero existe y si no existe lo crea y lo llena con el contenido en formato .json
	 * usando el metood .toString() de la clase JSONArray.
	 * Creando nuevos objetos libros mediante el metodo crearLibro() que se encuentra justo debajo en la Clase AplicacionAutores.
	 */
	private void crearFicheroJson()	{
		File rutaArchivoJson = new File(RUTA_FICHERO);
        try {
            if (!rutaArchivoJson.exists() || Files.size(Paths.get(RUTA_FICHERO)) == 0){
                JSONArray libreria = new JSONArray();
                libreria.put(crearLibro("María Fernández","Título 1","239","Anaya"));
                libreria.put(crearLibro("Elvira Nieto","Título 2","430","McMillan"));
                setListadoAutores(libreria);
                try (FileWriter fw = new FileWriter(RUTA_FICHERO)){
                    fw.write(getListadoAutores().toString(4));
                    fw.flush();
                }catch (IOException e){
                    JOptionPane.showMessageDialog(null, "Error al escribir el archivo JSON: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
				setListadoAutores(cargarContenido());
			}
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al comprobar el archivo JSON: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
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
			Path pathToFile = Paths.get(RUTA_FICHERO);
			if (!Files.exists(pathToFile) || Files.size(pathToFile) == 0){
				autoresArray = new JSONArray();
			}else{
				librosString = new String(Files.readAllBytes(pathToFile));
				librosArray= new JSONArray(librosString);
				for (Object obj : librosArray){
					JSONObject jsonObj = (JSONObject) obj;
					JSONObject autor=null;
					autor.put("autor",jsonObj.getString("autor")) ;
					autoresArray.put(autor);
				}
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
	private JSONObject obtenerAutoresJson(String nombreAutor){
		JSONObject autor =  new JSONObject();
		autor.put("nombre",nombreAutor);

		try {
			String librosString = new String(Files.readAllBytes(Paths.get(RUTA_FICHERO)));
			JSONArray librosArray = new JSONArray(librosString);
			for (Object libroObject:librosArray){
				JSONObject libro = (JSONObject) libroObject;
				if (libro.getString("autor").equalsIgnoreCase(nombreAutor)){
					autor.put("titulo",libro.getString("titulo"));
					autor.put("paginas",libro.getString("paginas"));
					autor.put("editorial",libro.getString("editorial"));
				}
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error durante la creacion del objeto Json autor ( Que contiene todos sus libros.): " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return autor;
	}
	/* DEVUELVE UN JSONARRAY

	private JSONObject obtenerAutoresJson(String nombreAutor){
		JSONArray autor =  new JSONArray();
		JSONObject nombreAutorObjeto = new JSONObject();
		nombreAutorObjeto.put("nombre",nombreAutor);
		autor.put(nombreAutorObjeto);
		try {
			String librosString = new String(Files.readAllBytes(Paths.get(RUTA_FICHERO)));
			JSONArray librosArray = new JSONArray(librosString);
			for (Object libroObject:librosArray){
				JSONObject libro = (JSONObject) libroObject;
				if (libro.getString("autor").equalsIgnoreCase(nombreAutor)){
					JSONObject libroDelAutor = new JSONObject();
					libroDelAutor.put("titulo",libro.getString("titulo"));
					libroDelAutor.put("paginas",libro.getString("paginas"));
					libroDelAutor.put("editorial",libro.getString("editorial"));
					autor.put(libroDelAutor);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return autor;
	}
	 */

	public void ejecutar(){
		// TODO
		this.ventanaInicioSesion = new VentanaInicioSesion(this);
		ventanaInicioSesion.setVisible(true);
		crearFicheroJson();
	}

	/**
	 * A este metodo le pasamos como String el nombre del autor y la obra. El metodo se encarga de comprobar si
	 * existen dentro de nuestro archivo '.json'. Recorriendo el JSONArray que creamos desde el archivo.
	 * En cada ciclo comprueba si el autor y titulo de ese objeto coinciden con los pasados por parámetro. Además si
	 * coinciden dentro del mismo ciclo tambien ponemos a true el booleano 'autorYTítulo'. Este nos indica que existe el
	 * libro que buscamos con el autor que buscamos. Si solo uno de ellos se encuentra, es decir, si el autor se encuentra pero
	 * no coincide con el titulo, lanzaremos un aviso que la combinacion no es correcta. Si no se encuentra el autor, solo con esa condicion
	 * entonces saltará otro mensaje avisando de que el autor no existe
	 *
	 * @param nombreAutor
	 * @param tituloLibroAutor
	 */
	public void iniciarValidacion(String nombreAutor, String tituloLibroAutor){
		JSONArray libreria =  null;
		boolean tituloRegistro = false;
		boolean autorRegistro = false;

		boolean autorYTitulo =false;

		try {
			libreria = new JSONArray(new String(Files.readAllBytes(Paths.get(RUTA_FICHERO))));
			for (Object o :libreria){
				boolean autorCiclo = false;
				boolean tituloCiclo = false;
				JSONObject libro = (JSONObject) o;
				if (libro.getString("autor").equalsIgnoreCase(nombreAutor)){
					autorCiclo = true;
					autorRegistro = true;
				}
				if (libro.getString("titulo").equalsIgnoreCase(tituloLibroAutor)){
					tituloCiclo = true;
					tituloRegistro = true;
				}
				if (autorCiclo && tituloCiclo){
					autorYTitulo = true;
				}
			}
			// Si tengo autor o titulo a true significa que existen pero si no tengo autorYTitulo es que no han existido juntas
			if (((autorRegistro || tituloRegistro)&&(!autorYTitulo))){
				JOptionPane.showMessageDialog(null, "La combinacion de autor y titulo no existen");
			} else if (!autorRegistro) {
				JOptionPane.showMessageDialog(null, "El autor no existe");
			} else {
				this.ventanaMenuAutor = new VentanaMenuAutor(this,nombreAutor);
				this.ventanaMenuAutor.setVisible(true);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al validar el autor y título: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cerrarSesion(){
		//TODO
	}

	public void crearAutor(String nombre, String titulo, String paginas, String editorial){
		JSONArray listaLibrosJSON;

		try(FileWriter fw = new FileWriter(RUTA_FICHERO)) {

			listaLibrosJSON = cargarContenido();
			System.out.println(listaLibrosJSON);
			listaLibrosJSON.put(crearLibro(nombre,titulo,paginas,editorial));
			JOptionPane.showMessageDialog(null, "Libro añadido correctamente");

			fw.write(listaLibrosJSON.toString(4));
			fw.flush();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al leer el JSON: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JSONArray cargarContenido() {

		try {

			if (Files.size(Paths.get(RUTA_FICHERO)) != 0 ) {
				setListadoAutores(new JSONArray(new String(Files.readAllBytes(Paths.get(RUTA_FICHERO)))));
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error durante la creacion del objeto Json autor ( Que contiene todos sus libros.): " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return getListadoAutores();
	}

	public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo){
		// TODO
	}

	public void borrarAutor(String nombreAutor){
		// TODO
	}

	/**
	 * A este método le llega el nombre del autor y busca en nuestro JSONArray, el de la app, para encontrar el libro que necesitamos
	 * Podemos usarlo de esta forma porque sabemos que el libro existe con este autor, solo lo usamos durante la ventana de 'VerDatos'
	 *
	 * @param nombreAutor
	 * @return
	 */
	public String obtenerPaginas(String nombreAutor){
		String paginas = "";

		JSONArray librosArray =getListadoAutores();
		for (Object libroObject:librosArray){
			JSONObject libro = (JSONObject) libroObject;
			if (libro.getString("autor").equalsIgnoreCase(nombreAutor)){
				return libro.getString("paginas");
			}
		}

		return paginas;
	}

	public String obtenerEditorial(String nombreAutor){
		String editorial = "";

		JSONArray librosArray =getListadoAutores();
		for (Object libroObject:librosArray){
			JSONObject libro = (JSONObject) libroObject;
			if (libro.getString("autor").equalsIgnoreCase(nombreAutor)){
				return libro.getString("editorial");
			}
		}

		return editorial;
	}

	public void mostrarVentanaCrearAutor(){
		this.ventanaCrearAutor = new VentanaCrearAutor(this);
		this.ventanaCrearAutor.setVisible(true);
	}

	public void mostrarVentanaVerDatos(String nombreAutor){
		this.ventanaVerDatos = new VentanaVerDatos(this,nombreAutor,obtenerPaginas(nombreAutor),obtenerEditorial(nombreAutor));
		this.ventanaVerDatos.setVisible(true);
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
