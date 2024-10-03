import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
/*
Ejercicio 115
Crea el fichero peliculas.xml que tenga el siguiente contenido:

<?xml version="1.0" encoding="UTF-8"?>
<peliculas>
    <pelicula id="1">
        <titulo>El señor de los anillos</titulo>
        <ano>1999</ano>
        <precio>19.99</precio>
    </pelicula>
    <pelicula id="2">
        <titulo>Star Wars</titulo>
        <ano>2005</ano>
        <precio>12.50</precio>
    </pelicula>
    <pelicula id="3">
        <titulo>Harry Potter</titulo>
        <ano>2001</ano>
        <precio>8.25</precio>
    </pelicula>
</peliculas>
 */

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            DOMImplementation implementation = docBuilder.getDOMImplementation();
            Document doc = docBuilder.getDOMImplementation().createDocument(null,"root",null);

            Element titulo = doc.createElement("titulo");
            titulo.setTextContent("Harry Potter");

            Element ano = doc.createElement("ano");
            ano.setTextContent("2001");

            Element precio = doc.createElement("precio");
            precio.setTextContent("8.25");

            Element pelicula = doc.createElement("pelicula");
            Attr atributoPelicula = doc.createAttribute("id");
            atributoPelicula.setValue("3");
            pelicula.setAttributeNode(atributoPelicula);
            pelicula.appendChild(titulo);
            pelicula.appendChild(ano);
            pelicula.appendChild(precio);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("/src/peliculas.xml"));
            transformer.transform(source,result);


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}