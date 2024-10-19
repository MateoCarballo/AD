package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import model.excepciones.ElAutorYaExiste;
import org.json.JSONArray;
import org.json.JSONObject;

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

	// Añado este atributo para poder tener la ultima modificacion del archivo Json.

	private JSONArray listadoAutores;

	public JSONArray getListadoAutores() {
		return listadoAutores;
	}

	public void setListadoAutores(JSONArray listadoAutores) {
		this.listadoAutores = listadoAutores;
	}


	public void ejecutar(){
		this.ventanaInicioSesion = new VentanaInicioSesion(this);
		ventanaInicioSesion.setVisible(true);
		crearFicheroJson();
	}


	/**
	 * Este metodo comprueba si el fichero existe y si no existe lo crea y lo llena con el contenido en formato .json
	 * usando el metood .toString() de la clase JSONArray. Sustituye al metodo -> guardarFicheroJson(JSONArray autores)
	 *
	 * Creando nuevos objetos libros mediante el metodo crearLibro() que se encuentra justo debajo en la Clase AplicacionAutores.
	 */
	private void crearFicheroJson()	{
		File rutaArchivoJson = new File(RUTA_FICHERO);
        try {
            if (!rutaArchivoJson.exists() || Files.size(Paths.get(RUTA_FICHERO)) == 0){
                JSONArray libreria = new JSONArray();
                /* En esta parte cargaba datos para probarlo
                libreria.put(crearLibro("María Fernández","Título 1","239","Anaya"));
                libreria.put(crearLibro("Elvira Nieto","Título 2","430","McMillan"));
                 */
                setListadoAutores(libreria);
                try (FileWriter fw = new FileWriter(RUTA_FICHERO)){
                    fw.write(getListadoAutores().toString(4));
                    fw.flush();
                }catch (IOException e){
                    JOptionPane.showMessageDialog(null, "Error al escribir el archivo JSON: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
				setListadoAutores(cargarContenidoAlJSONArray());
			}
        } catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al crear el archivo JSON: " + e.getMessage(),
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
		JSONObject autor;
		for (int i = 0; i < autores.length(); i++) {
			autor = (JSONObject) autores.get(i);
			if (autor.getString("autor").equalsIgnoreCase(nombreAutor)){
				indiceAutor = i;
			}
		}
		return indiceAutor;
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
		JSONArray libreria;
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
				mostrarMenuAutor(nombreAutor);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al validar el autor y título: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cerrarSesion(){
		//TODO no entiendo como implementarlo cada ventana se cierra a si misma
	}

	public void crearAutor(String nombre, String titulo, String paginas, String editorial){
		JSONArray listaLibrosJSON;

		try(FileWriter fw = new FileWriter(RUTA_FICHERO)) {

			listaLibrosJSON = cargarContenidoAlJSONArray();
			listaLibrosJSON.put(crearLibro(nombre,titulo,paginas,editorial));
			JOptionPane.showMessageDialog(null, "Libro añadido correctamente");

			fw.write(listaLibrosJSON.toString(4));
			fw.flush();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al leer el JSON: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Este mét0do devuelve un JSONArray con el contenido de nuestro archivo que
	 * guardaremos en @listadoAutores para poder manejarlo dentro de nuestra app.
	 * @return
	 */
	private JSONArray cargarContenidoAlJSONArray() {

		try {
			if (Files.size(Paths.get(RUTA_FICHERO)) != 0 ) {
				setListadoAutores(new JSONArray(new String(Files.readAllBytes(Paths.get(RUTA_FICHERO)))));
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar el contenido del .json dentro del JSONArray del programa: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return getListadoAutores();
	}

	/**
	 * Este método escribe lo que tenemos alamacenado dentro de nuestra variable de tipos JSONArray @listadoAutores
	 */
	public void escribirContenidoDesdeElJSONArray(){
		try(FileWriter fw = new FileWriter(RUTA_FICHERO)){
			fw.write(listadoAutores.toString(4));
			fw.flush();
		}catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error durante la escritura del archivo .Json","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Este método recoore el JSONArray casteando cada elemento a un JSONObject y buscano si tiene el mismo nombre que el
	 * que llega como parametro, si es así se cambia el titulo que tiene por el nuevo titulo introducido.
	 * @param nombreAutor
	 * @param nuevoTitulo
	 */
	public void cambiarTituloLibro(String nombreAutor, String nuevoTitulo){

		for (Object libroObject:getListadoAutores()) {
			JSONObject libro = (JSONObject) libroObject;
			if (libro.getString("autor").equalsIgnoreCase(nombreAutor)) {
			libro.put("titulo", nuevoTitulo);
			}
			escribirContenidoDesdeElJSONArray();
		}
		this.ventanaCambiarTitulo.dispose();
	}


	/**
	 * Este metodo elimina un libro buscandolo por el nombre de su autor. Para esto llama al método obtenerPosicionAutor
	 * que devuelve un entero con el indice que ocupa ese autor en el JSON que mandamos como parametro.
	 *
	 * @param nombreAutor
	 */
	public void borrarAutor(String nombreAutor){
		getListadoAutores().remove(obtenerPosicionAutor(nombreAutor,getListadoAutores()));
		escribirContenidoDesdeElJSONArray();
		ventanaBorrarAutor.dispose();
		ventanaMenuAutor.dispose();
	}

	/**
	 * A este método le llega el nombre del autor y busca en nuestro JSONArray, el de la app,
	 * para encontrar el numero de paginas del libro que tiene el autor que llega como parametro.
	 *
	 * Podemos usarlo de esta forma porque sabemos que el libro existe con este autor, solo lo
	 * usamos durante la ventana de 'VerDatos'
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

	/**
	 * A este método le llega el nombre del autor y busca en nuestro JSONArray, el de la app,
	 * para encontrar la editorial del libro que tiene el autor que llega como parametro.
	 *
	 * Podemos usarlo de esta forma porque sabemos que el libro existe con este autor, solo lo
	 * usamos durante la ventana de 'VerDatos'
	 *
	 * @param nombreAutor
	 * @return
	 */

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
		this.ventanaCambiarTitulo = new VentanaCambiarTitulo(this,nombreAutor);
		this.ventanaCambiarTitulo.setVisible(true);
	}

	public void mostrarVentanaBorrarAutor(String nombreAutor){
		this.ventanaBorrarAutor = new VentanaBorrarAutor(this,nombreAutor);
		this.ventanaBorrarAutor.setVisible(true);
	}

	public void mostrarMenuAutor(String nombreAutor){
		this.ventanaMenuAutor = new VentanaMenuAutor(this,nombreAutor);
		this.ventanaMenuAutor.setVisible(true);
	}

	/**
	 * En este metodo recibimos las propiedades de este libro, para comprobar si ya esta escrito en el sistema
	 * para esto comprobamos si existe algun objeto que comparta titulo y autor con los dados si es asi devuelve true (si existe)
	 * Si nos devuelve False significa que el titulo y autor no coinciden dentro del mismo objeto y nos permite introducir en nuevo libro en el sistema
	 *
	 * @param nombreAutor
	 * @param tituloLibro
	 * @return
	 */
	public boolean comprobarSiExiste(String nombreAutor,String tituloLibro) throws ElAutorYaExiste {
		boolean existe = false;
		for (Object o : getListadoAutores()){
			JSONObject libro = (JSONObject) o;
			if ((libro.getString("autor").equalsIgnoreCase(nombreAutor)) && libro.getString("titulo").equalsIgnoreCase(tituloLibro)) {
				existe = true;
			}
		}
		if (existe){
			throw new ElAutorYaExiste("El autor que intentas añadir ya existe dentro de la base de datos");
		}
		return existe;
	}
}
