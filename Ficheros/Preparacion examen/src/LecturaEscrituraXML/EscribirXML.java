package LecturaEscrituraXML;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class EscribirXML {
    final static String RUTA = "src"+File.separator+"LecturaEscrituraXML"+File.separator+"Archivos";

    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        creaXMLPersonas("Libreria.xml");
//        try {
//            leerXML();
//        } catch (ParserConfigurationException | SAXException | IOException e) {
//            System.out.println("Se ha lanzado una excepcion del tiopo -> " + e.getMessage());
//        }

    }

    private static void creaXMLPersonas(String nombreArchivo) throws TransformerException, ParserConfigurationException {
        crearDirectorio();
        File archivo = new File(RUTA + File.separator + File.separator + nombreArchivo );

        try{
            Document documentoXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().
                    getDOMImplementation().createDocument(null,"libreria",null);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");

            Element libreria = documentoXML.getDocumentElement();

            Element libro = documentoXML.createElement("libro");
            libro.setAttribute("isbn","12345678AA");
            Element titulo = documentoXML.createElement("titulo");
            titulo.setTextContent("Nombre del libro");
            libro.appendChild(titulo);
            libreria.appendChild(libro);

            libro = documentoXML.createElement("libro");
            titulo = documentoXML.createElement("titulo");
            titulo.setTextContent("Titulo del libro 2");
            libro.appendChild(titulo);

            libro.setAttribute("isbn","87654321Z");
            libreria.appendChild(libro);

            DOMSource domSource = new DOMSource(documentoXML);
            StreamResult streamResult = new StreamResult(archivo);
            transformer.transform(domSource,streamResult);

        } catch (ParserConfigurationException e) {
            throw new ParserConfigurationException("Error del tipo ParserConfigurationException");
        }
    }

    private static void crearDirectorio(){
        File directorio = new File(RUTA + File.separator + "Archivos Escribirxml" );

        if (!directorio.exists()){
            if (directorio.mkdirs()){
                System.out.println("Creada carpeta contenedora del archivo \"Personas.xml\"");
            }
        }
    }

}
